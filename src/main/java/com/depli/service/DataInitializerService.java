package com.depli.service;

import com.depli.data.NodeData;
import com.depli.data.NodeDataMap;
import com.depli.entity.JMXNode;
import com.depli.remote.DJMXConnection;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import static com.depli.DepliApplication.initializingFlag;
import static com.depli.DepliApplication.nodeDataMap;
import static com.depli.DepliApplication.rebootTrigger;

/** DataInitializerService
 * Initialize node connections list and MX bean data objects
 *
 * Created by lpsandaruwan on 3/24/17.
 */

@Service
public class DataInitializerService {

    public DataInitializerService() {}

    // Create object hash map filling it with NodeData objects
    public void initializeDJMXNodeConnections(JMXNodeService jmxNodeService) throws InterruptedException, IOException {
        // initialize new node data resource
        nodeDataMap = new NodeDataMap();

        for(JMXNode jmxNode : jmxNodeService.findAll()) {
            DJMXConnection djmxConnection = new DJMXConnection(jmxNode);
            djmxConnection.getConnection();

            nodeDataMap.addNewNode(jmxNode.getNodeId(), new NodeData(djmxConnection));
            Thread.sleep(2000);
        }

        nodeDataMap.setInitialized(true);
    }

    // Initialize MXBean data objects inside data objects
    public void initializeMxBeanDataObjects() throws InterruptedException, IOException {
        // Iterate node data map initialize MXBean data classes
        for(Map.Entry<Long, NodeData> nodeDataEntry : nodeDataMap.getNodeDataMap().entrySet()) {

            System.out.println("Initializing remote MXBean on nodeId: " + nodeDataEntry.getValue().getDjmxConnection().getJmxNode().getNodeId());

            // Initialize remote ClassLoading MX bean data
            nodeDataEntry.getValue().getdClassLoadingMXBean().initialize();
            nodeDataEntry.getValue().getdClassLoadingMXBean().refreshData();

            // Initialize remote Memory MX bean data
            nodeDataEntry.getValue().getdMemoryMXBean().initialize();
            nodeDataEntry.getValue().getdMemoryMXBean().refreshData();

            // Initialize remote OperatingSystem MX bean data
            nodeDataEntry.getValue().getdOperatingSystemMXBean().initialize();
            nodeDataEntry.getValue().getdOperatingSystemMXBean().refreshData();

            // Initialize remote Runtime MX bean
            nodeDataEntry.getValue().getdRuntimeMXBean().initialize();
            nodeDataEntry.getValue().getdRuntimeMXBean().refreshData();

            // Initialize remote sun OperatingSystem MX bean
            nodeDataEntry.getValue().getDpeOperatingSystemMXBean().initialize();
            nodeDataEntry.getValue().getDpeOperatingSystemMXBean().refreshData();

            // Initialize remote Thread MX bean
            nodeDataEntry.getValue().getdThreadMXBean().initialize();
            nodeDataEntry.getValue().getdThreadMXBean().refreshData();

            // Update statistics data
            nodeDataEntry.getValue().updateStatisticsData();

            Thread.sleep(500);

            // mark node as initialized
            nodeDataEntry.getValue().setInitialized(true);
        }
    }

    // run data initializer methods in a loop considering triggers
    @Async
    public void pollInitializeData(JMXNodeService jmxNodeService) throws IOException, InterruptedException {
        while(true) {
            if(rebootTrigger) {
                rebootTrigger = false;
                initializingFlag = true;

                initializeDJMXNodeConnections(jmxNodeService);
                initializeMxBeanDataObjects();

                Thread.sleep(1000);
                initializingFlag = false;
            }

            Thread.sleep(1000);
        }
    }
}
