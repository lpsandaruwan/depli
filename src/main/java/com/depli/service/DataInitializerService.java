package com.depli.service;

import com.depli.data.NodeData;
import com.depli.data.NodeDataMap;
import com.depli.entity.JMXNode;
import com.depli.remote.DJMXConnection;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import static com.depli.DepliApplication.*;

/** DataInitializerService
 * Initialize node connections list and MX bean data objects
 *
 * Created by lpsandaruwan on 3/24/17.
 */

@Service
public class DataInitializerService {

    public DataInitializerService() {}

    // Create object hash map filling it with NodeData objects
    @Async
    public void initializeDJMXNodeConnections(JMXNodeService jmxNodeService) throws InterruptedException, IOException {

        while(true) {
            // initialize new node data resource map on reboot trigger
            if(rebootTrigger) {
                nodeDataMap = new NodeDataMap();

                // add nodes in database to data resource map
                for(JMXNode jmxNode : jmxNodeService.findAll()) {
                    DJMXConnection djmxConnection = new DJMXConnection(jmxNode);
                    nodeDataMap.addNewNode(jmxNode.getNodeId(), new NodeData(djmxConnection));

                    Thread.sleep(100);
                }

                nodeDataMap.setInitialized(true);
                rebootTrigger = false;
            }

            // iterate through node data map and initialize connection
            for(Map.Entry<Long, NodeData> nodeDataEntry : nodeDataMap.getNodeDataMap().entrySet()) {

                if(!nodeDataEntry.getValue().isReachable()) {

                    try {
                        nodeDataEntry.getValue().getDjmxConnection().getConnection();
                        nodeDataEntry.getValue().setReachable(true);
                    }

                    catch (Exception ex) {
                        System.out.println("Failed to reach JMX connection on nodeID: " + nodeDataEntry.getValue().getDjmxConnection().getJmxNode().getNodeId());
                    }
                }

                Thread.sleep(1000);
            };

            Thread.sleep(4000);
        }
    }

    // Initialize MXBean data objects inside data objects
    @Async
    public void initializeMxBeanDataObjects() throws InterruptedException, IOException {
        while (true) {
            // Iterate node data map initialize MXBean data classes
            for(Map.Entry<Long, NodeData> nodeDataEntry : nodeDataMap.getNodeDataMap().entrySet()) {

                if(!nodeDataEntry.getValue().isInitialized()) {

                    if( nodeDataEntry.getValue().isReachable()) {
                        initializingFlag = true;

                        System.out.println("Attempt to initialize MXBean proxy connections on nodeId: " + nodeDataEntry.getValue().getDjmxConnection().getJmxNode().getNodeId());

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

                        // mark node as initialized
                        nodeDataEntry.getValue().setInitialized(true);

                        System.out.println("Successfully initialized MXBean proxy connections on nodeId: " + nodeDataEntry.getValue().getDjmxConnection().getJmxNode().getNodeId());
                    }

                    else {
                        System.out.println("JMX remote connection is not reachable on nodeId: " + nodeDataEntry.getValue().getDjmxConnection().getJmxNode().getNodeId());
                    }

                    Thread.sleep(1000);
                }

                initializingFlag = false;
            }

            Thread.sleep(3000);
        }
    }
}
