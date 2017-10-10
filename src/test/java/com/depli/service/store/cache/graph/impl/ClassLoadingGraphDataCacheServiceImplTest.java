package com.depli.service.store.cache.graph.impl;

import static com.depli.constant.CacheName.CLASS_LOADING_GRAPH_DATA_CACHE;

import com.depli.service.store.cache.BaseCacheServiceImplTest;
import com.depli.service.store.cache.graph.ClassLoadingGraphDataCacheService;
import org.mockito.InjectMocks;

public class ClassLoadingGraphDataCacheServiceImplTest extends
    BaseCacheServiceImplTest<ClassLoadingGraphDataCacheService> {

  @InjectMocks
  private ClassLoadingGraphDataCacheServiceImpl classLoadingGraphDataCacheService;

  @Override
  protected ClassLoadingGraphDataCacheService cacheService() {
    return classLoadingGraphDataCacheService;
  }

  @Override
  protected String cacheKey() {
    return CLASS_LOADING_GRAPH_DATA_CACHE;
  }

}
