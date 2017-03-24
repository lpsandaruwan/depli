package com.depli.utility;

import com.depli.service.DataInitializerService;
import com.depli.service.JMXNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * TaskRunner
 * Make connections to all the JMX nodes using service threads and keep node information updated by polling.
 *
 * Created by lpsandaruwan on 3/22/17.
 */

@Component
public class TaskRunner implements CommandLineRunner {

    // JMXNode data from database
    @Autowired
    private JMXNodeService jmxNodeService;

    private final DataInitializerService dataInitializerService;

    public TaskRunner(DataInitializerService dataInitializerService) {
        this.dataInitializerService = dataInitializerService;
    }

    @Override
    public void run(String... args) throws Exception {
        dataInitializerService.initializeDJMXNodeConnections(jmxNodeService);
        dataInitializerService.initializeMxBeanDataObjects();
    }
}
