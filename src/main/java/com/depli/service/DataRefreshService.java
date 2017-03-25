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

    // update node data objects
    @Async
    public void refreshNodeData(long nodeId) {
        nodeDataMap.getByNodeId(nodeId).refreshData();
    }

    // Iterate through data map
    @Async
    public void iterateAndRefreshNodeDataMap() throws InterruptedException {
        while (true) {
            for(Map.Entry<Long, NodeData> nodeDataEntry : nodeDataMap.getNodeDataMap().entrySet()) {
                if(nodeDataEntry.getValue().isInitialized()) {
                    this.refreshNodeData(nodeDataEntry.getKey());
                }
            }

            Thread.sleep(1000);
        }
    }
}
