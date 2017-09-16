package com.depli.service.store.descriptor.impl;

import com.depli.service.store.cache.RuntimeDescriptorCacheService;
import com.depli.service.store.descriptor.RuntimeDescriptorDescriptorService;
import com.depli.store.cache.descriptor.RuntimeDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Runtime descriptor service implementation.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Service
public class RuntimeDescriptorServiceImpl implements RuntimeDescriptorDescriptorService {

    @Autowired
    private RuntimeDescriptorCacheService runtimeDescriptorCacheService;

    @Override
    public RuntimeDescriptor getByNodeId(Long nodeId) {
        return runtimeDescriptorCacheService.getCache().get(nodeId);
    }

    @Override
    public void save(Long nodeId, RuntimeDescriptor runtimeDescriptor) {
        runtimeDescriptorCacheService.getCache().put(nodeId, runtimeDescriptor);
    }
}
