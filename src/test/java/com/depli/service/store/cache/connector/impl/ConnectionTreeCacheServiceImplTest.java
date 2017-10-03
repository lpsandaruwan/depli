package com.depli.service.store.cache.connector.impl;

import com.depli.service.store.cache.BaseCacheServiceImplTest;
import com.depli.service.store.cache.connector.ConnectionTreeCacheService;
import org.mockito.InjectMocks;

import static com.depli.constant.CacheName.CONNECTION_TREE_CACHE;

public class ConnectionTreeCacheServiceImplTest extends BaseCacheServiceImplTest<ConnectionTreeCacheService> {

    @InjectMocks
    private ConnectionTreeCacheServiceImpl connectionTreeCacheService;

    @Override
    protected ConnectionTreeCacheService cacheService() {
        return connectionTreeCacheService;
    }

    @Override
    protected String cacheKey() {
        return CONNECTION_TREE_CACHE;
    }

}
