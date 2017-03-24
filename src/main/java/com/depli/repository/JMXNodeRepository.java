package com.depli.repository;

import com.depli.entity.JMXNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lpsandaruwan on 3/22/17.
 */

@Transactional
public interface JMXNodeRepository extends JpaRepository<JMXNode, Long> {

    // Returns a list of JMXNode objects
    List<JMXNode> findAll();

    // Find JMX node information by node id
    JMXNode findByNodeId(long nodeId);

    // Delete JMX node entry from database
    Long removeByNodeId(long nodeId);

    // Add new JMX node information to database
    JMXNode save(JMXNode jmxNode);
}
