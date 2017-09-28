package com.depli.service.store.persistent;

import com.depli.store.persistent.entity.JMXNode;
import java.io.IOException;
import java.util.List;

/**
 * JMXNode service
 *
 * Service to manipulate JMXNode entities.
 *
 * @author lpsandaruwan
 * @since 8/6/2017
 */

public interface JMXNodeService {

  /**
   * Retrieves JMXNode entities.
   *
   * @return List of JMXNode entities
   */
  public List<JMXNode> findAll();

  /**
   * Returns the JMXNode entity for given node Id.
   *
   * @param nodeId JMXNode Id.
   * @return JMXNode entity for given node Id
   */
  public JMXNode findByNodeId(Long nodeId);

  /**
   * Updates JMXNode entity and persists.
   *
   * @param nodeId node Id of the JMXNode entity to be updated.
   * @param jmxNode JMXNode object with data which needs to be updated
   */
  public boolean updateByNodeId(Long nodeId, JMXNode jmxNode);

  /**
   * Removes persisted JMXNode entity.
   *
   * @param nodeId JMXNode entity Id
   */
  public boolean removeByNodeId(Long nodeId);

  /**
   * Persists new JMXNode object.
   *
   * @param jmxNode JMXNode object
   */
  public boolean save(JMXNode jmxNode) throws IOException;
}
