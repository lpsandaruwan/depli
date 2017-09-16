package com.depli.service.store.descriptor.impl;

import com.depli.service.store.cache.PlatformResourcesDescriptorCacheService;
import com.depli.service.store.descriptor.PlatformResourcesDescriptorService;
import com.depli.store.cache.descriptor.PlatformResourcesDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Platform resources descriptor service implementation.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Service
public class PlatformResourcesDescriptorServiceImpl implements PlatformResourcesDescriptorService {

    @Autowired
    private PlatformResourcesDescriptorCacheService platformResourcesDescriptorCacheService;

    @Override
    public PlatformResourcesDescriptor getByNodeId(Long nodeId) {
        return platformResourcesDescriptorCacheService.getCache().get(nodeId);
    }

    @Override
    public void save(Long nodeId, PlatformResourcesDescriptor platformResourcesDescriptor) {
        platformResourcesDescriptorCacheService.getCache().put(nodeId, platformResourcesDescriptor);
    }
}
