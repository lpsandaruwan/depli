package com.depli.controller;

import com.depli.service.store.graph.ClassLoadingGraphDataService;
import com.depli.service.store.graph.ProcessingUnitGraphDataService;
import com.depli.store.cache.graph.ClassLoadingGraphData;
import com.depli.store.cache.graph.ProcessingUnitGraphData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Graph Data Controller
 *
 * REST controller to expose graphs related data sets.
 *
 * @author lpsandaruwan
 * @since 9/20/17
 */

@RestController
@RequestMapping("/graphs")
public class GraphDataController {

  @Autowired
  private ClassLoadingGraphDataService classLoadingGraphDataService;

  @Autowired
  private ProcessingUnitGraphDataService processingUnitGraphDataService;

  @GetMapping("/mainframes/{descriptorIndex}")
  public ResponseEntity<ProcessingUnitGraphData> findProcessingUnitGraphDataByNodeId(
      @PathVariable Long descriptorIndex) {
    return new ResponseEntity<>(processingUnitGraphDataService.getByNodeId(descriptorIndex),
        HttpStatus.OK);
  }

  @GetMapping("/classes/{descriptorIndex}")
  public ResponseEntity<ClassLoadingGraphData> findClassLoadingGraphDataByNodeId(
      @PathVariable Long descriptorIndex) {
    return new ResponseEntity<>(classLoadingGraphDataService.getByNodeId(descriptorIndex),
        HttpStatus.OK);
  }
}
