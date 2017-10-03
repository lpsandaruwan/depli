package com.depli.service.store.cache.descriptor.impl;

import com.depli.service.store.cache.BaseCacheServiceImplTest;
import com.depli.service.store.cache.descriptor.ClassLoadingDescriptorCacheService;
import org.junit.Test;
import org.mockito.InjectMocks;

import static com.depli.constant.CacheName.CLASS_LOADING_DESCRIPTOR_CACHE;

public class ClassLoadingDescriptorCacheServiceImplTest extends BaseCacheServiceImplTest<ClassLoadingDescriptorCacheService> {

    @InjectMocks
    private ClassLoadingDescriptorCacheServiceImpl classLoadingDescriptorCacheService;

    @Override
    protected ClassLoadingDescriptorCacheService cacheService() {
        return classLoadingDescriptorCacheService;
    }

    @Override
    protected String cacheKey() {
        return CLASS_LOADING_DESCRIPTOR_CACHE;
    }

}
