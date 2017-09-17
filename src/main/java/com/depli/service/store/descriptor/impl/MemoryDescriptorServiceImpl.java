package com.depli.service.store.descriptor.impl;

import com.depli.service.store.cache.descriptor.MemoryDescriptorCacheService;
import com.depli.service.store.descriptor.MemoryDescriptorService;
import com.depli.store.cache.descriptor.MemoryUsageDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Memory usage descriptor service implementation.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Service
public class MemoryDescriptorServiceImpl implements MemoryDescriptorService {

    @Autowired
    private MemoryDescriptorCacheService memoryDescriptorCacheService;

    @Override
    public MemoryUsageDescriptor getByNodeId(Long nodeId) {
        return memoryDescriptorCacheService.getCache().get(nodeId);
    }

    @Override
    public void save(Long nodeId, MemoryUsageDescriptor memoryUsageDescriptor) {
        memoryDescriptorCacheService.getCache().put(nodeId, memoryUsageDescriptor);
    }
}
