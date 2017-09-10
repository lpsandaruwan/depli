package com.depli.controller;

import com.depli.store.cache.descriptor.StatisticsData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.depli.DepliApplication.nodeDataMap;

/**
 * StatsController
 * Expose calculated values and depli system functions to the frontend.
 * <p>
 * Created by lpsandaruwan on 3/26/17.
 */

@RestController
@RequestMapping(value = "/stats")
public class StatisticsController {

    @RequestMapping(value = "/{nodeId}", method = RequestMethod.GET)
    public StatisticsData getStatisticsByNodeId(@PathVariable long nodeId) {
        if (nodeDataMap.getByNodeId(nodeId).isInitialized()) {
            return nodeDataMap.getByNodeId(nodeId).getStatisticsData();
        }

        return null;
    }
}
