package com.depli.utility.initializer.cache.impl;

import com.depli.service.store.descriptor.MemoryDescriptorService;
import com.depli.store.cache.descriptor.MemoryDescriptor;
import com.depli.utility.initializer.cache.CacheInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Memory Descriptor Cache Initializer implementation
 *
 * Initializes and store MemoryDescriptor objects in MemoryDescriptor cache store.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Component
public class MemoryDescriptorCacheInitializer implements CacheInitializer {

    @Autowired
    private MemoryDescriptorService memoryDescriptorService;

    @Override
    public void initialize(Long nodeId) throws IOException {
        memoryDescriptorService.save(nodeId, new MemoryDescriptor());
    }
}
