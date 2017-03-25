/**
 * Controller for frontend dashboard
 * Created by lpsandaruwan on 3/21/17.
 */

var homeViewModule = angular.module("homeViewModule", []);


homeViewModule
    .controller("homeViewModule", function ($http, $scope) {
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
    });