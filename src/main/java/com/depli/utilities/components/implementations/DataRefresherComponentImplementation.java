package com.depli.utilities.components.implementations;

import com.depli.utilities.components.DataRefresherComponent;
import com.depli.data.NodeData;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.depli.DepliApplication.nodeDataMap;

/**
 * DataRefresherComponent
 * Poll refresh and update MXBean data in node data map periodically.
 * Created by lpsandaruwan on 3/25/17.
 */

@Component
public class DataRefresherComponentImplementation implements DataRefresherComponent {

    // update instant node data objects
    @Async
    public void refreshInstantNodeData(long nodeId) {
        nodeDataMap.getByNodeId(nodeId).refreshInstantData();
    }

    // update rarely update data
    @Async
    public void refreshNodeData(long nodeId) {
        nodeDataMap.getByNodeId(nodeId).refreshData();
        ;
    }

    // Iterate through data map and refresh instant data
    @Async
    public void iterateAndRefreshInstantNodeDataMap() throws InterruptedException {
        //noinspection InfiniteLoopStatement
        while (true) {
            for (Map.Entry<Long, NodeData> nodeDataEntry : nodeDataMap.getNodeDataMap().entrySet()) {
                if (nodeDataEntry.getValue().isInitialized()) {
                    this.refreshInstantNodeData(nodeDataEntry.getKey());
                }
            }

            Thread.sleep(1000);
        }
    }

    // Iterate through data map and refresh rarely updated data
    @Async
    public void iterateAndRefreshNodeDataMap() throws InterruptedException {
        //noinspection InfiniteLoopStatement
        while (true) {
            for (Map.Entry<Long, NodeData> nodeDataEntry : nodeDataMap.getNodeDataMap().entrySet()) {
                if (nodeDataEntry.getValue().isInitialized()) {
                    this.refreshNodeData(nodeDataEntry.getKey());
                }
            }

            Thread.sleep(60 * 1000);
        }
    }
}
