package com.depli.service.store.cache.descriptor.impl;

import static com.depli.constant.CacheName.PLATFORM_RESOURCES_DESCRIPTOR_CACHE;

import com.depli.service.store.cache.BaseCacheServiceImplTest;
import com.depli.service.store.cache.descriptor.PlatformResourcesDescriptorCacheService;
import org.mockito.InjectMocks;

public class PlatformResourcesDescriptorCacheServiceImplTest extends
    BaseCacheServiceImplTest<PlatformResourcesDescriptorCacheService> {

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
