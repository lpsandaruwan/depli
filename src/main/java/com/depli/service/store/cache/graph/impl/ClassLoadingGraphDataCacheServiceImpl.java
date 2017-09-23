package com.depli.service.store.cache.graph.impl;

import static com.depli.constant.CacheName.CLASS_LOADING_GRAPH_DATA_CACHE;

import com.depli.service.store.cache.graph.ClassLoadingGraphDataCacheService;
import com.depli.service.store.graph.ClassLoadingGraphDataService;
import com.depli.store.cache.graph.ClassLoadingGraphData;
import org.infinispan.Cache;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class loading graph data cache service implementation.
 *
 * @author lpsandaruwan
 * @since 9/23/17
 */

@Service
public class ClassLoadingGraphDataCacheServiceImpl implements ClassLoadingGraphDataCacheService {

  @Autowired
  private SpringEmbeddedCacheManager springEmbeddedCacheManager;

  @Override
  public Cache<Long, ClassLoadingGraphData> getCache() {
    return springEmbeddedCacheManager.getNativeCacheManager()
        .getCache(CLASS_LOADING_GRAPH_DATA_CACHE);
  }

  @Override
  public void clearCache() {
    springEmbeddedCacheManager.getCache(CLASS_LOADING_GRAPH_DATA_CACHE).clear();
  }
}
