package com.depli.service.store.cache.impl;

import com.depli.service.store.cache.ThreadDescriptorCacheService;
import com.depli.store.cache.descriptor.ThreadDescriptor;
import org.infinispan.Cache;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.depli.constant.CacheName.THREAD;

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
        return springEmbeddedCacheManager.getNativeCacheManager().getCache(THREAD.getCacheName());
    }

    @Override
    public void clearCache() {
        springEmbeddedCacheManager.getCache(THREAD.getCacheName()).clear();
    }
}
