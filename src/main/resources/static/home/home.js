/**
 * Controller for frontend dashboard
 * Created by lpsandaruwan on 3/21/17.
 */

var homeViewModule = angular.module("homeViewModule", []);


homeViewModule
    .controller("homeViewModule", function ($http, $scope, $interval) {

        // jmx node object
        $scope.jmxNodeList = [];

        // obtain jmx node list
        var getJmxNodeList = function () {
            $http.get("nodes")
                .then(function onSuccess(response) {
                    $scope.jmxNodeList = response.data;
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

                            // cpu related data
                            jmxNode.chartData = [response.data.cpuUsageData];
                            jmxNode.isConnected = response.data.isConnected;
                            jmxNode.cpuUsage = response.data.cpuUsage;

                            // set chart color
                            if(response.data.cpuUsage < 33) {
                                jmxNode.chartColor = [ '#868686'];
                            }
                            else if (response.data.cpuUsage > 33 && response.data.cpuUsage < 66) {
                                jmxNode.chartColor = [ '#B05B4F'];
                            } else {
                                jmxNode.chartColor = [ '#FF220D'];
                            }

                            // memory related data
                            jmxNode.usedHeapMemory = response.data.usedHeapMemory;
                            jmxNode.usedNonHeapMemory = response.data.usedNonHeapMemory;
                        })
                        .catch(function onError(response) {

                        });
                });
            }, 500);
        };

        refreshStatisticsData();


        // cpu chart data
        $scope.chartLabels = [
            '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''];
        $scope.chartSeries = ['CPU'];
    });
