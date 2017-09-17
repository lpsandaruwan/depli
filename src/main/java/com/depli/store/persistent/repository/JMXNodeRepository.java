package com.depli.store.persistent.repository;

import com.depli.store.persistent.entity.JMXNode;
import java.util.List;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lpsandaruwan on 3/22/17.
 */

@Transactional
public interface JMXNodeRepository extends Repository<JMXNode, Long> {

  // Returns a list of JMXNode objects
  List<JMXNode> findAll();

  // Find JMX node by hostname
  JMXNode findByHostname(String hostname);

  // Find JMX node information by node id
  JMXNode findByNodeId(long nodeId);

  // Delete JMX node entry from database
  Long removeByNodeId(long nodeId);

  // Add new JMX node information to database
  JMXNode save(JMXNode jmxNode);
}
