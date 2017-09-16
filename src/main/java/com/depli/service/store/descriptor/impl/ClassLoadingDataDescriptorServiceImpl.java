package com.depli.service.store.descriptor.impl;

import com.depli.service.store.cache.ClassLoadingDataDescriptorCacheService;
import com.depli.service.store.descriptor.ClassLoadingDataDescriptorService;
import com.depli.store.cache.descriptor.ClassLoadingDataDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class loading data descriptor service implementation.
 *
 * @author lpsandaruwan
 * @since 9/12/17
 */

@Service
public class ClassLoadingDataDescriptorServiceImpl implements ClassLoadingDataDescriptorService {

    @Autowired
    private ClassLoadingDataDescriptorCacheService classLoadingDataDescriptorCacheService;

    @Override
    public ClassLoadingDataDescriptor getByNodeId(Long nodeId) {
        return classLoadingDataDescriptorCacheService.getCache().get(nodeId);
    }

    @Override
    public void save(Long nodeId, ClassLoadingDataDescriptor classLoadingDataDescriptor) {
        classLoadingDataDescriptorCacheService.getCache().put(nodeId, classLoadingDataDescriptor);
    }
}
