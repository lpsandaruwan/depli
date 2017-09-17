package com.depli.utility.component.impl;

import com.depli.utility.component.DataRefresherComponent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.depli.DepliApplication.nodeDataMap;

/**
 * DataRefresherComponent
 * Poll refresh and update MXBean store in node store map periodically.
 * Created by lpsandaruwan on 3/25/17.
 */

@Component
public class DataRefresherComponentImpl implements DataRefresherComponent {

    // update instant node store objects
    @Async
    public void refreshInstantNodeData(long nodeId) {
        nodeDataMap.getByNodeId(nodeId).refreshInstantData();
    }

    // update rarely update store
    @Async
    public void refreshNodeData(long nodeId) {
        nodeDataMap.getByNodeId(nodeId).refreshData();
    }

    // Iterate through store map and refresh instant store
    @Async
    public void iterateAndRefreshInstantNodeDataMap() throws InterruptedException {
        //noinspection InfiniteLoopStatement
        while (true) {
            if (nodeDataMap.getNodeDataMap() == null) {
                continue;
            }
            for (Map.Entry<Long, NodeData> nodeDataEntry : nodeDataMap.getNodeDataMap().entrySet()) {
                if (nodeDataEntry.getValue().isInitialized()) {
                    this.refreshInstantNodeData(nodeDataEntry.getKey());
                }
            }

            Thread.sleep(1000);
        }
    }

    // Iterate through store map and refresh rarely updated store
    @Async
    public void iterateAndRefreshNodeDataMap() throws InterruptedException {
        //noinspection InfiniteLoopStatement
        while (true) {
            if (nodeDataMap.getNodeDataMap().entrySet() == null) {
                continue;
            }
            for (Map.Entry<Long, NodeData> nodeDataEntry : nodeDataMap.getNodeDataMap().entrySet()) {
                if (nodeDataEntry.getValue().isInitialized()) {
                    this.refreshNodeData(nodeDataEntry.getKey());
                }
            }

            Thread.sleep(60 * 1000);
        }
    }
}
