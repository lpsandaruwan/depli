/**
 * Created by lpsandaruwan on 9/23/17.
 */

var GraphDataServiceModule = angular.module('GraphDataServiceModule', []);

GraphDataServiceModule.service('GraphDataService',
    function ($http) {

      return {
        getClassGraphDataByDescriptorId: function (descriptorId) {
          return $http.get("/graphs/classes/" + descriptorId)
        },

        getCpuGraphDataByDescriptorId: function (descriptorId) {
          return $http.get("/graphs/mainframes/" + descriptorId)
        }
      }
    });