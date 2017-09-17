package com.depli.utility.connector.impl;

import com.depli.store.cache.connector.MainConnector;
import com.depli.store.persistent.entity.JMXNode;
import com.depli.utility.connector.ManagementBeanServerConnector;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * Management bean server connection implementation
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Component
public class ManagementBeanServerConnectorImpl implements ManagementBeanServerConnector {

    @Override
    public MainConnector getConnection(JMXNode jmxNode) throws IOException {
        MainConnector mainConnector = new MainConnector(jmxNode);
        mainConnector.openConnection();

        return mainConnector;
    }
}
