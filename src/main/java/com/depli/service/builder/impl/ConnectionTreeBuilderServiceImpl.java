package com.depli.service.builder.impl;

import com.depli.service.builder.ConnectionTreeBuilderService;
import com.depli.store.cache.connector.ConnectionTree;
import com.depli.store.cache.connector.MainConnector;
import com.depli.store.persistent.entity.JMXNode;
import com.depli.utility.connector.ManagementBeanServerConnector;
import com.depli.utility.connector.proxy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.management.*;

/**
 * Connection tree builder service implementation
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Service
public class ConnectionTreeBuilderServiceImpl implements ConnectionTreeBuilderService {

    @Autowired
    private ManagementBeanServerConnector managementBeanServerConnector;

    @Autowired
    private ClassLoadingMXBeanProxyConnector classLoadingMXBeanProxyConnector;

    @Autowired
    private MemoryMXBeanProxyConnector memoryMXBeanProxyConnector;

    @Autowired
    private OperatingSystemMXBeanProxyConnector operatingSystemMXBeanProxyConnector;

    @Autowired
    private PlatformResourcesMXBeanProxyConnector platformResourcesMXBeanProxyConnector;

    @Autowired
    private RuntimeMXBeanProxyConnector runtimeMXBeanProxyConnector;

    @Autowired
    private ThreadMXBeanProxyConnector threadMXBeanProxyConnector;

    @Override
    public ConnectionTree getTree(JMXNode jmxNode) throws IOException {
        MainConnector mainConnector = managementBeanServerConnector.getConnection(jmxNode);
        mainConnector.openConnection();
        ClassLoadingMXBean classLoadingMXBean = classLoadingMXBeanProxyConnector.getConnection(mainConnector.getServerConnection());
        MemoryMXBean memoryMXBean = memoryMXBeanProxyConnector.getConnection(mainConnector.getServerConnection());
        OperatingSystemMXBean operatingSystemMXBean = operatingSystemMXBeanProxyConnector.getConnection(mainConnector.getServerConnection());
        com.sun.management.OperatingSystemMXBean platformResourcesMXBean = platformResourcesMXBeanProxyConnector.getConnection(mainConnector.getServerConnection());
        RuntimeMXBean runtimeMXBean = runtimeMXBeanProxyConnector.getConnection(mainConnector.getServerConnection());
        ThreadMXBean threadMXBean = threadMXBeanProxyConnector.getConnection(mainConnector.getServerConnection());

        return new ConnectionTree(
                mainConnector,
                classLoadingMXBean,
                memoryMXBean,
                operatingSystemMXBean,
                platformResourcesMXBean,
                runtimeMXBean,
                threadMXBean
        );
    }
}
