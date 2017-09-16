package com.depli.service.store.cache.impl;

import com.depli.service.store.cache.MemoryUsageDescriptorCacheService;
import com.depli.store.cache.descriptor.MemoryUsageDescriptor;
import org.infinispan.Cache;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.depli.constant.CacheName.MEMORY_USAGE;

/**
 * Memory usage descriptor cache service implementation.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Service
public class MemoryUsageDescriptorCacheServiceImpl implements MemoryUsageDescriptorCacheService {

    @Autowired
    private SpringEmbeddedCacheManager springEmbeddedCacheManager;

    @Override
    public Cache<Long, MemoryUsageDescriptor> getCache() {
        return springEmbeddedCacheManager.getNativeCacheManager().getCache(MEMORY_USAGE.getCacheName());
    }

    @Override
    public void clearCache() {
        springEmbeddedCacheManager.getCache(MEMORY_USAGE.getCacheName()).clear();
    }
}
