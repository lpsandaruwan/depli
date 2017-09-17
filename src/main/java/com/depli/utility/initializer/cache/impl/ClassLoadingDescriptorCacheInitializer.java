package com.depli.utility.initializer.cache.impl;

import com.depli.service.store.connector.ConnectionTreeService;
import com.depli.service.store.descriptor.ClassLoadingDescriptorService;
import com.depli.store.cache.connector.ConnectionTree;
import com.depli.store.cache.descriptor.ClassLoadingDescriptor;
import com.depli.utility.initializer.cache.CacheInitializer;
import com.depli.utility.mediator.ClassLoadingMXBeanMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Class Loading Descriptor Cache Initializer implementation
 *
 * Initializes and store ClassLoadingDescriptor objects in ClassLoadingDescriptor cache store.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Component
public class ClassLoadingDescriptorCacheInitializer implements CacheInitializer {

    @Autowired
    private ClassLoadingDescriptorService classLoadingDescriptorService;

    @Override
    public void initialize(Long nodeId) throws IOException {
        classLoadingDescriptorService.save(nodeId, new ClassLoadingDescriptor());
    }
}
