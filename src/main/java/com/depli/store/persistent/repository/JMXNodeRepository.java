package com.depli.store.persistent.repository;

import com.depli.store.persistent.entity.JMXNode;
import java.util.List;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * JMXNode repository.
 *
 * @author lpsandaruwan
 * @since 3/22/17
 */


public interface JMXNodeRepository extends Repository<JMXNode, Long> {

  @Transactional(readOnly = true)
  List<JMXNode> findAll();

  @Transactional(readOnly = true)
  JMXNode findByNodeId(Long nodeId);

  @Transactional
  Long removeByNodeId(Long nodeId);

  @Transactional
  JMXNode save(JMXNode jmxNode);

  @Transactional(readOnly = true)
  boolean existsByNodeId(Long nodeId);
}
