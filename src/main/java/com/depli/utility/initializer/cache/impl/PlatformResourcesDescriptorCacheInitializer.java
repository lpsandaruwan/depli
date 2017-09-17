package com.depli.utility.initializer.cache.impl;

import com.depli.service.store.connector.ConnectionTreeService;
import com.depli.service.store.descriptor.PlatformResourcesDescriptorService;
import com.depli.store.cache.descriptor.PlatformResourcesDescriptor;
import com.depli.utility.initializer.cache.CacheInitializer;
import com.depli.utility.mediator.PlatformResourcesMXBeanMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Platform Resources Descriptor Cache Initializer implementation
 *
 * Initializes and store PlatformResourcesDescriptor objects in PlatformResourcesDescriptor cache store.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Component
public class PlatformResourcesDescriptorCacheInitializer implements CacheInitializer {

    @Autowired
    private PlatformResourcesDescriptorService platformResourcesDescriptorService;

    @Autowired
    private PlatformResourcesMXBeanMediator platformResourcesMXBeanMediator;

    @Autowired
    private ConnectionTreeService connectionTreeService;

    @Override
    public void initialize(Long nodeId) throws IOException {
        platformResourcesDescriptorService.save(nodeId, new PlatformResourcesDescriptor());

        /* Mediate static data from com.sun.management.OperatingSystemMXBean to platform resources descriptor in cache */
        platformResourcesMXBeanMediator.mediateStaticData(
                nodeId,
                connectionTreeService.getByNodeId(nodeId).getPlatformResourcesMXBean()
        );
    }
}
