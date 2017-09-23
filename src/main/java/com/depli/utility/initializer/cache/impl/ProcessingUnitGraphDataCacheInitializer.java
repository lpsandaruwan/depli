package com.depli.utility.initializer.cache.impl;

import com.depli.service.store.graph.ProcessingUnitGraphDataService;
import com.depli.store.cache.graph.ProcessingUnitGraphData;
import com.depli.utility.initializer.cache.CacheInitializer;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CPU graph data cache initializer
 *
 * Initializes and store ProcessingUnitGraphData objects in ProcessingUnitGraphData cache store.
 *
 * @author lpsandaruwan
 * @since 9/19/17
 */

@Component
public class ProcessingUnitGraphDataCacheInitializer implements CacheInitializer {

  @Autowired
  private ProcessingUnitGraphDataService processingUnitGraphDataService;

  @Override
  public void initialize(Long nodeId) throws IOException {
    processingUnitGraphDataService.save(nodeId, new ProcessingUnitGraphData(20));
  }
}
