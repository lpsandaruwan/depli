package com.depli.service.store.graph.impl;

import com.depli.service.store.cache.graph.ProcessingUnitGraphDataCacheService;
import com.depli.service.store.graph.ProcessingUnitGraphDataService;
import com.depli.store.cache.graph.ProcessingUnitGraphData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Processing unit graph data service implementation
 *
 * @author lpsandaruwan
 * @since 9/19/17
 */

@Service
public class ProcessingUnitGraphDataServiceImpl implements ProcessingUnitGraphDataService {

  @Autowired
  private ProcessingUnitGraphDataCacheService processingUnitGraphDataCacheService;

  @Override
  public ProcessingUnitGraphData getByNodeId(Long nodeId) {
    return processingUnitGraphDataCacheService.getCache().get(nodeId);
  }

  @Override
  public void save(Long nodeId, ProcessingUnitGraphData processingUnitGraphData) {
    processingUnitGraphDataCacheService.getCache().put(nodeId, processingUnitGraphData);
  }
}
