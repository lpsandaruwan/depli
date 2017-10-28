/**
 * Created by lpsandaruwan on 3/21/17.
 */

var instanceViewModule = angular.module("instanceViewModule", ["chart.js"]);

instanceViewModule
.controller("statisticsController",
    function ($http, $interval, $scope, $timeout, JMXNodeService,
        ClassLoadingDataService, GraphDataService, MemoryDataService,
        OperatingSystemDataService, PlatformResourcesDataService,
        RuntimeDataService, ThreadDataService) {
      // page animation helper
      $scope.pageClass = "page-instance";

      // selected node
      $scope.jmxNodeId = JMXNodeService.getSelectedNodeId();

      // get jmx node metadata information
      $scope.jmxNode = [];

      // get node metadata
      var getJmxNodeMetadata = function () {
        JMXNodeService.getByNodeId($scope.jmxNodeId)
        .then(function onSuccess(response) {
          $scope.jmxNode = response.data;
        })
      };
      getJmxNodeMetadata();

      // get operating system information
      var getOperatingSystemInformation = function () {
        $timeout(function () {
          OperatingSystemDataService.getByDescriptorId($scope.jmxNodeId)
          .then(function (response) {
            $scope.operatingSystemInformation = response.data;
          })
        }, 100)

      };
      getOperatingSystemInformation();

      // get runtime related data
      var getDynamicRuntimeInformation = function () {
        $timeout(function () {
          RuntimeDataService.getDynamicsByDescriptorId($scope.jmxNodeId)
          .then(function onSuccess(response) {
            $scope.runtimeDynamics = response.data;
          });
        }, 100)
      };
      getDynamicRuntimeInformation();

      var getStaticRuntimeInformation = function () {
        $timeout(function () {
          RuntimeDataService.getStaticsByDescriptorId($scope.jmxNodeId)
          .then(function onSuccess(response) {
            $scope.runtimeStatics = response.data;
          });
        }, 100)
      };
      getStaticRuntimeInformation();

      // get class loading data
      var getClassLoadingInformation = function () {
        ClassLoadingDataService.getByDescriptorId($scope.jmxNodeId)
        .then(function onSuccess(response) {
          $scope.classLoadingData = response.data;
        })
      };
      getClassLoadingInformation();

      var getGraphData = function () {
        GraphDataService.getClassGraphDataByDescriptorId($scope.jmxNodeId)
        .then(function onSuccess(response) {
          $scope.classLoadingChartData = [response.data.loadedClassCountGraphData];
        });

        GraphDataService.getCpuGraphDataByDescriptorId($scope.jmxNodeId)
        .then(function onSuccess(response) {
          $scope.cpuGraphData = [response.data.jvmCpuGraphData,
            response.data.hostCpuGraphData];
        });
      };
      getGraphData();

      // set class chart metadata
      $scope.classChartLabels = [
        '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '',
        '', ''
      ];
      $scope.classChartSeries = ['Loaded'];
      $scope.classChartColors = ['#FF220D'];

      // get memory store
      var getMemoryInformation = function () {
        MemoryDataService.getByDescriptorId($scope.jmxNodeId)
        .then(function onSuccess(response) {
          $scope.memoryData = response.data;

          $scope.memChartData = [
            [response.data.heapMemory.init, response.data.nonHeapMemory.init],
            [response.data.heapMemory.used, response.data.nonHeapMemory.used],
            [response.data.heapMemory.committed,
              response.data.nonHeapMemory.committed],
            [response.data.heapMemory.max, response.data.nonHeapMemory.max]
          ];
        })
      };
      getMemoryInformation();

      // set memory chart metadata
      $scope.memChartLabels = ['Heap', 'Non Heap'];
      $scope.memChartSeries = ['init', 'used', 'committed', 'max'];

      // get platform extension operating system data
      var getPlatformResourceData = function () {
        PlatformResourcesDataService.getByDescriptorId($scope.jmxNodeId)
        .then(function onSuccess(response) {
          $scope.platformResources = response.data;

          $scope.hostCpuUsage = response.data.hostCpuUsage;
          $scope.jvmUptime = response.data.jvmUptime;

          // set CPU chart color
          if (response.data.jvmCpuUsage < 33) {
            $scope.cpuChartColor = ['#868686', '#9E7A77'];
          }
          else if (response.data.jvmCpuUsage > 33 && response.data.hostCpuUsage
              < 66) {
            $scope.cpuChartColor = ['#B05B4F', '#9E7A77'];
          }
          else {
            $scope.cpuChartColor = ['#FF220D', '#9E7A77'];
          }

          $scope.hostMemChartData = [
            [(response.data.totalPhysicalMemory
            - response.data.freePhysicalMemory),
              (response.data.totalSwapSpace - response.data.freeSwapSpace)],
            [response.data.totalPhysicalMemory, response.data.totalSwapSpace]
          ];
        })
      };
      getPlatformResourceData();

      // set host memory chart metadata
      $scope.hostMemChartLabels = ['Physical', 'Swap'];
      $scope.hostMemChartSeries = ['used', 'total'];
      $scope.hostMemChartColors = ['#B05B4F', '#9E7A77'];

      // cpu chart store
      $scope.cpuChartLabels = [
        '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '',
        '', ''];
      $scope.cpuChartSeries = ['JVM CPU', 'Host CPU'];

      var pollData = function () {
        $interval(function () {
          getClassLoadingInformation();
          getGraphData();
          getMemoryInformation();
          getPlatformResourceData();
          getDynamicRuntimeInformation();
        }, 1000)
      };
      pollData();

      // destroy intervals on page exit
      $scope.$on('$destroy', function () {
        $interval.cancel(pollData);
      });
    })

.controller("threadDataController",
    function ($http, $interval, $scope, $timeout, JMXNodeService,
        ThreadDataService) {
      $scope.jmxNodeId = JMXNodeService.getSelectedNodeId();

      var getThreadInformation = function () {
        ThreadDataService.getDumpsByDescriptorId($scope.jmxNodeId)
        .then(function onSuccess(response) {
          $scope.threadData = response.data.threadInfoList;
        })
      };
      getThreadInformation();

      var pollThreadInformation = function () {
        $interval(function () {
          getThreadInformation();
        }, 60000)
      };
      pollThreadInformation();

      // destroy intervals on page exit
      $scope.$on('$destroy', function () {
        $interval.cancel(pollThreadInformation);
      });
    });
