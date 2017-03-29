/**
 * Created by lpsandaruwan on 3/21/17.
 */

var instanceViewModule = angular.module("instanceViewModule", ["chart.js"]);


instanceViewModule
    .controller("instanceViewModule", function ($http, $interval, $scope, $timeout, jmxNodeService) {
        // page animation helper
        $scope.pageClass = "page-instance";

        // selected node
        $scope.jmxNodeId = jmxNodeService.getSelectedNode();

        // get jmx node metadata information
        $scope.jmxNode = [];


        var getJmxNodeMetadata = function () {
            $http.get("nodes/" + $scope.jmxNodeId)
                .then(function onSuccess(response) {
                    $scope.jmxNode = response.data;
                })
                .catch(function onError() {

                });
        };
        getJmxNodeMetadata();


        // get operating environment data
        var getEnvironData = function () {
            $timeout(function () {
                $http.get("osdobjects/" + $scope.jmxNodeId)
                    .then(function (response) {
                        $scope.jmxNode.envData = response.data;
                    })
                    .catch(function onError() {

                    })
            }, 300)

        };
        getEnvironData();


        // poll and set class loading related data
        var pollClassLoadingInformation = function () {
            $interval(function () {
                $http.get("cdobjects/" + $scope.jmxNodeId)
                    .then(function onSuccess(response) {
                        $scope.jmxNode.classData = response.data;
                        $scope.classChartData = [response.data.loadedClassCountData];
                    })
            }, 1000)
        };
        pollClassLoadingInformation();

        $scope.labelsClassChart = [
            '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''
        ];
        $scope.seriesClassChart = ['Loaded'];
        $scope.colorsClassChart = ['#FF220D'];


        // poll and set memory loading data
        var pollMemoryLoadingInformation = function () {
            $interval(function () {
                $http.get("mdobjects/" + $scope.jmxNodeId)
                    .then(function onSuccess(response) {
                        $scope.jmxNode.memData = response.data;

                        $scope.dataMemChart = [
                            [response.data.heapMemory.init, response.data.nonHeapMemory.init],
                            [response.data.heapMemory.used, response.data.nonHeapMemory.used],
                            [response.data.heapMemory.committed, response.data.nonHeapMemory.committed],
                            [response.data.heapMemory.max, response.data.nonHeapMemory.max]
                        ];
                    })
            }, 1000)
        };
        pollMemoryLoadingInformation();

        $scope.labelsMemChart = ['Heap', 'Non Heap'];
        $scope.seriesMemChart = ['init', 'used', 'committed', 'max'];


        // poll and set platform extension operating system related data
        var pollPEOSInformation = function () {
            $interval(function () {
                $http.get("peosdobjects/" + $scope.jmxNodeId)
                    .then(function onSuccess(response) {
                        $scope.jmxNode.peosData = response.data;

                        $scope.hostMemChartData = [
                            [response.data.freePhysicalMemory, response.data.freeSwapSpace],
                            [response.data.totalPhysicalMemory, response.data.totalSwapSpace]
                        ];
                    })
            }, 1000)
        };
        pollPEOSInformation();

        $scope.labelsHostMemChart = ['Physical', 'Swap'];
        $scope.seriesHostMemChart = ['free', 'total'];


        // poll and set runtime related data
        var pollRuntimeInformation = function () {
            $interval(function () {
                $http.get("rdobjects/" + $scope.jmxNodeId)
                    .then(function onSuccess(response) {
                        $scope.jmxNode.runData = response.data;
                    })
            }, 1000)
        };
        pollRuntimeInformation();


        // poll and set threads related data
        var pollThreadInformation = function () {
            $interval(function () {
                $http.get("tdobjects/" + $scope.jmxNodeId)
                    .then(function onSuccess(response) {
                        $scope.jmxNode.threadData = response.data;
                    })
            }, 1000)
        };
        pollThreadInformation();


        // cancel sync on page exit
        $scope.$on('$destroy', function () {
            $interval.cancel(pollClassLoadingInformation);
            $interval.cancel(pollMemoryLoadingInformation);
            $interval.cancel(pollPEOSInformation);
            $interval.cancel(pollRuntimeInformation);
            $interval.cancel(pollThreadInformation);
        });
    });
