package com.depli.service.store.cache.impl;

import com.depli.store.cache.descriptor.ClassLoadingDataDescriptor;
import com.depli.service.store.cache.ClassLoadingDataDescriptorCacheService;
import org.infinispan.Cache;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.depli.constant.CacheName.CLASS_LOADING_DATA;

/**
 * Class loading data descriptor cache Service Implementation.
 *
 * @author lpsandaruwan
 * @since 9/11/17
 */

@Service
public class ClassLoadingDataDescriptorCacheServiceImpl implements ClassLoadingDataDescriptorCacheService {

    @Autowired
    private SpringEmbeddedCacheManager springEmbeddedCacheManager;

    @Override
    public Cache<Long, ClassLoadingDataDescriptor> getCache() {
        return springEmbeddedCacheManager.getNativeCacheManager().getCache(CLASS_LOADING_DATA.getCacheName());
    }

    @Override
    public void clearCache() {
        springEmbeddedCacheManager.getCache(CLASS_LOADING_DATA.getCacheName()).clear();
    }
}
