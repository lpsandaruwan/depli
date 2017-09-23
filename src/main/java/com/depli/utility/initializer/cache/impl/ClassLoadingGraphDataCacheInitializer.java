package com.depli.utility.initializer.cache.impl;

import com.depli.service.store.graph.ClassLoadingGraphDataService;
import com.depli.store.cache.graph.ClassLoadingGraphData;
import com.depli.utility.initializer.cache.CacheInitializer;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class Loading Graph Data Cache Initializer
 *
 * Initializes and store ClassLoadingGraphData objects in ClassLoadingGraphData cache store.
 *
 * @author lpsandaruwan
 * @author 9/23/17
 */

@Component
public class ClassLoadingGraphDataCacheInitializer implements CacheInitializer {

  @Autowired
  private ClassLoadingGraphDataService classLoadingGraphDataService;

  @Override
  public void initialize(Long nodeId) throws IOException {
    classLoadingGraphDataService.save(nodeId, new ClassLoadingGraphData(20));
  }
}
