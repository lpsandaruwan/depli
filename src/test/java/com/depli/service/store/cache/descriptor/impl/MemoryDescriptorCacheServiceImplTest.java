package com.depli.service.store.cache.descriptor.impl;

import com.depli.service.store.cache.BaseCacheServiceImplTest;
import com.depli.service.store.cache.descriptor.MemoryDescriptorCacheService;
import org.mockito.InjectMocks;

import static com.depli.constant.CacheName.MEMORY_USAGE_DESCRIPTOR_CACHE;

public class MemoryDescriptorCacheServiceImplTest extends BaseCacheServiceImplTest<MemoryDescriptorCacheService> {

    @InjectMocks
    private MemoryDescriptorCacheServiceImpl memoryDescriptorCacheService;

    @Override
    protected MemoryDescriptorCacheService cacheService() {
        return memoryDescriptorCacheService;
    }

    @Override
    protected String cacheKey() {
        return MEMORY_USAGE_DESCRIPTOR_CACHE;
    }

}
