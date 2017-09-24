package com.depli.service.store.persistent.impl;

import static com.depli.constant.ErrorType.ERROR;
import static com.depli.constant.ErrorType.INITIALIZATION_FAILED;
import static com.depli.constant.ErrorType.INVALID_NODE_ID;

import com.depli.service.store.persistent.JMXNodeService;
import com.depli.store.persistent.entity.JMXNode;
import com.depli.store.persistent.repository.JMXNodeRepository;
import com.depli.utility.initializer.InitializerFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author lpsandaruwan
 * @since 3/23/17
 */

@Component
public class JMXNodeServiceImpl implements JMXNodeService {

  @Autowired
  private JMXNodeRepository jmxNodeRepository;

  @Autowired
  private InitializerFactory initializerFactory;

  @Override
  public ResponseEntity<List<JMXNode>> findAll() {
    return new ResponseEntity<>(jmxNodeRepository.findAll(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity findByNodeId(Long nodeId) {
    JMXNode jmxNode = jmxNodeRepository.findByNodeId(nodeId);
    if (jmxNode == null) {
      Map<String, String> map = new HashMap<>();
      map.put(ERROR, INVALID_NODE_ID);
      return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(jmxNode, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Map> updateByNodeId(Long nodeId, JMXNode jmxNode) {
    if (jmxNodeRepository.existsByNodeId(nodeId)) {
      jmxNodeRepository.save(jmxNode);
      return reinitializeDataStore();
    }
    Map<String, String> map = new HashMap<>();
    map.put(ERROR, INVALID_NODE_ID);
    return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
  }

  @Override
  public ResponseEntity<Map> removeByNodeId(Long nodeId) {
    if (jmxNodeRepository.existsByNodeId(nodeId)) {
      jmxNodeRepository.removeByNodeId(nodeId);
      return reinitializeDataStore();
    }
    Map<String, String> map = new HashMap<>();
    map.put(ERROR, INVALID_NODE_ID);
    return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
  }

  @Override
  public ResponseEntity<Map> save(JMXNode jmxNode) throws IOException {
    jmxNodeRepository.save(jmxNode);
    return reinitializeDataStore();
  }

  private ResponseEntity<Map> reinitializeDataStore() {
    try {
      initializerFactory.initialize();
    } catch (Exception e) {
      Map<String, String> map = new HashMap<>();
      map.put(ERROR, INITIALIZATION_FAILED);
      return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
