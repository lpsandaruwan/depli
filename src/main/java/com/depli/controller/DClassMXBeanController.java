package com.depli.controller;

import com.depli.data.object.ClassLoadingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.depli.DepliApplication.nodeDataMap;

/** ClassDataController
 * REST API to expose ClassLoadingMXBean to frontend.
 *
 * Created by lpsandaruwan on 3/25/17.
 */

@RestController
@RequestMapping("/cdobjects")
public class DClassMXBeanController {

    @RequestMapping(value = "/{nodeId}", method = RequestMethod.GET)
    public ClassLoadingData findClassLoadingDataById(@PathVariable long nodeId) {
        return nodeDataMap.getByNodeId(nodeId).getdClassLoadingMXBean().getClassLoadingData();
    }
}
