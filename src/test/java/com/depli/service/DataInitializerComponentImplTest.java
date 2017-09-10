package com.depli.service;

import com.depli.store.persistent.service.impl.JMXNodeServiceImpl;
import com.depli.utility.component.impl.DataInitializerComponentImpl;
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
public class DataInitializerComponentImplTest {

    @Autowired
    private JMXNodeServiceImpl jmxNodeServiceImpl;

    @Autowired
    private DataInitializerComponentImpl dataInitializerServiceImplementation;

    @Test
    public void initializeDJMXNodeConnectionsTest() throws Exception {
        dataInitializerServiceImplementation.initializeDJMXNodeConnections(jmxNodeServiceImpl);
    }

    @Test
    public void initializeMxBeanDataObjectsTest() throws Exception {
        dataInitializerServiceImplementation.initializeMxBeanDataObjects();
    }
}
