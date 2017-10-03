package com.depli.service.store.cache.graph.impl;

import com.depli.service.store.cache.BaseCacheServiceImplTest;
import com.depli.service.store.cache.graph.ProcessingUnitGraphDataCacheService;
import org.mockito.InjectMocks;

import static com.depli.constant.CacheName.CPU_GRAPH_DATA_CACHE;

public class ProcessingUnitGraphDataCacheServiceImplTest extends BaseCacheServiceImplTest<ProcessingUnitGraphDataCacheService> {

    @InjectMocks
    private ProcessingUnitGraphDataCacheServiceImpl processingUnitGraphDataCacheService;

    @Override
    protected ProcessingUnitGraphDataCacheService cacheService() {
        return processingUnitGraphDataCacheService;
    }

    @Override
    protected String cacheKey() {
        return CPU_GRAPH_DATA_CACHE;
    }

}
