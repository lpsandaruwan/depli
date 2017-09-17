package com.depli.service.builder;

import com.depli.store.cache.connector.ConnectionTree;
import com.depli.store.persistent.entity.JMXNode;

import java.io.IOException;

/**
 * Connection Tree Builder Service
 *
 * Provides service to initialize and retrieve ConnectionTree instances.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

public interface ConnectionTreeBuilderService {

    /**
     * Returns built ConnectionTree instance with opened MBeanSeverConnections and MXBeans proxy connections.
     *
     * @param jmxNode JMXNode entity
     * @return ConnectionTree instance for given nodeId
     */
    public ConnectionTree getTree(JMXNode jmxNode) throws IOException;
}
