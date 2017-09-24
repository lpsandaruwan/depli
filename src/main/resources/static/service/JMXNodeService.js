/**
 * Created by lpsandaruwan on 9/23/17.
 */

var JMXNodeServiceModule = angular.module('JMXNodeServiceModule', []);

JMXNodeServiceModule.service('JMXNodeService',
    function ($http, $location) {
      var selectedNodeId = null;
      var selectedNodeName = "";

      return {
        selectJmxNode: function (_selectedNodeId) {
          selectedNodeId = _selectedNodeId;
          $location.url("/instance");
        },

        getSelectedNodeId: function () {
          return selectedNodeId;
        },

        setNodeName: function (_nodeName) {
          selectedNodeName = _nodeName;
        },

        getNodeName: function () {
          return selectedNodeName;
        },

        getAll: function () {
          return $http.get("nodes")
        },

        getByNodeId: function (nodeId) {
          return $http.get("nodes/" + nodeId)
        },

        saveNew: function (requestData) {
          return $http.post("nodes", requestData)
        },

        updateByNodeId: function (nodeId, requestData) {
          return $http.put("nodes/" + nodeId, requestData)
        },

        deleteByNodeId: function (nodeId) {
          return $http.delete("nodes/" + nodeId)
        }
      }
    });