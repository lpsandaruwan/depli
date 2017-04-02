package com.depli.service;

import com.depli.data.NodeData;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.depli.DepliApplication.nodeDataMap;

/** DataRefreshService
 * Poll refresh and update MXBean data in node data map periodically.
 * Created by lpsandaruwan on 3/25/17.
 */

@Service
public class DataRefreshService {

    // update instant node data objects
    @Async
    public void refreshInstantNodeData(long nodeId) {
        nodeDataMap.getByNodeId(nodeId).refreshInstantData();
    }

    // update rarely update data
    @Async
    public void refreshNodeData(long nodeId) {
        nodeDataMap.getByNodeId(nodeId).refreshData();;
    }

    // Iterate through data map and refresh instant data
    @Async
    public void iterateAndRefreshInstantNodeDataMap() throws InterruptedException {
        while (true) {
            for(Map.Entry<Long, NodeData> nodeDataEntry : nodeDataMap.getNodeDataMap().entrySet()) {
                try {
                    if(nodeDataEntry.getValue().isInitialized() && nodeDataEntry.getValue().isReachable()) {
                        this.refreshInstantNodeData(nodeDataEntry.getKey());
                    }
                }

                catch (Exception ex) {
                    System.out.println("Seems like JMX connection on nodeId: " + nodeDataEntry.getValue().getDjmxConnection().getJmxNode().getNodeId() + "is dead.");
                    nodeDataEntry.getValue().falseAllInitializedData();
                }
            }

            Thread.sleep(1000);
        }
    }

    // Iterate through data map and refresh rarely updated data
    @Async
    public void iterateAndRefreshNodeDataMap() throws InterruptedException {
        while (true) {
            for(Map.Entry<Long, NodeData> nodeDataEntry : nodeDataMap.getNodeDataMap().entrySet()) {
                if(nodeDataEntry.getValue().isInitialized()) {
                    this.refreshNodeData(nodeDataEntry.getKey());
                }
            }

            Thread.sleep( 60 * 1000);
        }
    }
}
