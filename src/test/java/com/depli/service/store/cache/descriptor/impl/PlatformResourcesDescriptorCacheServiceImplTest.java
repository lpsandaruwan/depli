package com.depli.service.store.cache.descriptor.impl;

import com.depli.service.store.cache.BaseCacheServiceImplTest;
import com.depli.service.store.cache.descriptor.PlatformResourcesDescriptorCacheService;
import org.junit.Test;
import org.mockito.InjectMocks;

import static com.depli.constant.CacheName.PLATFORM_RESOURCES_DESCRIPTOR_CACHE;

public class PlatformResourcesDescriptorCacheServiceImplTest extends BaseCacheServiceImplTest<PlatformResourcesDescriptorCacheService> {

    @InjectMocks
    private PlatformResourcesDescriptorCacheServiceImpl platformResourcesDescriptorCacheService;

    @Override
    protected PlatformResourcesDescriptorCacheService cacheService() {
        return platformResourcesDescriptorCacheService;
    }

    @Override
    protected String cacheKey() {
        return PLATFORM_RESOURCES_DESCRIPTOR_CACHE;
    }

}
