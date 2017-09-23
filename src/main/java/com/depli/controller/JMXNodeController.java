package com.depli.controller;

import com.depli.service.store.persistent.JMXNodeService;
import com.depli.store.persistent.entity.JMXNode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  public List<JMXNode> getAll() {
    return jmxNodeService.findAll();
  }

  @GetMapping("/{nodeId}")
  public JMXNode getByNodeId(@PathVariable Long nodeId) {
    return jmxNodeService.findByNodeId(nodeId);
  }

  @PostMapping("/save")
  public void save(JMXNode jmxNode) {
    jmxNodeService.save(jmxNode);
  }

  @DeleteMapping("/{nodeId}")
  public void deleteByNodeId(@PathVariable Long nodeId) {
    jmxNodeService.removeByNodeId(nodeId);
  }
}
