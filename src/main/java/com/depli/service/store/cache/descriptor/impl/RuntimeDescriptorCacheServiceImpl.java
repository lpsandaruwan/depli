package com.depli.service.store.cache.descriptor.impl;

import static com.depli.constant.CacheName.RUNTIME_DESCRIPTOR_CACHE;

import com.depli.service.store.cache.descriptor.RuntimeDescriptorCacheService;
import com.depli.store.cache.descriptor.RuntimeDescriptor;
import org.infinispan.Cache;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Runtime descriptor cache service implementation.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Service
public class RuntimeDescriptorCacheServiceImpl implements RuntimeDescriptorCacheService {

  @Autowired
  private SpringEmbeddedCacheManager springEmbeddedCacheManager;

  @Override
  public Cache<Long, RuntimeDescriptor> getCache() {
    return springEmbeddedCacheManager.getNativeCacheManager().getCache(RUNTIME_DESCRIPTOR_CACHE);
  }

  @Override
  public void clearCache() {
    springEmbeddedCacheManager.getCache(RUNTIME_DESCRIPTOR_CACHE).clear();
  }
}
