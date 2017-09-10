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


        // get node meta domain
        var getJmxNodeMetadata = function () {
            $http.get("nodes/" + $scope.jmxNodeId)
                .then(function onSuccess(response) {
                    $scope.jmxNode = response.data;
                })
                .catch(function onError() {

                });
        };
        getJmxNodeMetadata();


        // get operating environment domain
        var getEnvironData = function () {
            $timeout(function () {
                $http.get("osdobjects/" + $scope.jmxNodeId)
                    .then(function (response) {
                        $scope.jmxNode.envData = response.data;
                    })
                    .catch(function onError() {

                    })
            }, 100)

        };
        getEnvironData();


        // get runtime related domain
        var getRuntimeInformation = function () {
            $timeout(function () {
                $http.get("rdobjects/" + $scope.jmxNodeId)
                    .then(function onSuccess(response) {
                        $scope.jmxNode.runData = response.data;
                    })
            }, 100)
        };
        getRuntimeInformation();


        // get class loading domain
        var getClassLoadingData = function () {
            $http.get("cdobjects/" + $scope.jmxNodeId)
                .then(function onSuccess(response) {
                    $scope.jmxNode.classData = response.data;
                    $scope.classChartData = [response.data.loadedClassCountData];
                })
        };
        getClassLoadingData();

        // set class chart metadata
        $scope.labelsClassChart = [
            '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''
        ];
        $scope.seriesClassChart = ['Loaded'];
        $scope.colorsClassChart = ['#FF220D'];


        // get memory domain
        var getMemoryData = function () {
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
        };
        getMemoryData();

        // set memory chart metadata
        $scope.labelsMemChart = ['Heap', 'Non Heap'];
        $scope.seriesMemChart = ['init', 'used', 'committed', 'max'];


        // get platform extension operating system domain
        var getPEOSInformation = function () {
            $http.get("peosdobjects/" + $scope.jmxNodeId)
                .then(function onSuccess(response) {
                    $scope.jmxNode.peosData = response.data;

                    $scope.hostMemChartData = [
                        [response.data.freePhysicalMemory, response.data.freeSwapSpace],
                        [response.data.totalPhysicalMemory, response.data.totalSwapSpace]
                    ];
                })
        };
        getPEOSInformation();

        // set host memory chart metadata
        $scope.labelsHostMemChart = ['Physical', 'Swap'];
        $scope.seriesHostMemChart = ['free', 'total'];
        $scope.colorsHostMemChart = ['#B05B4F', '#9E7A77'];


        // get statistics domain
        var getStatistics = function () {
            $http.get("stats/" + $scope.jmxNodeId)
                .then(function onSuccess(response) {
                    $scope.hostCpuUsage = response.data.hostCpuUsage;
                    $scope.jvmUptime = response.data.jvmUptime;

                    $scope.statsData = [response.data.jvmCpuUsageData, response.data.hostCpuUsageData];

                    // set chart color
                    if (response.data.jvmCpuUsage < 33) {
                        $scope.cpuChartColor = ['#868686', '#9E7A77'];
                    }
                    else if (response.data.jvmCpuUsage > 33 && response.data.cpuUsage < 66) {
                        $scope.cpuChartColor = ['#B05B4F', '#9E7A77'];
                    }
                    else {
                        $scope.cpuChartColor = ['#FF220D', '#9E7A77'];
                    }
                })
                .catch(function onError() {

                })
        };
        getStatistics();

        // cpu chart domain
        $scope.cpuChartLabels = [
            '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''
        ];
        $scope.cpuChartSeries = ['JVM CPU', 'Host CPU'];


        // poll domain
        var pollData = function () {
            $interval(function () {
                getClassLoadingData();
                getMemoryData();
                getPEOSInformation();
                getStatistics();
            }, 1000)
        };
        pollData();


        // cancel sync on page exit
        $scope.$on('$destroy', function () {
            $interval.cancel(pollData);
        });
    })


    .controller("threadDataController", function ($http, $interval, $scope, $timeout, jmxNodeService) {
        // selected node
        $scope.jmxNodeId = jmxNodeService.getSelectedNode();


        // get thread related domain
        var getThreadInformation = function () {
            $http.get("tdobjects/" + $scope.jmxNodeId)
                .then(function onSuccess(response) {
                    $scope.threadData = response.data.threadInfos;
                })
        };
        getThreadInformation();


        // poll threads related domain
        var pollThreadInformation = function () {
            $interval(function () {
                getThreadInformation();
            }, 60000)
        };
        pollThreadInformation();


        // cancel sync on page exit
        $scope.$on('$destroy', function () {
            $interval.cancel(pollThreadInformation);
        });
    });
