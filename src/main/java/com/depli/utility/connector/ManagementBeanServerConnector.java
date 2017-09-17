package com.depli.utility.connector;

import com.depli.store.cache.connector.MainConnector;
import com.depli.store.persistent.entity.JMXNode;

import java.io.IOException;

/**
 * Management bean server connector
 *
 * Provides methods to open and retrieve management bean server connection for given remote Java virtual machine.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

public interface ManagementBeanServerConnector {

    /**
     * Returns main connector object containing management bean server connection for given remote Java virtual machine node information.
     *
     * @param jmxNode entity containing remote Java virtual machine's connection metadata
     * @return main connector object containing management bean server connection for given node
     */
    public MainConnector getConnection(JMXNode jmxNode) throws IOException;
}
