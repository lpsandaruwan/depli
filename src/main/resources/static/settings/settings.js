/**
 * Created by lpsandaruwan on 3/31/17.
 */

var settingsModule = angular.module("settingsModule", []);


settingsModule
    .controller("settingsModule", function ($http, $interval, $mdDialog, $rootScope, $route, $scope, $timeout, jmxNodeService) {
        // page animation helper
        $scope.pageClass = "page-settings";


        // node list
        $scope.jmxNodeList = {};
        $scope.isNullList = true;
        $scope.errorMsg = "";


        // set toolbar header
        var setToolbarHeader = function () {
            jmxNodeService.setNodeName("> SETTINGS");

            $rootScope.toolbarHeader = jmxNodeService.getNodeName();
        };
        setToolbarHeader();


        // get node list
        var getJmxNodeList = function () {
            $http.get("nodes")
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
                    $scope.errorMsg = "Connection error!, Status code: " + response.status;
                })
        };
        getJmxNodeList();


        // add new node md dialog controllers
        var addNodeController = function ($http, $mdDialog, $scope) {
            $scope.errorResponse = {};
            $scope.errorResponse.status = false;
            $scope.inProgress = false;

            // new node data
            $scope.jmxNode = {};

            $scope.jmxNode.authId = undefined;
            $scope.jmxNode.nodeName = undefined;
            $scope.jmxNode.hostname = undefined;
            $scope.jmxNode.port = undefined;
            $scope.jmxNode.authRequired = false;
            $scope.jmxNode.sslRequired = false;
            $scope.jmxNode.username = null;
            $scope.jmxNode.password = null;

            // close md dialog
            $scope.cancel = function () {
                $mdDialog.cancel();
            };

            // save node data
            var saveNodeData = function () {
                $http.post("nodes/save", $scope.jmxNode)
                    .then(function onSuccess() {
                        $scope.inProgress = false;
                        $mdDialog.hide();
                    })
                    .catch(function onError(response) {
                        $scope.inProgress = false;
                        $scope.errorResponse.status = true;
                        $scope.errorResponse.error = "error, response code: " + response.status;
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
                templateUrl: "settings/node_data.html",
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


        // edit node md dialog controllers
        var editNodeController = function ($http, $mdDialog, $scope, jmxNodeId) {
            $scope.errorResponse = {};
            $scope.errorResponse.status = false;
            $scope.inProgress = false;

            // get node data
            var getJmxNodeData = function () {
                $http.get("nodes/" + jmxNodeId)
                    .then(function onSuccess(response) {
                        $scope.jmxNode = response.data;
                    })
                    .catch(function onError(response) {
                        $scope.errorResponse.status = false;
                        $scope.errorResponse.error = "error getting node data, response code: " + response.status;
                    })
            };
            getJmxNodeData();

            // close md dialog
            $scope.cancel = function () {
                $mdDialog.cancel();
            };

            // save node data
            var saveNodeData = function () {
                $http.post("nodes/save", $scope.jmxNode)
                    .then(function onSuccess() {
                        $scope.inProgress = false;
                        $mdDialog.hide();
                    })
                    .catch(function onError(response) {
                        $scope.inProgress = false;
                        $scope.errorResponse.status = true;
                        $scope.errorResponse.error = "error, response code: " + response.status;
                    })
            };

            $scope.saveData = function () {
                $scope.inProgress = true;
                saveNodeData();
            };
        };


        // edit node data function
        $scope.editNewNode = function (ev, _jmxNodeId) {
            $mdDialog.show({
                controller: editNodeController,
                templateUrl: "settings/node_data.html",
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
                .textContent("You won't be able to monitor the node after rebooting depli backend.")
                .ariaLabel('deleteNode')
                .targetEvent(ev)
                .ok("Delete")
                .cancel("Cancel");

            $mdDialog.show(confirm)
                .then(function () {
                    $http.delete("nodes/" + jmxNodeId)
                        .then(function onSuccess(response) {
                            if (response.data === true) {
                                $mdDialog.hide();
                            }
                        })
                        .catch(function onError(response) {
                            $scope.errorResponse.error = "error, response code: " + response.status;
                        });

                    $timeout(function () {
                        getJmxNodeList();
                        $rootScope.reloadBackend();
                    }, 1000);
                })

        };
    });
