/**
 * Created by lpsandaruwan on 9/23/17.
 */

var OperatingSystemDataServiceModule = angular.module(
    'OperatingSystemDataServiceModule', []);

OperatingSystemDataServiceModule.service('OperatingSystemDataService',
    function ($http) {

      return {
        getByDescriptorId: function (descriptorId) {
          return $http.get("/descriptors/systems/" + descriptorId + "/statics")
        }
      }
    });