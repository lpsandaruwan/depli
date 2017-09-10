package com.depli.service;

import com.depli.service.impl.JMXNodeServiceImpl;
import com.depli.utility.components.implementations.DataInitializerComponentImplementation;
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
public class DataInitializerComponentImplementationTest {

    @Autowired
    private JMXNodeServiceImpl jmxNodeServiceImpl;

    @Autowired
    private DataInitializerComponentImplementation dataInitializerServiceImplementation;

    @Test
    public void initializeDJMXNodeConnectionsTest() throws Exception {
        dataInitializerServiceImplementation.initializeDJMXNodeConnections(jmxNodeServiceImpl);
    }

    @Test
    public void initializeMxBeanDataObjectsTest() throws Exception {
        dataInitializerServiceImplementation.initializeMxBeanDataObjects();
    }
}
