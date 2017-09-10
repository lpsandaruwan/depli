package com.depli.controller;

import com.depli.store.cache.descriptor.ThreadData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.depli.DepliApplication.nodeDataMap;


/**
 * DThreadMXBeanController
 * REST API to expose MemoryMXBean to frontend.
 * <p>
 * Created by lpsandaruwan on 3/25/17.
 */

@RestController
@RequestMapping("/tdobjects")
public class ThreadDataController {

    @RequestMapping(value = "/{nodeId}", method = RequestMethod.GET)
    public ThreadData findClassLoadingDataById(@PathVariable long nodeId) {
        if (nodeDataMap.getByNodeId(nodeId).isInitialized()) {
            return nodeDataMap.getByNodeId(nodeId).getThreadDataObserver().getThreadData();
        }

        return null;
    }
}
