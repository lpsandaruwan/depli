package com.depli.controller;

import com.depli.entity.JMXNode;
import com.depli.service.JMXNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** JMXNodeController
 * REST API to manage JMX node metadata and credentials.
 *
 * Created by lpsandaruwan on 3/25/17.
 */

@RestController
public class JMXNodeController {

    @Autowired
    private JMXNodeService jmxNodeService;

    @RequestMapping(value = "/nodes", method = RequestMethod.GET)
    public List<JMXNode> getAllJMXNodes() {
        return jmxNodeService.findAll();
    }

    @RequestMapping(value = "/nodes/{nodeId}", method = RequestMethod.GET)
    public JMXNode getByNodeId(@PathVariable long nodeId) {
        return jmxNodeService.findByNodeId(nodeId);
    }

    @RequestMapping(value = "/nodes/new", method = RequestMethod.POST)
    public JMXNode saveNodeData(@RequestBody JMXNode jmxNode) {
        return jmxNodeService.save(jmxNode);
    }

    @RequestMapping(value = "/nodes/{nodeId}", method = RequestMethod.DELETE)
    public long deleteByNodeId(@PathVariable long nodeId) {
        return jmxNodeService.removeByNodeId(nodeId);
    }
}
