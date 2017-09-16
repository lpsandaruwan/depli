package com.depli.service.store.descriptor.impl;

import com.depli.service.store.cache.MemoryUsageDescriptorCacheService;
import com.depli.service.store.descriptor.MemoryUsageDescriptorService;
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
public class MemoryUsageDescriptorServiceImpl implements MemoryUsageDescriptorService {

    @Autowired
    private MemoryUsageDescriptorCacheService memoryUsageDescriptorCacheService;

    @Override
    public MemoryUsageDescriptor getByNodeId(Long nodeId) {
        return memoryUsageDescriptorCacheService.getCache().get(nodeId);
    }

    @Override
    public void save(Long nodeId, MemoryUsageDescriptor memoryUsageDescriptor) {
        memoryUsageDescriptorCacheService.getCache().put(nodeId, memoryUsageDescriptor);
    }
}
