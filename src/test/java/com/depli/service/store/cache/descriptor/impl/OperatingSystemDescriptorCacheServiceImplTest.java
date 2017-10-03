package com.depli.service.store.cache.descriptor.impl;

import com.depli.service.store.cache.BaseCacheServiceImplTest;
import com.depli.service.store.cache.descriptor.OperatingSystemDescriptorCacheService;
import org.junit.Test;
import org.mockito.InjectMocks;

import static com.depli.constant.CacheName.OPERATING_SYSTEM_DESCRIPTOR_CACHE;

public class OperatingSystemDescriptorCacheServiceImplTest extends BaseCacheServiceImplTest<OperatingSystemDescriptorCacheService> {

    @InjectMocks
    private OperatingSystemDescriptorCacheServiceImpl operatingSystemDescriptorCacheService;

    @Override
    protected OperatingSystemDescriptorCacheService cacheService() {
        return operatingSystemDescriptorCacheService;
    }

    @Override
    protected String cacheKey() {
        return OPERATING_SYSTEM_DESCRIPTOR_CACHE;
    }

}
