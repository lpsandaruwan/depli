package com.depli.utility.initializer.impl;

import com.depli.service.store.cache.connector.ConnectionTreeCacheService;
import com.depli.service.store.cache.descriptor.*;
import com.depli.service.store.persistent.JMXNodeService;
import com.depli.store.persistent.entity.JMXNode;
import com.depli.utility.initializer.InitializerFactory;
import com.depli.utility.initializer.cache.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Initializer Factory implementation
 *
 * Initializes caches and data stores.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Component
public class InitializerFactoryImpl implements InitializerFactory {

    @Autowired
    private ConnectionTreeCacheService connectionTreeCacheService;

    @Autowired
    private ClassLoadingDescriptorCacheService classLoadingDescriptorCacheService;

    @Autowired
    private MemoryDescriptorCacheService memoryDescriptorCacheService;

    @Autowired
    private OperatingSystemDescriptorCacheService operatingSystemDescriptorCacheService;

    @Autowired
    private PlatformResourcesDescriptorCacheService platformResourcesDescriptorCacheService;

    @Autowired
    private RuntimeDescriptorCacheService runtimeDescriptorCacheService;

    @Autowired
    private ThreadDescriptorCacheService threadDescriptorCacheService;

    @Autowired
    private ClassLoadingDescriptorCacheInitializer classLoadingDescriptorCacheInitializer;

    @Autowired
    private ConnectionTreeInitializer connectionTreeInitializer;

    @Autowired
    private MemoryDescriptorCacheInitializer memoryDescriptorCacheInitializer;

    @Autowired
    private OperatingSystemDescriptorCacheInitializer operatingSystemDescriptorCacheInitializer;

    @Autowired
    private PlatformResourcesDescriptorCacheInitializer platformResourcesDescriptorCacheInitializer;

    @Autowired
    private RuntimeDescriptorCacheInitializer runtimeDescriptorCacheInitializer;

    @Autowired
    private ThreadDescriptorCacheInitializer threadDescriptorCacheInitializer;

    @Autowired
    private JMXNodeService jmxNodeService;

    @Override
    public void initialize() throws IOException {
        /* Clear cache store before initializing if exists */
        connectionTreeCacheService.clearCache();
        classLoadingDescriptorCacheService.clearCache();
        memoryDescriptorCacheService.clearCache();
        operatingSystemDescriptorCacheService.clearCache();
        platformResourcesDescriptorCacheService.clearCache();
        runtimeDescriptorCacheService.clearCache();
        threadDescriptorCacheService.clearCache();

        /* Iterate JMXNode entities and initialize cache stores */
        for (JMXNode jmxNode: jmxNodeService.findAll()) {
            Long nodeId = jmxNode.getNodeId();

            connectionTreeInitializer.initialize(jmxNode);
            classLoadingDescriptorCacheInitializer.initialize(nodeId);
            memoryDescriptorCacheInitializer.initialize(nodeId);
            operatingSystemDescriptorCacheInitializer.initialize(nodeId);
            platformResourcesDescriptorCacheInitializer.initialize(nodeId);
            runtimeDescriptorCacheInitializer.initialize(nodeId);
            threadDescriptorCacheInitializer.initialize(nodeId);
        }
    }
}
