/**
 * Created by lpsandaruwan on 9/23/17.
 */

var ClassLoadingDataServiceModule = angular.module(
    'ClassLoadingDataServiceModule', []);

ClassLoadingDataServiceModule.service('ClassLoadingDataService',
    function ($http) {

      return {
        getByDescriptorId: function (descriptorId) {
          return $http.get("/descriptors/classes/" + descriptorId + "/dynamics")
        }
      }
    });