package com.depli.controller;

import com.depli.store.cache.descriptor.RuntimeDescriptor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.depli.DepliApplication.nodeDataMap;

/**
 * DRuntimeMXBeanController
 * REST API to expose MemoryMXBean to frontend.
 * <p>
 * Created by lpsandaruwan on 3/25/17.
 */

@RestController
@RequestMapping("/rdobjects")
public class RuntimeDataController {

    @RequestMapping(value = "/{nodeId}", method = RequestMethod.GET)
    public RuntimeDescriptor findClassLoadingDataById(@PathVariable long nodeId) {

        if (nodeDataMap.getByNodeId(nodeId).isInitialized()) {
            return nodeDataMap.getByNodeId(nodeId).getRuntimeDataObserver().getRuntimeDescriptor();
        }

        return null;
    }
}