/**
 * Created by lpsandaruwan on 3/21/17.
 */

var depliFrontend = angular.module("depliFrontend", [
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
    .controller("mainController", function () {

    });