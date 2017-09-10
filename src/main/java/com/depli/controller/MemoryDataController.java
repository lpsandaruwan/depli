package com.depli.controller;

import com.depli.domain.descriptor.MemoryData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.depli.DepliApplication.nodeDataMap;

/**
 * MemoryDataController
 * REST API to expose MemoryMXBean to frontend.
 * <p>
 * Created by lpsandaruwan on 3/25/17.
 */

@RestController
@RequestMapping("/mdobjects")
public class MemoryDataController {

    @RequestMapping(value = "/{nodeId}", method = RequestMethod.GET)
    public MemoryData findClassLoadingDataById(@PathVariable long nodeId) {
        if (nodeDataMap.getByNodeId(nodeId).isInitialized()) {
            return nodeDataMap.getByNodeId(nodeId).getMemoryDataObserver().getMemoryData();
        }

        return null;
    }
}
