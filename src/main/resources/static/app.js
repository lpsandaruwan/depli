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
    .config(function ($httpProvider, $locationProvider, $routeProvider, $qProvider) {

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

        $qProvider.errorOnUnhandledRejections(false);
    });


depliFrontend
    .controller("mainController", function ($http, $location, $mdDialog, $rootScope, $scope, jmxNodeService) {
        // page animation helper
        $scope.pageClass = 'page-dashboard';


        // set toolbar header
        var setToolbarHeader = function () {
            $rootScope.toolbarHeader = jmxNodeService.getNodeName();
        };
        setToolbarHeader();


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
        // refresh scope data
        getJmxNodeList();


        // redirect to dashboard view
        $scope.gotoDashboardView = function () {
            jmxNodeService.setNodeName("");
            setToolbarHeader();

            $location.url("/");
        };


        // display jmx node in detailed view
        $scope.gotoInstanceView = function (jmxNode) {
            jmxNodeService.setNodeName("> " + jmxNode.nodeName + "@" + jmxNode.hostname);
            setToolbarHeader();

            jmxNodeService.selectJmxNode(jmxNode.nodeId);
        };


        // redirect to settings view
        $scope.gotoSettingsView = function () {
            jmxNodeService.getNodeName("");
            setToolbarHeader();

            $location.url("/settings");
        };

    })


    .config(function($mdThemingProvider) {
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
