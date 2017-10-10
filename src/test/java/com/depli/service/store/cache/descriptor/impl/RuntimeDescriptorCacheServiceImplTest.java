package com.depli.service.store.cache.descriptor.impl;

import static com.depli.constant.CacheName.RUNTIME_DESCRIPTOR_CACHE;

import com.depli.service.store.cache.BaseCacheServiceImplTest;
import com.depli.service.store.cache.descriptor.RuntimeDescriptorCacheService;
import org.mockito.InjectMocks;

public class RuntimeDescriptorCacheServiceImplTest extends
    BaseCacheServiceImplTest<RuntimeDescriptorCacheService> {

  @InjectMocks
  private RuntimeDescriptorCacheServiceImpl runtimeDescriptorCacheService;

  @Override
  protected RuntimeDescriptorCacheService cacheService() {
    return runtimeDescriptorCacheService;
  }

  @Override
  protected String cacheKey() {
    return RUNTIME_DESCRIPTOR_CACHE;
  }

}
