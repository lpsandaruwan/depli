/**
 * Created by lpsandaruwan on 3/21/17.
 */

var depliFrontend = angular.module("depliFrontend", [
  'chart.js',
  'homeViewModule',
  'instanceViewModule',
  'ngAnimate',
  'ngMessages',
  'ngMaterial',
  'ngRoute',
  'settingsModule',
  'JMXNodeServiceModule',
  'ClassLoadingDataServiceModule',
  'GraphDataServiceModule',
  'MemoryDataServiceModule',
  'OperatingSystemDataServiceModule',
  'PlatformResourcesDataServiceModule',
  'RuntimeDataServiceModule',
  'ThreadDataServiceModule'
]);

depliFrontend
.config(
    function ($httpProvider, $locationProvider, $routeProvider, $qProvider) {

      $routeProvider
      .when("/", {
        templateUrl: "view/home/home.html",
        controller: "homeViewModule"
      })

      .when("/instance/", {
        templateUrl: "view/instance/instance.html",
        controller: "statisticsController"
      })

      .when("/settings", {
        templateUrl: "view/settings/settings.html",
        controller: "settingsModule"
      })

      .otherwise("/");

      $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
      $qProvider.errorOnUnhandledRejections(false);
    });

depliFrontend
.config(function ($mdThemingProvider) {
  $mdThemingProvider.theme('customTheme')
  .primaryPalette('blue')
  .accentPalette('blue')
  .warnPalette('blue');
});
