package com.depli.service.store.cache.impl;

import com.depli.service.store.cache.PlatformResourcesDescriptorCacheService;
import com.depli.store.cache.descriptor.PlatformResourcesDescriptor;
import org.infinispan.Cache;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.depli.constant.CacheName.PLATFORM_RESOURCES_DESCRIPTOR_CACHE;

/**
 * Platform resources descriptor cache service implementation.
 *
 * @author lpsandaruwan
 * @since 9/11/17
 */

@Service
public class PlatformResourcesDescriptorCacheServiceImpl implements PlatformResourcesDescriptorCacheService {

    @Autowired
    private SpringEmbeddedCacheManager springEmbeddedCacheManager;

    @Override
    public Cache<Long, PlatformResourcesDescriptor> getCache() {
        return springEmbeddedCacheManager.getNativeCacheManager().getCache(PLATFORM_RESOURCES_DESCRIPTOR_CACHE);
    }

    @Override
    public void clearCache() {
        springEmbeddedCacheManager.getCache(PLATFORM_RESOURCES_DESCRIPTOR_CACHE).clear();
    }
}
