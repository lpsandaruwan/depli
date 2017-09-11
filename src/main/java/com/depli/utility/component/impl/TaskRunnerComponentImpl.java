package com.depli.utility.component.impl;

import com.depli.service.store.persistent.JMXNodeService;
import com.depli.utility.component.DataInitializerComponent;
import com.depli.utility.component.DataRefresherComponent;
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
public class TaskRunnerComponentImpl implements CommandLineRunner {

    private final DataInitializerComponent dataInitializerComponent;
    private final DataRefresherComponent dataRefresherComponent;
    // JMXNode store from database
    @Autowired
    private JMXNodeService jmxNodeService;

    public TaskRunnerComponentImpl(DataInitializerComponent dataInitializerComponent, DataRefresherComponent dataRefresherComponent) {
        this.dataInitializerComponent = dataInitializerComponent;
        this.dataRefresherComponent = dataRefresherComponent;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize node store map
        dataInitializerComponent.pollInitializeData(jmxNodeService);

        // poll and refresh node store map
        dataRefresherComponent.iterateAndRefreshInstantNodeDataMap();
        dataRefresherComponent.iterateAndRefreshNodeDataMap();
    }
}
