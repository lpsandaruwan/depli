package com.depli.utility.components.implementations;

import com.depli.service.JMXNodeService;
import com.depli.utility.components.DataInitializerComponent;
import com.depli.utility.components.DataRefresherComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * TaskRunner
 * Make connections to all the JMX nodes using service threads and keep node information updated by polling.
 * <p>
 * Created by lpsandaruwan on 3/22/17.
 */

@Component
public class TaskRunnerComponentImplementation implements CommandLineRunner {

    private final DataInitializerComponent dataInitializerComponent;
    private final DataRefresherComponent dataRefresherComponent;
    // JMXNode domain from database
    @Autowired
    private JMXNodeService jmxNodeService;

    public TaskRunnerComponentImplementation(DataInitializerComponent dataInitializerComponent, DataRefresherComponent dataRefresherComponent) {
        this.dataInitializerComponent = dataInitializerComponent;
        this.dataRefresherComponent = dataRefresherComponent;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize node domain map
        dataInitializerComponent.pollInitializeData(jmxNodeService);

        // poll and refresh node domain map
        dataRefresherComponent.iterateAndRefreshInstantNodeDataMap();
        dataRefresherComponent.iterateAndRefreshNodeDataMap();
    }
}
