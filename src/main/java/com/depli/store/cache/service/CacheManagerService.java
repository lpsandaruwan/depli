package com.depli.store.cache.service;

import com.depli.store.cache.descriptor.ClassLoadingDataDescriptor;
import org.infinispan.Cache;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.depli.constant.CacheName.CLASS_LOADING_DATA;

/**
 * Created by lpsandaruwan on 9/10/17.
 */

@Service
public class CacheManagerService {

    @Autowired
    private SpringEmbeddedCacheManager springEmbeddedCacheManager;

    private Cache<Long, ClassLoadingDataDescriptor> classLoadingDataDescriptorCache;

    public Cache<Long, ClassLoadingDataDescriptor> getClassLoadingDataDescriptorCache() {
        classLoadingDataDescriptorCache = springEmbeddedCacheManager.getNativeCacheManager().getCache(CLASS_LOADING_DATA.getCacheName());
        return classLoadingDataDescriptorCache;
    }
}
