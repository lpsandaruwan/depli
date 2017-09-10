package com.depli.utility.component.impl;

import com.depli.store.cache.holder.NodeData;
import com.depli.store.cache.service.CacheManagerService;
import com.depli.store.persistent.entity.JMXNode;
import com.depli.store.persistent.service.JMXNodeService;
import com.depli.utility.component.DataInitializerComponent;
import com.depli.utility.observer.JMXConnectionObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.depli.DepliApplication.*;

/**
 * DataInitializerComponent
 * Initialize node connections list and MX bean store objects
 * <p>
 * Created by lpsandaruwan on 3/24/17.
 */

@Component
public class DataInitializerComponentImpl implements DataInitializerComponent {

    @Autowired
    private CacheManagerService cacheManagerService;

    // Create object hash map filling it with NodeData objects
    public void initializeDJMXNodeConnections(JMXNodeService jmxNodeService) throws Exception {

        for (JMXNode jmxNode : jmxNodeService.findAll()) {
            JMXConnectionObserver jmxConnectionObserver = new JMXConnectionObserver(jmxNode);
            jmxConnectionObserver.getConnection();

            nodeDataMap.addNewNode(jmxNode.getNodeId(), new NodeData(jmxConnectionObserver));
            Thread.sleep(2000);
        }

        nodeDataMap.setInitialized(true);
    }

    // Initialize MXBean store objects inside store objects
    public void initializeMxBeanDataObjects() throws Exception {
        // Iterate node store map initialize MXBean store classes
        for (Map.Entry<Long, NodeData> nodeDataEntry : nodeDataMap.getNodeDataMap().entrySet()) {

            System.out.println("Initializing observer MXBean on nodeId: " + nodeDataEntry.getValue().getJmxConnectionObserver().getJmxNode().getNodeId());

            // Initialize observer ClassLoading MX bean store
            nodeDataEntry.getValue().getClassLoadingDataObserver().initialize();
            nodeDataEntry.getValue().getClassLoadingDataObserver().refreshData();

            cacheManagerService.getClassLoadingDataDescriptorCache().put(nodeDataEntry.getKey(), nodeDataEntry.getValue().getClassLoadingDataObserver().getClassLoadingData());

            // Initialize observer Memory MX bean store
            nodeDataEntry.getValue().getMemoryDataObserver().initialize();
            nodeDataEntry.getValue().getMemoryDataObserver().refreshData();

            // Initialize observer OperatingSystem MX bean store
            nodeDataEntry.getValue().getOperatingSystemDataObserver().initialize();
            nodeDataEntry.getValue().getOperatingSystemDataObserver().refreshData();

            // Initialize observer Runtime MX bean
            nodeDataEntry.getValue().getRuntimeDataObserver().initialize();
            nodeDataEntry.getValue().getRuntimeDataObserver().refreshData();

            // Initialize observer sun OperatingSystem MX bean
            nodeDataEntry.getValue().getPlatformSystemDataObserver().initialize();
            nodeDataEntry.getValue().getPlatformSystemDataObserver().refreshData();

            // Initialize observer Thread MX bean
            nodeDataEntry.getValue().getThreadDataObserver().initialize();
            nodeDataEntry.getValue().getThreadDataObserver().refreshData();

            // Update statistics store
            nodeDataEntry.getValue().updateStatisticsData();

            Thread.sleep(500);

            // mark node as initialized
            nodeDataEntry.getValue().setInitialized(true);
        }
    }

    // run store initializer methods in a loop considering triggers
    @Async
    public void pollInitializeData(JMXNodeService jmxNodeService) throws Exception {
        // noinspection InfiniteLoopStatement
        while (true) {
            if (rebootTrigger) {
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
