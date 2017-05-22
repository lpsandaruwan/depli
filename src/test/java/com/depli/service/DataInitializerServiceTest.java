package com.depli.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lpsandaruwan on 5/22/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataInitializerServiceTest {

    @Autowired
    private JMXNodeService jmxNodeService;

    @Autowired
    private DataInitializerService  dataInitializerService;

    @Test
    public void initializeDJMXNodeConnectionsTest() throws Exception {
        dataInitializerService.initializeDJMXNodeConnections(jmxNodeService);
    }

    @Test
    public void initializeMxBeanDataObjectsTest() throws Exception {
        dataInitializerService.initializeMxBeanDataObjects();
    }
}
