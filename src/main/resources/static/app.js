/**
 * Created by lpsandaruwan on 3/21/17.
 */

var depliFrontend = angular.module("depliFrontend", [
  "chart.js",
  "homeViewModule",
  "instanceViewModule",
  'ngAnimate',
  "ngMessages",
  "ngMaterial",
  "ngRoute",
  "settingsModule"
]);

depliFrontend
.config(
    function ($httpProvider, $locationProvider, $routeProvider, $qProvider) {

      $routeProvider
      .when("/", {
        templateUrl: "home/home.html",
        controller: "homeViewModule"
      })

      .when("/instance/", {
        templateUrl: "instance/instance.html",
        controller: "instanceViewModule"
      })

      .when("/settings", {
        templateUrl: "settings/settings.html",
        controller: "settingsModule"
      })

      .otherwise("/");

      $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
      $qProvider.errorOnUnhandledRejections(false);
    });

depliFrontend
.controller("mainController",
    function ($http, $interval, $location, $mdDialog, $rootScope, $scope,
        $window, jmxNodeService) {
      // page animation helper
      $scope.pageClass = 'page-dashboard';

      // backend status
      // -1 while rebooting, 0 while initializing store, 1 while running
      $scope.backendStatus = 1;

      // set toolbar header
      var setToolbarHeader = function () {
        $rootScope.toolbarHeader = jmxNodeService.getNodeName();
      };
      setToolbarHeader();

      // reload and reinitialize backend store and connections
      $rootScope.reloadBackend = function () {
        $http.get("apptools/reboot")
        .then(function onSuccess(response) {
          if (response.data === true) {
            $window.location.reload();
          }
        })
        .catch(function (response) {

        })
      };

      // reload backend button controller
      $scope.triggerReloadBackend = function (ev) {
        var confirm = $mdDialog.confirm()
        .title("Reload backend ?")
        .textContent(
            "Reinitializing and reloading backend store will take longer. ")
        .ariaLabel('deleteNode')
        .targetEvent(ev)
        .ok("RELOAD")
        .cancel("CANCEL");

        $mdDialog.show(confirm)
        .then(function () {
          $rootScope.reloadBackend();
        })

      };

      // jmx node list to help navigation though node views
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
      // refresh scope store
      getJmxNodeList();

      // redirect to dashboard view
      $scope.gotoDashboardView = function () {
        jmxNodeService.setNodeName("");
        setToolbarHeader();

        $location.url("/");
      };

      // display jmx node in detailed view
      $scope.gotoInstanceView = function (jmxNode) {
        jmxNodeService.setNodeName(
            "> " + jmxNode.nodeName + "@" + jmxNode.hostname);
        setToolbarHeader();

        jmxNodeService.selectJmxNode(jmxNode.nodeId);
      };

      // redirect to settings view
      $scope.gotoSettingsView = function () {
        jmxNodeService.getNodeName("");
        setToolbarHeader();

        $location.url("/settings");
      };

      // get backend status to lock/unlock views
      var getBackendStatus = function () {
        $http.get("apptools/stats")
        .then(function onError(response) {
          $scope.backendStatus = response.data;
        })
      };
      getBackendStatus();

      // poll app status
      var pollBackendStatus = function () {
        $interval(function () {
          getBackendStatus();
        }, 1000)
      };
      pollBackendStatus();

      // cancel sync on page exit
      $scope.$on('$destroy', function () {
        $interval.cancel(pollBackendStatus);
      });
    })

.config(function ($mdThemingProvider) {
  $mdThemingProvider.theme('customTheme')
  .primaryPalette('red')
  .accentPalette('red')
  .warnPalette('red');
});

depliFrontend
.service('jmxNodeService', function ($location) {
  var jmxNodeId = undefined;
  var nodeName = "";

  return {
    selectJmxNode: function (_jmxNodeId) {
      jmxNodeId = _jmxNodeId;
      $location.url("/instance");
    },

    getSelectedNode: function () {
      return jmxNodeId;
    },

    setNodeName: function (_nodeName) {
      nodeName = _nodeName;
    },

    getNodeName: function () {
      return nodeName;
    }
  }
});
