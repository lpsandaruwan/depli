package com.depli.controller;

import com.depli.service.store.graph.ClassLoadingGraphDataService;
import com.depli.service.store.graph.ProcessingUnitGraphDataService;
import com.depli.store.cache.graph.ClassLoadingGraphData;
import com.depli.store.cache.graph.ProcessingUnitGraphData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Graph Data Controller
 *
 * Expose graph data via REST.
 *
 * @author lpsandaruwan
 * @since 9/20/17
 */

@RestController
@RequestMapping("/nodes")
public class GraphDataController {

  @Autowired
  private ClassLoadingGraphDataService classLoadingGraphDataService;

  @Autowired
  private ProcessingUnitGraphDataService processingUnitGraphDataService;

  @GetMapping("/{nodeId}/graphs/mainframes")
  public ProcessingUnitGraphData findProcessingUnitGraphDataByNodeId(@PathVariable Long nodeId) {
    return processingUnitGraphDataService.getByNodeId(nodeId);
  }

  @GetMapping("/{nodeId}/graphs/classes")
  public ClassLoadingGraphData findClassLoadingGraphDataByNodeId(@PathVariable Long nodeId) {
    return classLoadingGraphDataService.getByNodeId(nodeId);
  }
}
