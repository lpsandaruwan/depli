package com.depli.service.store.persistent.impl;

import com.depli.service.store.persistent.JMXNodeService;
import com.depli.store.persistent.entity.JMXNode;
import com.depli.store.persistent.repository.JMXNodeRepository;
import com.depli.utility.initializer.InitializerFactory;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * JMX Node Service Implementation.
 *
 * @author lpsandaruwan
 * @since 3/23/17
 */

@Service
public class JMXNodeServiceImpl implements JMXNodeService {

  @Autowired
  private JMXNodeRepository jmxNodeRepository;

  @Autowired
  private InitializerFactory initializerFactory;

  @Override
  public List<JMXNode> findAll() {
    return jmxNodeRepository.findAll();
  }

  @Override
  public JMXNode findByNodeId(Long nodeId) {
    return jmxNodeRepository.findByNodeId(nodeId);

  }

  @Override
  public boolean updateByNodeId(Long nodeId, JMXNode jmxNode) {
    if (jmxNodeRepository.existsByNodeId(nodeId)) {
      jmxNodeRepository.save(jmxNode);
      return reinitializeDataStore();
    }
    return false;
  }

  @Override
  public boolean removeByNodeId(Long nodeId) {
    if (jmxNodeRepository.existsByNodeId(nodeId)) {
      jmxNodeRepository.removeByNodeId(nodeId);
      return reinitializeDataStore();
    }
    return false;
  }

  @Override
  public boolean save(JMXNode jmxNode) throws IOException {
    jmxNodeRepository.save(jmxNode);
    return reinitializeDataStore();
  }

  private boolean reinitializeDataStore() {
    try {
      initializerFactory.initialize();
    } catch (Exception e) {
      return false;
    }
    return true;
  }
}
