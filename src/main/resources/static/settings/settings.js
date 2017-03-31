/**
 * Created by lpsandaruwan on 3/31/17.
 */

var settingsModule = angular.module("settingsModule", []);


settingsModule
    .controller("settingsModule", function ($http, $interval, $mdDialog, $rootScope, $scope, $timeout, jmxNodeService) {
        // page animation helper
        $scope.pageClass = "page-settings";


        // node list
        $scope.jmxNodeList = {};
        $scope.jmxNodeList.isNull = true;


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
                    if (response.data !== null) {
                        $scope.jmxNodeList.isNull = false;
                        $scope.jmxNodeList = response.data;
                    }

                    else {
                        $scope.jmxNodeList.isNull = true;
                        $scope.jmxNodeList.error = "Empty list";
                    }
                })
                .catch(function onError(response) {
                    $scope.jmxNodeList.isNull = true;
                    $scope.error = "Connection error!, Status code: " + response.status;
                })
        };
        getJmxNodeList();


        // add new node function
        $scope.addNewNode = function (ev) {
            $mdDialog.show({
                controller: addNodeController,
                templateUrl: "settings/new_node.html",
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose: true,
                fullscreen: $scope.customFullscreen
            })
                .then(function () {
                })
        };


        // add new node md dialog controller
        var addNodeController = function ($http, $mdDialog, $scope, $timeout) {
            $scope.errorResponse = {};
            $scope.errorResponse.status = false;

            // new node data
            $scope.authData = {};
            $scope.jmxNode = {};

            $scope.authData.username = undefined;
            $scope.authData.password = undefined;

            $scope.jmxNode.authId = undefined;
            $scope.jmxNode.nodeName = undefined;
            $scope.jmxNode.hostname = undefined;
            $scope.jmxNode.port = undefined;
            $scope.jmxNode.authRequied = false;
            $scope.jmxNode.sslRequired = false;

            // close md dialog
            $scope.cancel = function() {
                $mdDialog.cancel();
            };

            // save and get auth data ids
            var saveAuthData = function () {
                $http.post("nodes/auth", $scope.authData)
                    .then(function onSuccess(response) {
                        $scope.jmxNode.authId = response.data;
                    })
                    .catch(function onError(response) {
                        $scope.errorResponse.status = true;
                        $scope.errorResponse.error = "error, response code: " + response.status;
                    })
            };

            // save node data
            var saveNodeData = function () {
                $http.post("nodes/new", $scope.jmxNode)
                    .then(function onSuccess(response) {
                    })
                    .catch(function onError(response) {
                        $scope.errorResponse.status = true;
                        $scope.errorResponse.error = "error, response code: " + response.status;
                    })
            };

            $scope.saveData = function () {
                if($scope.jmxNode.authRequied) {
                    saveAuthData();

                    $timeout(function () {
                        saveNodeData();
                    }, 1000)
                }
            };
        };
    });