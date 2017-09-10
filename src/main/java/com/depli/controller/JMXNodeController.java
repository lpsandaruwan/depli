package com.depli.controller;

import com.depli.domain.persistent.entity.JMXNode;
import com.depli.service.impl.JMXNodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * JMXNodeController
 * REST API to manage JMX node metadata and credentials.
 * <p>
 * Created by lpsandaruwan on 3/25/17.
 */

@RestController
public class JMXNodeController {

    @Autowired
    private JMXNodeServiceImpl jmxNodeServiceImpl;

    @RequestMapping(value = "/nodes", method = RequestMethod.GET)
    public List<JMXNode> getAllJMXNodes() {
        if (jmxNodeServiceImpl.findAll() == null) {
            return null;
        }
        return jmxNodeServiceImpl.findAll();
    }

    @RequestMapping(value = "/nodes/{nodeId}", method = RequestMethod.GET)
    public JMXNode getByNodeId(@PathVariable long nodeId) {
        if (jmxNodeServiceImpl.findByNodeId(nodeId) == null) {
            return null;
        }
        return jmxNodeServiceImpl.findByNodeId(nodeId);
    }

    @RequestMapping(value = "/nodes/save", method = RequestMethod.POST)
    public JMXNode saveNodeData(@RequestBody JMXNode jmxNode) {
        return jmxNodeServiceImpl.save(jmxNode);
    }

    @RequestMapping(value = "/nodes/{nodeId}", method = RequestMethod.DELETE)
    public long deleteByNodeId(@PathVariable long nodeId) {
        return jmxNodeServiceImpl.removeByNodeId(nodeId);
    }
}
