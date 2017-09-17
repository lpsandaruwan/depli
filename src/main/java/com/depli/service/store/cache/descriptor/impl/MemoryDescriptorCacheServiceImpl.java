package com.depli.service.store.cache.descriptor.impl;

import com.depli.service.store.cache.descriptor.MemoryDescriptorCacheService;
import com.depli.store.cache.descriptor.MemoryDescriptor;
import org.infinispan.Cache;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.depli.constant.CacheName.MEMORY_USAGE_DESCRIPTOR_CACHE;

/**
 * Memory descriptor cache service implementation.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Service
public class MemoryDescriptorCacheServiceImpl implements MemoryDescriptorCacheService {

    @Autowired
    private SpringEmbeddedCacheManager springEmbeddedCacheManager;

    @Override
    public Cache<Long, MemoryDescriptor> getCache() {
        return springEmbeddedCacheManager.getNativeCacheManager().getCache(MEMORY_USAGE_DESCRIPTOR_CACHE);
    }

    @Override
    public void clearCache() {
        springEmbeddedCacheManager.getCache(MEMORY_USAGE_DESCRIPTOR_CACHE).clear();
    }
}
