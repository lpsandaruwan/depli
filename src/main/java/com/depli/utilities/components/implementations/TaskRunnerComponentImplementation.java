package com.depli.utilities.components.implementations;

import com.depli.utilities.components.DataInitializerComponent;
import com.depli.utilities.components.DataRefresherComponent;
import com.depli.services.JMXNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * TaskRunner
 * Make connections to all the JMX nodes using services threads and keep node information updated by polling.
 * <p>
 * Created by lpsandaruwan on 3/22/17.
 */

@Component
public class TaskRunnerComponentImplementation implements CommandLineRunner {

    private final DataInitializerComponent dataInitializerComponent;
    private final DataRefresherComponent dataRefresherComponent;
    // JMXNode data from database
    @Autowired
    private JMXNodeService jmxNodeService;

    public TaskRunnerComponentImplementation(DataInitializerComponent dataInitializerComponent, DataRefresherComponent dataRefresherComponent) {
        this.dataInitializerComponent = dataInitializerComponent;
        this.dataRefresherComponent = dataRefresherComponent;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize node data map
        dataInitializerComponent.pollInitializeData(jmxNodeService);

        // poll and refresh node data map
        dataRefresherComponent.iterateAndRefreshInstantNodeDataMap();
        dataRefresherComponent.iterateAndRefreshNodeDataMap();
    }
}
