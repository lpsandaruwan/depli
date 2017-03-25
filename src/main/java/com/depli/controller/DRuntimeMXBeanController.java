package com.depli.controller;

import com.depli.data.object.RuntimeData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.depli.DepliApplication.nodeDataMap;

/** DRuntimeMXBeanController
 * REST API to expose MemoryMXBean to frontend.
 *
 * Created by lpsandaruwan on 3/25/17.
 */

@RestController
@RequestMapping("/rdobjects")
public class DRuntimeMXBeanController {

    @RequestMapping(value = "/{nodeId}", method = RequestMethod.GET)
    public RuntimeData findClassLoadingDataById(@PathVariable long nodeId) {
        return nodeDataMap.getByNodeId(nodeId).getdRuntimeMXBean().getRuntimeData();
    }
}