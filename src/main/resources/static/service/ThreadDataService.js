/**
 * Created by lpsandaruwan on 9/23/17.
 */

var ThreadDataServiceModule = angular.module('ThreadDataServiceModule', []);

ThreadDataServiceModule.service('ThreadDataService',
    function ($http) {

      return {
        getCountsByDescriptorId: function (descriptorId) {
          return $http.get(
              "/descriptors/threads/" + descriptorId + "/dynamics/counts")
        },

        getDumpsByDescriptorId: function (descriptorId) {
          return $http.get(
              "/descriptors/threads/" + descriptorId + "/dynamics/dumps")
        }
      }
    });