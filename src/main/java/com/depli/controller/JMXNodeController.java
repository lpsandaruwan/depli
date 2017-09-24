package com.depli.controller;

import com.depli.service.store.persistent.JMXNodeService;
import com.depli.store.persistent.entity.JMXNode;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * JMXNodeController
 *
 * REST API controller to expose methods to manipulate JMXNode entities.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@RestController
@RequestMapping(value = "/nodes")
public class JMXNodeController {

  @Autowired
  private JMXNodeService jmxNodeService;

  @GetMapping
  public ResponseEntity<List<JMXNode>> getAll() {
    return jmxNodeService.findAll();
  }

  @GetMapping("/{nodeId}")
  public ResponseEntity getByNodeId(@PathVariable Long nodeId) {
    return jmxNodeService.findByNodeId(nodeId);
  }

  @PostMapping
  public ResponseEntity<Map> saveNewNode(@RequestBody JMXNode jmxNode) throws IOException {
    return jmxNodeService.save(jmxNode);
  }

  @PutMapping("/{nodeId}")
  public ResponseEntity<Map> updateByNodeId(@PathVariable Long nodeId,
      @RequestBody JMXNode jmxNode) {
    return jmxNodeService.updateByNodeId(nodeId, jmxNode);
  }

  @DeleteMapping("/{nodeId}")
  public ResponseEntity<Map> deleteByNodeId(@PathVariable Long nodeId) {
    return jmxNodeService.removeByNodeId(nodeId);
  }
}
