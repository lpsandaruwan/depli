package com.depli.service.store.descriptor.impl;

import com.depli.service.store.cache.descriptor.ThreadDescriptorCacheService;
import com.depli.service.store.descriptor.ThreadDescriptorService;
import com.depli.store.cache.descriptor.ThreadDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Thread descriptor service implementation.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Service
public class ThreadDescriptorServiceImpl implements ThreadDescriptorService {

    @Autowired
    private ThreadDescriptorCacheService threadDescriptorCacheService;

    @Override
    public ThreadDescriptor getByNodeId(Long nodeId) {
        return threadDescriptorCacheService.getCache().get(nodeId);
    }

    @Override
    public void save(Long nodeId, ThreadDescriptor threadDescriptor) {
        threadDescriptorCacheService.getCache().put(nodeId, threadDescriptor);
    }
}
