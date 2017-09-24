/**
 * Created by lpsandaruwan on 9/23/17.
 */

var RuntimeDataServiceModule = angular.module('RuntimeDataServiceModule', []);

RuntimeDataServiceModule.service('RuntimeDataService',
    function ($http) {

      return {
        getDynamicsByDescriptorId: function (descriptorId) {
          return $http.get(
              "/descriptors/runtimes/" + descriptorId + "/dynamics")
        },

        getStaticsByDescriptorId: function (descriptorId) {
          return $http.get("/descriptors/runtimes/" + descriptorId + "/statics")
        }
      }
    });