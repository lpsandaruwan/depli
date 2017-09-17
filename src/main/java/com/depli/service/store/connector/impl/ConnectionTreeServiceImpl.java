package com.depli.service.store.connector.impl;

import com.depli.service.store.cache.connector.ConnectionTreeCacheService;
import com.depli.service.store.connector.ConnectionTreeService;
import com.depli.store.cache.connector.ConnectionTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Connection Tree Service implementation
 *
 * Provides service to manage class connection tree object cache and to retrieve
 * manipulated data from cache.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Service
public class ConnectionTreeServiceImpl implements ConnectionTreeService {

    @Autowired
    private ConnectionTreeCacheService connectionTreeCacheService;

    @Override
    public ConnectionTree getByNodeId(Long nodeId) {
        return connectionTreeCacheService.getCache().get(nodeId);
    }

    @Override
    public void save(Long nodeId, ConnectionTree connectionTree) {
        connectionTreeCacheService.getCache().put(nodeId, connectionTree);
    }
}
