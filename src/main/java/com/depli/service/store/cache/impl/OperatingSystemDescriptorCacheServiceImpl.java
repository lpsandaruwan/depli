package com.depli.service.store.cache.impl;

import com.depli.service.store.cache.OperatingSystemDescriptorCacheService;
import com.depli.store.cache.descriptor.OperatingSystemDescriptor;
import org.infinispan.Cache;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.depli.constant.CacheName.OPERATING_SYSTEM_DESCRIPTOR_CACHE;

/**
 * Operating system descriptor cache service implementation.
 *
 * @author lpsandaruwan
 * @since 9/11/17
 */

@Service
public class OperatingSystemDescriptorCacheServiceImpl implements OperatingSystemDescriptorCacheService {

    @Autowired
    private SpringEmbeddedCacheManager springEmbeddedCacheManager;

    @Override
    public Cache<Long, OperatingSystemDescriptor> getCache() {
        return springEmbeddedCacheManager.getNativeCacheManager().getCache(OPERATING_SYSTEM_DESCRIPTOR_CACHE);
    }

    @Override
    public void clearCache() {
        springEmbeddedCacheManager.getCache(OPERATING_SYSTEM_DESCRIPTOR_CACHE).clear();
    }
}
