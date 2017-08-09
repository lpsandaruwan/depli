package com.depli.services;

import com.depli.utilities.components.implementations.DataInitializerComponentImplementation;
import com.depli.services.implementations.JMXNodeServiceImplementation;
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
    private JMXNodeServiceImplementation jmxNodeServiceImplementation;

    @Autowired
    private DataInitializerComponentImplementation dataInitializerServiceImplementation;

    @Test
    public void initializeDJMXNodeConnectionsTest() throws Exception {
        dataInitializerServiceImplementation.initializeDJMXNodeConnections(jmxNodeServiceImplementation);
    }

    @Test
    public void initializeMxBeanDataObjectsTest() throws Exception {
        dataInitializerServiceImplementation.initializeMxBeanDataObjects();
    }
}
