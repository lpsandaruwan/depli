package com.depli.service.store.descriptor.impl;

import com.depli.service.store.cache.OperatingSystemDescriptorCacheService;
import com.depli.service.store.descriptor.OperatingSystemDescriptorService;
import com.depli.store.cache.descriptor.OperatingSystemDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Operating system descriptor service implementation.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Service
public class OperatingSystemDescriptorServiceImpl implements OperatingSystemDescriptorService {

    @Autowired
    private OperatingSystemDescriptorCacheService operatingSystemDescriptorCacheService;

    @Override
    public OperatingSystemDescriptor getByNodeId(Long nodeId) {
        return operatingSystemDescriptorCacheService.getCache().get(nodeId);
    }

    @Override
    public void save(Long nodeId, OperatingSystemDescriptor operatingSystemDescriptor) {
        operatingSystemDescriptorCacheService.getCache().put(nodeId, operatingSystemDescriptor);
    }
}
