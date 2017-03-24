package com.depli.service;

import com.depli.remote.DJMXConnection;
import com.depli.data.NodeData;
import com.depli.entity.JMXNode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import static com.depli.DepliApplication.nodeDataMap;

/** DataInitializerService
 * Initialize node connections list and MX bean data objects
 *
 * Created by lpsandaruwan on 3/24/17.
 */

@Service
public class DataInitializerService {

    public DataInitializerService() {}

    // Create object hashmap filling it with NodeData objects
    @Async
    public void initializeDJMXNodeConnections(JMXNodeService jmxNodeService) throws InterruptedException, IOException {
        Thread.sleep(1000);

        for(JMXNode jmxNode : jmxNodeService.findAll()) {
            DJMXConnection djmxConnection = new DJMXConnection(jmxNode);
            djmxConnection.getConnection();

            nodeDataMap.addNewNode(jmxNode.getNodeId(), new NodeData(djmxConnection));
            Thread.sleep(500);
        }

        nodeDataMap.setInitialized(true);
    }

    // Initialize MXBean data objects inside data objects if nodedata map is initialized
    @Async
    public void initializeMxBeanDataObjects() throws InterruptedException, IOException {
        while(!nodeDataMap.isInitialized()) {
            System.out.println("Data hash map not initialized yet.");
            Thread.sleep(1000);
        }

        // Iterate node data map initialize MXBean data classes
        for(Map.Entry<Long, NodeData> nodeDataEntry : nodeDataMap.getNodeDataMap().entrySet()) {

            System.out.println("Initializing remote MXBean on nodeId: " + nodeDataEntry.getValue().getDjmxConnection().getJmxNode().getNodeId());

            // Initialize remote ClassLoading MX bean
            nodeDataEntry.getValue().getdClassLoadingMXBean().initialize();

            // Initialize remote Memory MX bean
            nodeDataEntry.getValue().getdMemoryMXBean().initialize();

            // Initialize remote OperatingSystem MX bean
            nodeDataEntry.getValue().getdOperatingSystemMXBean().initialize();

            // Initialize remote Runtime MX bean
            nodeDataEntry.getValue().getdRuntimeMXBean().initialize();

            // Initialize remote Thread MX bean
            nodeDataEntry.getValue().getdThreadMXBean().initialize();

            Thread.sleep(500);

            // mark node as initialized
            nodeDataEntry.getValue().setInitialized(true);
        }
    }
}
