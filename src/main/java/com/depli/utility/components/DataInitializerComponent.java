package com.depli.utility.components;

import com.depli.service.JMXNodeService;

/**
 * Created by Lahiru Pathirage on 8/6/2017.
 */

public interface DataInitializerComponent {

    public void initializeDJMXNodeConnections(JMXNodeService jmxNodeService) throws Exception;

    public void initializeMxBeanDataObjects() throws Exception;

    public void pollInitializeData(JMXNodeService jmxNodeService) throws Exception;
}
