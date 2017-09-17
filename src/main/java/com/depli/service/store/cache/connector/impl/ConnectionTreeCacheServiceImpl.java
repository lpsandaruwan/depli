package com.depli.service.store.cache.connector.impl;

import static com.depli.constant.CacheName.CONNECTION_TREE_CACHE;

import com.depli.service.store.cache.connector.ConnectionTreeCacheService;
import com.depli.store.cache.connector.ConnectionTree;
import org.infinispan.Cache;
import org.infinispan.spring.provider.SpringEmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Connection tree cache service implementation
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Service
public class ConnectionTreeCacheServiceImpl implements ConnectionTreeCacheService {

  @Autowired
  private SpringEmbeddedCacheManager springEmbeddedCacheManager;

  @Override
  public Cache<Long, ConnectionTree> getCache() {
    return springEmbeddedCacheManager.getNativeCacheManager().getCache(CONNECTION_TREE_CACHE);
  }

  @Override
  public void clearCache() {
    springEmbeddedCacheManager.getCache(CONNECTION_TREE_CACHE).clear();
  }
}
