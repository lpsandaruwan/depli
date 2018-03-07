/**
 * Created by lpsandaruwan on 3/31/17.
 */

var settingsModule = angular.module('settingsModule', []);

settingsModule
.controller("settingsModule",
    function ($http, $interval, $mdDialog, $rootScope, $route, $scope, $timeout,
        JMXNodeService) {
      // page animation helper
      $scope.pageClass = "page-settings";

      // node list
      $scope.jmxNodeList = {};
      $scope.isNullList = true;
      $scope.errorMsg = "";

      // set toolbar header
      var setToolbarHeader = function () {
        JMXNodeService.setNodeName("> SETTINGS");
        $rootScope.toolbarHeader = JMXNodeService.getNodeName();
      };
      setToolbarHeader();

      // get node list
      var getJmxNodeList = function () {

        JMXNodeService.getAll()
        .then(function onSuccess(response) {
          $scope.jmxNodeList = response.data;

          if (response.data.length !== 0) {
            $scope.isNullList = false;
          }

          else {
            $scope.isNullList = true;
            $scope.errorMsg = "Empty list";
          }
        })
        .catch(function onError(response) {
          $scope.isNullList = true;
          $scope.errorMsg = "Connection error!, Status code: "
              + response.status;
        })
      };
      getJmxNodeList();

      // add new node md dialog controller
      var addNodeController = function ($http, $mdDialog, $scope) {
        $scope.errorResponse = {};
        $scope.errorResponse.status = false;
        $scope.inProgress = false;

        // new node store
        $scope.jmxNode = {};

        $scope.jmxNode.authId = undefined;
        $scope.jmxNode.nodeName = undefined;
        $scope.jmxNode.serviceUrl = undefined;
        $scope.jmxNode.authRequired = false;
        $scope.jmxNode.username = null;
        $scope.jmxNode.password = null;

        // close md dialog
        $scope.cancel = function () {
          $mdDialog.cancel();
        };

        // save node store
        var saveNodeData = function () {
          JMXNodeService.saveNew($scope.jmxNode)
          .then(function onSuccess() {
            $scope.inProgress = false;
            $mdDialog.hide();
          })
          .catch(function onError(response) {
            $scope.inProgress = false;
            $scope.errorResponse.status = true;
            $scope.errorResponse.error = "Error: " + response.data.error;
          })
        };

        $scope.saveData = function () {
          $scope.inProgress = true;
          saveNodeData();
        };
      };

      // add new node function
      $scope.addNewNode = function (ev) {
        $mdDialog.show({
          controller: addNodeController,
          templateUrl: "view/settings/node_data.html",
          parent: angular.element(document.body),
          targetEvent: ev,
          clickOutsideToClose: true,
          fullscreen: $scope.customFullscreen
        })
        .then(function () {
          getJmxNodeList();
          $rootScope.reloadBackend();
        })
      };

      // edit node md dialog controller
      var editNodeController = function ($http, $mdDialog, $scope, jmxNodeId) {
        $scope.errorResponse = {};
        $scope.errorResponse.status = false;
        $scope.inProgress = false;

        // get node store
        var getJmxNodeData = function () {
          JMXNodeService.getByNodeId(jmxNodeId)
          .then(function onSuccess(response) {
            $scope.jmxNode = response.data;
          })
          .catch(function onError(response) {
            $scope.errorResponse.status = true;
            $scope.errorResponse.error = "Error: " + response.data.error;
          })
        };
        getJmxNodeData();

        // close md dialog
        $scope.cancel = function () {
          $mdDialog.cancel();
        };

        // save node store
        var saveNodeData = function () {
          JMXNodeService.updateByNodeId(jmxNodeId, $scope.jmxNode)
          .then(function onSuccess() {
            $scope.inProgress = false;
            $mdDialog.hide();
          })
          .catch(function onError(response) {
            $scope.inProgress = false;
            $scope.errorResponse.status = true;
            $scope.errorResponse.error = "Error: " + response.data.error;
          })
        };

        $scope.saveData = function () {
          $scope.inProgress = true;
          saveNodeData();
        };
      };

      // edit node store function
      $scope.editNewNode = function (ev, _jmxNodeId) {
        $mdDialog.show({
          controller: editNodeController,
          templateUrl: "view/settings/node_data.html",
          parent: angular.element(document.body),
          targetEvent: ev,
          clickOutsideToClose: true,
          fullscreen: $scope.customFullscreen,
          locals: {
            jmxNodeId: _jmxNodeId
          }
        })
        .then(function () {
          getJmxNodeList();
          $rootScope.reloadBackend();
        })
      };

      // delete a node
      $scope.deleteNode = function (ev, jmxNodeId) {
        var confirm = $mdDialog.confirm()
        .title("Delete node ?")
        .textContent(
            "You won't be able to monitor the node after rebooting depli backend.")
        .ariaLabel('deleteNode')
        .targetEvent(ev)
        .ok("Delete")
        .cancel("Cancel");

        $mdDialog.show(confirm)
        .then(function () {
          JMXNodeService.deleteByNodeId(jmxNodeId)
          .then(function onSuccess(response) {
            if (response.data === true) {
              $mdDialog.hide();
            }
          })
          .catch(function onError(response) {
            $scope.errorResponse.error = "Error: " + response.data.error;
          });

          $timeout(function () {
            getJmxNodeList();
            $rootScope.reloadBackend();
          }, 1000);
        })

      };
    });
