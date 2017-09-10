package com.depli.utility.components.implementations;

import com.depli.domain.holder.NodeData;
import com.depli.domain.holder.NodeDataMap;
import com.depli.domain.persistent.entity.JMXNode;
import com.depli.service.JMXNodeService;
import com.depli.utility.components.DataInitializerComponent;
import com.depli.utility.observers.JMXConnectionObserver;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.depli.DepliApplication.*;

/**
 * DataInitializerComponent
 * Initialize node connections list and MX bean domain objects
 * <p>
 * Created by lpsandaruwan on 3/24/17.
 */

@Component
public class DataInitializerComponentImplementation implements DataInitializerComponent {

    private static final Logger logger = Logger.getLogger(DataInitializerComponentImplementation.class);

    // Create object hash map filling it with NodeData objects
    public void initializeDJMXNodeConnections(JMXNodeService jmxNodeService) throws Exception {
        // initialize new node domain resource
        nodeDataMap = new NodeDataMap();

        for (JMXNode jmxNode : jmxNodeService.findAll()) {
            JMXConnectionObserver JMXConnectionObserverImplementationObserverComponent = new JMXConnectionObserver(jmxNode);
            JMXConnectionObserverImplementationObserverComponent.getConnection();

            nodeDataMap.addNewNode(jmxNode.getNodeId(), new NodeData(JMXConnectionObserverImplementationObserverComponent));
            Thread.sleep(2000);
        }

        nodeDataMap.setInitialized(true);
    }

    // Initialize MXBean domain objects inside domain objects
    public void initializeMxBeanDataObjects() throws Exception {
        // Iterate node domain map initialize MXBean domain classes
        for (Map.Entry<Long, NodeData> nodeDataEntry : nodeDataMap.getNodeDataMap().entrySet()) {

            System.out.println("Initializing observers MXBean on nodeId: " + nodeDataEntry.getValue().getJMXConnectionObserver().getJmxNode().getNodeId());

            // Initialize observers ClassLoading MX bean domain
            nodeDataEntry.getValue().getClassLoadingDataObserver().initialize();
            nodeDataEntry.getValue().getClassLoadingDataObserver().refreshData();

            // Initialize observers Memory MX bean domain
            nodeDataEntry.getValue().getMemoryDataObserver().initialize();
            nodeDataEntry.getValue().getMemoryDataObserver().refreshData();

            // Initialize observers OperatingSystem MX bean domain
            nodeDataEntry.getValue().getOperatingSystemDataObserver().initialize();
            nodeDataEntry.getValue().getOperatingSystemDataObserver().refreshData();

            // Initialize observers Runtime MX bean
            nodeDataEntry.getValue().getRuntimeDataObserver().initialize();
            nodeDataEntry.getValue().getRuntimeDataObserver().refreshData();

            // Initialize observers sun OperatingSystem MX bean
            nodeDataEntry.getValue().getPlatformSystemDataObserver().initialize();
            nodeDataEntry.getValue().getPlatformSystemDataObserver().refreshData();

            // Initialize observers Thread MX bean
            nodeDataEntry.getValue().getThreadDataObserver().initialize();
            nodeDataEntry.getValue().getThreadDataObserver().refreshData();

            // Update statistics domain
            nodeDataEntry.getValue().updateStatisticsData();

            Thread.sleep(500);

            // mark node as initialized
            nodeDataEntry.getValue().setInitialized(true);
        }
    }

    // run domain initializer methods in a loop considering triggers
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
