/**
 * Controller for frontend dashboard
 * Created by lpsandaruwan on 3/21/17.
 */

var homeViewModule = angular.module("homeViewModule", ["chart.js"]);


homeViewModule
    .controller("homeViewModule", function ($http, $interval, $rootScope, $scope, jmxNodeService) {
        // jmx node object
        $scope.jmxNodeList = [];
        $scope.isNullList = true;


        // obtain jmx node list
        var getJmxNodeList = function () {
            $http.get("nodes")
                .then(function onSuccess(response) {
                    $scope.jmxNodeList = response.data;

                    if (response.data.length !== 0) {
                        $scope.isNullList = false;
                    }
                    else {
                        $scope.isNullList = true;
                    }
                })
                .catch(function onError() {
                });
        };


        // obtain jmx node data
        getJmxNodeList();


        // refresh stat data
        var refreshStatisticsData = function () {
            $interval(function () {
                angular.forEach($scope.jmxNodeList, function (jmxNode) {
                    $http.get("stats/" + jmxNode.nodeId)
                        .then(function onSuccess(response) {
                            if (response.data !== null) {
                                jmxNode.isInitialized = true;

                                // cpu related data
                                jmxNode.chartData = [response.data.jvmCpuUsageData, response.data.hostCpuUsageData];
                                jmxNode.isConnected = response.data.connected;
                                jmxNode.jvmCpuUsage = response.data.jvmCpuUsage;


                                // set chart color
                                if (response.data.jvmCpuUsage < 33) {
                                    jmxNode.chartColor = ['#868686', '#9E7A77'];
                                }
                                else if (response.data.jvmCpuUsage > 33 && response.data.cpuUsage < 66) {
                                    jmxNode.chartColor = ['#B05B4F', '#9E7A77'];
                                } else {
                                    jmxNode.chartColor = ['#FF220D', '#9E7A77'];
                                }


                                // class loading data
                                jmxNode.loadedClassCount = response.data.loadedClassCount;

                                // memory related data
                                jmxNode.usedHeapMemory = response.data.usedHeapMemory;
                                jmxNode.usedNonHeapMemory = response.data.usedNonHeapMemory;

                                // thread loading data
                                jmxNode.liveThreadCount = response.data.liveThreadCount;

                                // host operating system data
                                jmxNode.hostCpuUsage = response.data.hostCpuUsage;
                                jmxNode.hostFreePhysicalMemory = response.data.hostFreePhysicalMemory;
                                jmxNode.hostTotalPhysicalMemory = response.data.hostTotalPhysicalMemory;
                            }

                            else {
                                jmxNode.isInitialized = false;
                            }

                        })
                        .catch(function onError(response) {
                            jmxNode.isInitialized = false;
                            jmxNode.error = "Status code: " + response.status;
                        });
                });
            }, 500);
        };
        refreshStatisticsData();


        // goto instance view helper
        $scope.gotoInstanceView = function (jmxNode) {
            jmxNodeService.setNodeName("> " + jmxNode.nodeName + "@" + jmxNode.hostname);
            $rootScope.toolbarHeader = jmxNodeService.getNodeName();
            jmxNodeService.selectJmxNode(jmxNode.nodeId);
        };


        // cpu chart data
        $scope.chartLabels = [
            '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''
        ];
        $scope.chartSeries = ['JVM CPU', 'Host CPU'];


        // cancel sync on page exit
        $scope.$on('$destroy', function () {
            $interval.cancel(refreshStatisticsData);
        });
    });
