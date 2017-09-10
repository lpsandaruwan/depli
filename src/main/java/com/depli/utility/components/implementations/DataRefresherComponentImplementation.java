package com.depli.utility.components.implementations;

import com.depli.domain.holder.NodeData;
import com.depli.utility.components.DataRefresherComponent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.depli.DepliApplication.nodeDataMap;

/**
 * DataRefresherComponent
 * Poll refresh and update MXBean domain in node domain map periodically.
 * Created by lpsandaruwan on 3/25/17.
 */

@Component
public class DataRefresherComponentImplementation implements DataRefresherComponent {

    // update instant node domain objects
    @Async
    public void refreshInstantNodeData(long nodeId) {
        nodeDataMap.getByNodeId(nodeId).refreshInstantData();
    }

    // update rarely update domain
    @Async
    public void refreshNodeData(long nodeId) {
        nodeDataMap.getByNodeId(nodeId).refreshData();
        ;
    }

    // Iterate through domain map and refresh instant domain
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

    // Iterate through domain map and refresh rarely updated domain
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
