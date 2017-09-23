package com.depli.service.store.cache.graph.impl;

import static com.depli.constant.CacheName.CPU_GRAPH_DATA_CACHE;

import com.depli.service.store.cache.graph.ProcessingUnitGraphDataCacheService;
import com.depli.store.cache.graph.ProcessingUnitGraphData;
import org.infinispan.Cache;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CPU graph data object cache service implementation.
 *
 * @author lpsandaruwan
 * @since 9/19/17
 */

@Service
public class ProcessingUnitGraphDataCacheServiceImpl implements
    ProcessingUnitGraphDataCacheService {

  @Autowired
  private SpringEmbeddedCacheManager springEmbeddedCacheManager;

  @Override
  public Cache<Long, ProcessingUnitGraphData> getCache() {
    return springEmbeddedCacheManager.getNativeCacheManager()
        .getCache(CPU_GRAPH_DATA_CACHE);
  }

  @Override
  public void clearCache() {
    springEmbeddedCacheManager.getCache(CPU_GRAPH_DATA_CACHE).clear();
  }
}
