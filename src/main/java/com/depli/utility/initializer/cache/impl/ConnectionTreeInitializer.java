package com.depli.utility.initializer.cache.impl;

import com.depli.service.builder.ConnectionTreeBuilderService;
import com.depli.service.store.connector.ConnectionTreeService;
import com.depli.store.cache.connector.ConnectionTree;
import com.depli.store.persistent.entity.JMXNode;
import com.depli.utility.initializer.cache.CacheInitializer;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ConnectionTree Cache Initializer implementation
 *
 * Initializes and store ConnectionTree objects in ConnectionTree cache store.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Component
public class ConnectionTreeInitializer implements CacheInitializer {

  @Autowired
  private ConnectionTreeBuilderService connectionTreeBuilderService;

  @Autowired
  private ConnectionTreeService connectionTreeService;

  /**
   * Initializes ConnectionTree and store them in ConnectionTree cache store for given JMXNode
   * entity.
   *
   * @param jmxNode JMXNode entity
   */
  public void initialize(JMXNode jmxNode) throws IOException {
    ConnectionTree connectionTree = connectionTreeBuilderService.getTree(jmxNode);
    connectionTreeService.save(jmxNode.getNodeId(), connectionTree);
  }

  /**
   * Initializes elements and store them in appropriate cache store for given JMXNode entity Id.
   *
   * @param nodeId nodeId of JMXNode entity
   */
  @Override
  public void initialize(Long nodeId) throws IOException {
    // Do nothing prior to above method.
  }
}
