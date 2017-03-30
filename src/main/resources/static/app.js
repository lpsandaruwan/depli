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
    "ngRoute"
]);


depliFrontend
    .config(function ($httpProvider, $locationProvider, $routeProvider) {

        $routeProvider
            .when("/", {
                templateUrl: "home/home.html",
                controller: "homeViewModule"
            })

            .when("/instance/", {
                templateUrl: "instance/instance.html",
                controller: "instanceViewModule"
            })

            .otherwise("/");
    });


depliFrontend
    .controller("mainController", function ($http, $location, $rootScope, $scope, jmxNodeService) {
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
            jmxNodeService.setNodeName("DASHBOARD");
            setToolbarHeader();

            $location.url("/");
        };


        // display jmx node in detailed view
        $scope.gotoInstanceView = function (jmxNode) {
            jmxNodeService.setNodeName(jmxNode.nodeName + " - " + jmxNode.hostname);
            setToolbarHeader();

            jmxNodeService.selectJmxNode(jmxNode.nodeId);
        };


        // add new node function
        $scope.addNewNode = function () {

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
        var nodeName = "DASHBOARD";

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
