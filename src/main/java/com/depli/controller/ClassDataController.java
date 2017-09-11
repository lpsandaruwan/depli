package com.depli.controller;

import com.depli.store.cache.descriptor.ClassLoadingDataDescriptor;
import com.depli.service.store.cache.ClassLoadingDataDescriptorCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.depli.DepliApplication.nodeDataMap;

/**
 * ClassDataController
 * REST API to expose ClassLoadingMXBean to frontend.
 * <p>
 * Created by lpsandaruwan on 3/25/17.
 */

@RestController
@RequestMapping("/cdobjects")
public class ClassDataController {

    @Autowired
    private ClassLoadingDataDescriptorCacheService classLoadingDataDescriptorCacheService;

    @RequestMapping(value = "/{nodeId}", method = RequestMethod.GET)
    public ClassLoadingDataDescriptor findClassLoadingDataById(@PathVariable long nodeId) {
        if (nodeDataMap.getByNodeId(nodeId).isInitialized()) {
            return classLoadingDataDescriptorCacheService.getCache().get(nodeId);
        }

        return null;
    }
}
