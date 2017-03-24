package com.depli.service;

import com.depli.entity.JMXNode;
import com.depli.repository.JMXNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lpsandaruwan on 3/23/17.
 */

@Service
public class JMXNodeService {

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
