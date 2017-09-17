package com.depli.utility.initializer.cache.impl;

import com.depli.service.store.descriptor.ThreadDescriptorService;
import com.depli.store.cache.descriptor.ThreadDescriptor;
import com.depli.utility.initializer.cache.CacheInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Thread Descriptor Cache Initializer implementation
 *
 * Initializes and store ThreadDescriptor objects in ThreadDescriptor cache store.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Component
public class ThreadDescriptorCacheInitializer implements CacheInitializer {

    @Autowired
    private ThreadDescriptorService threadDescriptorService;

    @Override
    public void initialize(Long nodeId) throws IOException {
        threadDescriptorService.save(nodeId, new ThreadDescriptor());
    }
}
