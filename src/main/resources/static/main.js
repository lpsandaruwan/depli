/**
 * Created by lpsandaruwan on 9/24/17.
 */

depliFrontend
.controller("mainController",
    function ($http, $interval, $location, $mdDialog, $rootScope, $scope,
        $window, JMXNodeService) {
      // page animation helper
      $scope.pageClass = 'page-dashboard';

      // backend status
      // -1 while rebooting, 0 while initializing store, 1 while running
      $scope.backendStatus = 1;

      // set toolbar header
      var setToolbarHeader = function () {
        $rootScope.toolbarHeader = JMXNodeService.getNodeName();
      };
      setToolbarHeader();

      // reload and reinitialize backend store and connections
      $rootScope.reloadBackend = function () {
        $http.get("apptools/reboot")
        .then(function onSuccess(response) {
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
        JMXNodeService.getAll()
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
        JMXNodeService.setNodeName("");
        setToolbarHeader();
        $location.url("/");
      };

      // display jmx node in detailed view
      $scope.gotoInstanceView = function (jmxNode) {
        JMXNodeService.setNodeName(
            "> " + jmxNode.nodeName);
        setToolbarHeader();
        JMXNodeService.selectJmxNode(jmxNode.nodeId);
      };

      // redirect to settings view
      $scope.gotoSettingsView = function () {
        setToolbarHeader();
        $location.url("/settings");
      };

      // cancel sync on page exit
      $scope.$on('$destroy', function () {
        $interval.cancel(pollBackendStatus);
      });
    });