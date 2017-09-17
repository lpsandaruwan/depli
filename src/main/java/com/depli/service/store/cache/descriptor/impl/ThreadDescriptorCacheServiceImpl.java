package com.depli.service.store.cache.descriptor.impl;

import static com.depli.constant.CacheName.THREAD_DESCRIPTOR_CACHE;

import com.depli.service.store.cache.descriptor.ThreadDescriptorCacheService;
import com.depli.store.cache.descriptor.ThreadDescriptor;
import org.infinispan.Cache;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Thread descriptor cache service implementation.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Service
public class ThreadDescriptorCacheServiceImpl implements ThreadDescriptorCacheService {

  @Autowired
  private SpringEmbeddedCacheManager springEmbeddedCacheManager;

  @Override
  public Cache<Long, ThreadDescriptor> getCache() {
    return springEmbeddedCacheManager.getNativeCacheManager().getCache(THREAD_DESCRIPTOR_CACHE);
  }

  @Override
  public void clearCache() {
    springEmbeddedCacheManager.getCache(THREAD_DESCRIPTOR_CACHE).clear();
  }
}
