/**
 * Created by lpsandaruwan on 9/23/17.
 */

var MemoryDataServiceModule = angular.module('MemoryDataServiceModule', []);

MemoryDataServiceModule.service('MemoryDataService',
    function ($http) {
      return {
        getByDescriptorId: function (descriptorId) {
          return $http.get(
              "/descriptors/memories/" + descriptorId + "/dynamics")
        }
      }
    });