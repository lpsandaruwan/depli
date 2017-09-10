package com.depli.controller;

import com.depli.store.cache.descriptor.PEOperatingSystemData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.depli.DepliApplication.nodeDataMap;

/**
 * DOperatingSystemMXBeanController
 * REST API to expose MemoryMXBean to frontend.
 * <p>
 * Created by lpsandaruwan on 3/29/17.
 */

@RestController
@RequestMapping("/peosdobjects")
public class PlatformSystemDataController {

    @RequestMapping(value = "/{nodeId}", method = RequestMethod.GET)
    public PEOperatingSystemData findClassLoadingDataById(@PathVariable long nodeId) {
        if (nodeDataMap.getByNodeId(nodeId).isInitialized()) {
            return nodeDataMap.getByNodeId(nodeId).getPlatformSystemDataObserver().getPeOperatingSystemData();
        }

        return null;
    }
}
