/**
 * Created by lpsandaruwan on 9/23/17.
 */

var PlatformResourcesDataServiceModule = angular.module(
    'PlatformResourcesDataServiceModule', []);

PlatformResourcesDataServiceModule.service('PlatformResourcesDataService',
    function ($http) {

      return {
        getByDescriptorId: function (descriptorId) {
          return $http.get(
              "/descriptors/platforms/" + descriptorId + "/dynamics")
        }
      }
    });