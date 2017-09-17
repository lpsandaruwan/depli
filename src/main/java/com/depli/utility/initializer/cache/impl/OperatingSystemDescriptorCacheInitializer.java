package com.depli.utility.initializer.cache.impl;

import com.depli.service.store.connector.ConnectionTreeService;
import com.depli.service.store.descriptor.OperatingSystemDescriptorService;
import com.depli.store.cache.descriptor.OperatingSystemDescriptor;
import com.depli.utility.initializer.cache.CacheInitializer;
import com.depli.utility.mediator.OperatingSystemMXBeanMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Operating System Descriptor Cache Initializer implementation
 *
 * Initializes and store OperatingSystemDescriptor objects in OperatingSystemDescriptor cache store.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Component
public class OperatingSystemDescriptorCacheInitializer implements CacheInitializer {

    @Autowired
    private OperatingSystemDescriptorService operatingSystemDescriptorService;

    @Autowired
    private OperatingSystemMXBeanMediator operatingSystemMXBeanMediator;

    @Autowired
    private ConnectionTreeService connectionTreeService;

    @Override
    public void initialize(Long nodeId) throws IOException {
        operatingSystemDescriptorService.save(nodeId, new OperatingSystemDescriptor());

        /* Mediate static data from OperatingSystemMXBean to operating system descriptor in cache */
        operatingSystemMXBeanMediator.mediateStaticData(
                nodeId,
                connectionTreeService.getByNodeId(nodeId).getOperatingSystemMXBean()
        );
    }
}
