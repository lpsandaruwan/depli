package com.depli.controllers;

import com.depli.entities.JMXNode;
import com.depli.services.implementations.JMXNodeServiceImplementation;
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
    private JMXNodeServiceImplementation jmxNodeServiceImplementation;

    @RequestMapping(value = "/nodes", method = RequestMethod.GET)
    public List<JMXNode> getAllJMXNodes() {
        if (jmxNodeServiceImplementation.findAll() == null) {
            return null;
        }
        return jmxNodeServiceImplementation.findAll();
    }

    @RequestMapping(value = "/nodes/{nodeId}", method = RequestMethod.GET)
    public JMXNode getByNodeId(@PathVariable long nodeId) {
        if (jmxNodeServiceImplementation.findByNodeId(nodeId) == null) {
            return null;
        }
        return jmxNodeServiceImplementation.findByNodeId(nodeId);
    }

    @RequestMapping(value = "/nodes/save", method = RequestMethod.POST)
    public JMXNode saveNodeData(@RequestBody JMXNode jmxNode) {
        return jmxNodeServiceImplementation.save(jmxNode);
    }

    @RequestMapping(value = "/nodes/{nodeId}", method = RequestMethod.DELETE)
    public long deleteByNodeId(@PathVariable long nodeId) {
        return jmxNodeServiceImplementation.removeByNodeId(nodeId);
    }
}
