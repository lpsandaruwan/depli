/**
 * Controller for frontend dashboard
 * Created by lpsandaruwan on 3/21/17.
 */

var homeViewModule = angular.module("homeViewModule", ["chart.js"]);

homeViewModule
.controller("homeViewModule",
    function ($http, $interval, $rootScope, $scope, JMXNodeService,
        ClassLoadingDataService, GraphDataService, MemoryDataService,
        PlatformResourcesDataService, ThreadDataService) {
      // jmx node object
      $scope.jmxNodeList = [];
      $scope.isNullList = true;

      // obtain jmx node list
      var getJmxNodeList = function () {
        JMXNodeService.getAll()
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

      // obtain jmx node store
      getJmxNodeList();

      // refresh statistics data
      var refreshStatisticsData = function () {
        $interval(function () {
          angular.forEach($scope.jmxNodeList, function (jmxNode) {

            GraphDataService.getCpuGraphDataByDescriptorId(jmxNode.nodeId)
            .then(function onSuccess(response) {
              jmxNode.chartData = [response.data.jvmCpuGraphData,
                response.data.hostCpuGraphData];

              jmxNode.jvmCpuUsage = response.data.jvmCpuGraphData[
              response.data.pointIndex - 1];
              jmxNode.hostCpuUsage = response.data.hostCpuGraphData[
              response.data.pointIndex - 1];

              // set chart color
              if (response.data.jvmCpuGraphData[response.data.pointIndex - 1]
                  < 33) {
                jmxNode.chartColor = ['#868686', '#9E7A77'];
              }
              else if (response.data.jvmCpuGraphData[response.data.pointIndex
                  - 1] > 33
                  && response.data.jvmCpuGraphData[response.data.pointIndex - 1]
                  < 66) {
                jmxNode.chartColor = ['#B05B4F', '#9E7A77'];
              } else {
                jmxNode.chartColor = ['#FF220D', '#9E7A77'];
              }
            });

            ClassLoadingDataService.getByDescriptorId(jmxNode.nodeId)
            .then(function onSuccess(response) {
              jmxNode.loadedClassCount = response.data.loadedClassCount;
            });

            MemoryDataService.getByDescriptorId(jmxNode.nodeId)
            .then(function onSuccess(response) {
              jmxNode.usedHeapMemory = response.data.heapMemory.used;
              jmxNode.usedNonHeapMemory = response.data.nonHeapMemory.used;
            });

            PlatformResourcesDataService.getByDescriptorId(jmxNode.nodeId)
            .then(function onSuccess(response) {
              jmxNode.freePhysicalMemory = response.data.freePhysicalMemory;
              jmxNode.totalPhysicalMemory = response.data.totalPhysicalMemory;
            });

            ThreadDataService.getCountsByDescriptorId(jmxNode.nodeId)
            .then(function onSuccess(response) {
              jmxNode.liveThreadCount = response.data.liveThreadCount;
            });
          });
        }, 1000);
      };
      refreshStatisticsData();

      // goto instance view helper
      $scope.gotoInstanceView = function (jmxNode) {
        JMXNodeService.setNodeName(
            "> " + jmxNode.nodeName + "@" + jmxNode.hostname);
        $rootScope.toolbarHeader = JMXNodeService.getNodeName();
        JMXNodeService.selectJmxNode(jmxNode.nodeId);
      };

      // chart metadata
      $scope.chartLabels = [
        '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '',
        '', ''
      ];
      $scope.chartSeries = ['JVM CPU', 'Host CPU'];

      // cancel sync on page exit
      $scope.$on('$destroy', function () {
        $interval.cancel(refreshStatisticsData);
      });
    });
