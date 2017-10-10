package com.depli.service.store.cache.descriptor.impl;

import static com.depli.constant.CacheName.THREAD_DESCRIPTOR_CACHE;

import com.depli.service.store.cache.BaseCacheServiceImplTest;
import com.depli.service.store.cache.descriptor.ThreadDescriptorCacheService;
import org.mockito.InjectMocks;

public class ThreadDescriptorCacheServiceImplTest extends
    BaseCacheServiceImplTest<ThreadDescriptorCacheService> {

  @InjectMocks
  private ThreadDescriptorCacheServiceImpl threadDescriptorCacheService;

  @Override
  protected ThreadDescriptorCacheService cacheService() {
    return threadDescriptorCacheService;
  }

  @Override
  protected String cacheKey() {
    return THREAD_DESCRIPTOR_CACHE;
  }

}
