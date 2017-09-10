package com.depli.service.impl;

import com.depli.domain.persistent.entity.JMXNode;
import com.depli.domain.persistent.repository.JMXNodeRepository;
import com.depli.service.JMXNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lpsandaruwan on 3/23/17.
 */

@Component
public class JMXNodeServiceImpl implements JMXNodeService {

    @Autowired
    private JMXNodeRepository jmxNodeRepository;

    // Returns list of JMXNode objects
    public List<JMXNode> findAll() {
        return jmxNodeRepository.findAll();
    }

    // Find JMXNode object by node ID
    public JMXNode findByNodeId(long nodeId) {
        return jmxNodeRepository.findByNodeId(nodeId);
    }

    // Delete JMXNode entry from database
    public Long removeByNodeId(long nodeId) {
        return jmxNodeRepository.removeByNodeId(nodeId);
    }

    // Save JMXNode entry in database
    public JMXNode save(JMXNode jmxNode) {
        return jmxNodeRepository.save(jmxNode);
    }
}
