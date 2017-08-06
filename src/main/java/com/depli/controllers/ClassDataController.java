package com.depli.controllers;

import com.depli.data.object.ClassLoadingData;
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

    @RequestMapping(value = "/{nodeId}", method = RequestMethod.GET)
    public ClassLoadingData findClassLoadingDataById(@PathVariable long nodeId) {
        if (nodeDataMap.getByNodeId(nodeId).isInitialized()) {
            return nodeDataMap.getByNodeId(nodeId).getClassLoadingDataObserver().getClassLoadingData();
        }

        return null;
    }
}
