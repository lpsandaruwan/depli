package com.depli.controller;

import com.depli.store.cache.descriptor.OperatingSystemDescriptor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.depli.DepliApplication.nodeDataMap;


/**
 * DOperatingSystemMXBeanController
 * REST API to expose MemoryMXBean to frontend.
 * <p>
 * Created by lpsandaruwan on 3/25/17.
 */

@RestController
@RequestMapping("/osdobjects")
public class OperatingSystemDataController {

    @RequestMapping(value = "/{nodeId}", method = RequestMethod.GET)
    public OperatingSystemDescriptor findClassLoadingDataById(@PathVariable long nodeId) {
        if (nodeDataMap.getByNodeId(nodeId).isInitialized()) {
            return nodeDataMap.getByNodeId(nodeId).getOperatingSystemDataObserver().getOperatingSystemDescriptor();
        }

        return null;
    }
}
