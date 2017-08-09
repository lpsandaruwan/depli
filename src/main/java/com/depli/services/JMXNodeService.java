package com.depli.services;

import com.depli.entities.JMXNode;

import java.util.List;

/**
 * Created by Lahiru Pathirage on 8/6/2017.
 */

public interface JMXNodeService {
    public List<JMXNode> findAll();

    public JMXNode findByNodeId(long nodeId);

    public Long removeByNodeId(long nodeId);

    public JMXNode save(JMXNode jmxNode);
}
