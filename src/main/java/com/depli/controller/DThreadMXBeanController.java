package com.depli.controller;

import com.depli.data.object.ThreadData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.depli.DepliApplication.nodeDataMap;

/** DThreadMXBeanController
 * REST API to expose MemoryMXBean to frontend.
 *
 * Created by lpsandaruwan on 3/25/17.
 */

@RestController
@RequestMapping("/tdobjects")
public class DThreadMXBeanController {

    @RequestMapping(value = "/{nodeId}", method = RequestMethod.GET)
    public ThreadData findClassLoadingDataById(@PathVariable long nodeId) {
        if(nodeDataMap.getByNodeId(nodeId) != null && nodeDataMap.getByNodeId(nodeId).isInitialized()) {
            return nodeDataMap.getByNodeId(nodeId).getdThreadMXBean().getThreadData();
        }

        return null;
    }
}
