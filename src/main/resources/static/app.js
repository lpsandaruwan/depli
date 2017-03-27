/**
 * Created by lpsandaruwan on 3/21/17.
 */

var depliFrontend = angular.module("depliFrontend", [
    "chart.js",
    "homeViewModule",
    "instanceViewModule",
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

            .when("/instance", {
                templateUrl: "instance/instance.html",
                controller: "instanceViewModule"
            })

            .otherwise("/");
    });


depliFrontend
    .controller("mainController", function ($http, $scope) {
        $scope.jmxNodeList = [];

        // obtain jmx node list
        var getJmxNodeList = function () {
            $http.get("nodes")
                .then(function onSucces(response) {
                    $scope.jmxNodeList = response.data;
                })
                .catch(function onError() {

                });
        };

        // refresh scope data
        getJmxNodeList();
    })

    .config(function($mdThemingProvider) {
        $mdThemingProvider.theme('customTheme')
            .primaryPalette('red')
            .accentPalette('red')
            .warnPalette('red');
    });