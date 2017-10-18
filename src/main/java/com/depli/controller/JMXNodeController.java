package com.depli.controller;

import static com.depli.constant.ErrorType.ERROR;
import static com.depli.constant.ErrorType.INITIALIZATION_FAILED;
import static com.depli.constant.ErrorType.INVALID_NODE_ID;

import com.depli.service.store.persistent.JMXNodeService;
import com.depli.store.persistent.entity.JMXNode;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    return new ResponseEntity<>(jmxNodeService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/{nodeId}")
  public ResponseEntity getByNodeId(@PathVariable Long nodeId) {
    JMXNode jmxNode = jmxNodeService.findByNodeId(nodeId);
    if (jmxNode == null) {
      Map<String, String> map = new HashMap<>();
      map.put(ERROR, INVALID_NODE_ID);
      return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(jmxNode, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Map> saveNewNode(@RequestBody JMXNode jmxNode) throws IOException {
    if (jmxNodeService.save(jmxNode)) {
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
    Map<String, String> map = new HashMap<>();
    map.put(ERROR, INITIALIZATION_FAILED);
    return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @PutMapping("/{nodeId}")
  public ResponseEntity<Map> updateByNodeId(@PathVariable Long nodeId,
      @RequestBody JMXNode jmxNode) {
    if (jmxNodeService.updateByNodeId(nodeId, jmxNode)) {
      return new ResponseEntity<>(HttpStatus.OK);
    }
    Map<String, String> map = new HashMap<>();
    map.put(ERROR, INVALID_NODE_ID);
    return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping("/{nodeId}")
  public ResponseEntity<Map> deleteByNodeId(@PathVariable Long nodeId) {
    if (jmxNodeService.removeByNodeId(nodeId)) {
      return new ResponseEntity<>(HttpStatus.OK);
    }
    Map<String, String> map = new HashMap<>();
    map.put(ERROR, INVALID_NODE_ID);
    return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
  }
}
