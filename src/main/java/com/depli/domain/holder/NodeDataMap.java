package com.depli.domain.holder;

import java.util.HashMap;
import java.util.Map;

/**
 * NodeDataMap
 * observers object list.
 * <p>
 * Created by lpsandaruwan on 3/23/17.
 */

public class NodeDataMap {

    private boolean isInitialized;
    private Map<Long, NodeData> nodeDataMap = new HashMap<Long, NodeData>();

    public NodeDataMap() {
        this.isInitialized = false;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean initialized) {
        this.isInitialized = initialized;
    }

    public Map<Long, NodeData> getNodeDataMap() {
        return nodeDataMap;
    }

    public NodeData getByNodeId(long nodeId) {
        return nodeDataMap.get(nodeId);
    }

    public NodeData addNewNode(long nodeId, NodeData nodeData) {
        nodeDataMap.put(nodeId, nodeData);
        return this.getByNodeId(nodeId);
    }

    public NodeData removeByNodeId(long nodeId) {
        return nodeDataMap.remove(nodeId);
    }
}
