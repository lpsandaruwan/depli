package com.depli.service.store.cache.descriptor.impl;

import static com.depli.constant.CacheName.CLASS_LOADING_DESCRIPTOR_CACHE;

import com.depli.service.store.cache.descriptor.ClassLoadingDescriptorCacheService;
import com.depli.store.cache.descriptor.ClassLoadingDescriptor;
import org.infinispan.Cache;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class loading data descriptor cache service implementation.
 *
 * @author lpsandaruwan
 * @since 9/11/17
 */

@Service
public class ClassLoadingDescriptorCacheServiceImpl implements ClassLoadingDescriptorCacheService {

  @Autowired
  private SpringEmbeddedCacheManager springEmbeddedCacheManager;

  @Override
  public Cache<Long, ClassLoadingDescriptor> getCache() {
    return springEmbeddedCacheManager.getNativeCacheManager()
        .getCache(CLASS_LOADING_DESCRIPTOR_CACHE);
  }

  @Override
  public void clearCache() {
    springEmbeddedCacheManager.getCache(CLASS_LOADING_DESCRIPTOR_CACHE).clear();
  }
}
