package com.depli.controller;

import com.depli.data.object.OperatingSystemData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.depli.DepliApplication.nodeDataMap;

/** DOperatingSystemMXBeanController
 * REST API to expose MemoryMXBean to frontend.
 *
 * Created by lpsandaruwan on 3/25/17.
 */

@RestController
@RequestMapping("/osdobjects")
public class DOperatingSystemMXBeanController {

    @RequestMapping(value = "/{nodeId}", method = RequestMethod.GET)
    public OperatingSystemData findClassLoadingDataById(@PathVariable long nodeId) {
        if(nodeDataMap.getByNodeId(nodeId).isInitialized()) {
            return nodeDataMap.getByNodeId(nodeId).getdOperatingSystemMXBean().getOperatingSystemData();
        }

        return null;
    }
}
