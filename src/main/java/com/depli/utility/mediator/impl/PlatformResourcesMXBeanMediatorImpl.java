package com.depli.utility.mediator.impl;

import com.depli.service.store.descriptor.PlatformResourcesDescriptorService;
import com.depli.utility.mediator.PlatformResourcesMXBeanMediator;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Platform resources MXBean mediator implementation.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Component
public class PlatformResourcesMXBeanMediatorImpl implements PlatformResourcesMXBeanMediator {

    @Autowired
    private PlatformResourcesDescriptorService platformResourcesDescriptorService;

    @Async
    @Override
    public void mediateDynamicData(Long nodeId, OperatingSystemMXBean platformResourcesMXBean) {
        platformResourcesDescriptorService.getByNodeId(nodeId).setDynamicData(
                toFloat(platformResourcesMXBean.getFreePhysicalMemorySize()),
                toFloat(platformResourcesMXBean.getFreeSwapSpaceSize()),
                toFloat(platformResourcesMXBean.getSystemCpuLoad()),
                toFloat(platformResourcesMXBean.getProcessCpuLoad())
        );
    }

    @Override
    public void mediateStaticData(Long nodeId, OperatingSystemMXBean platformResourcesMXBean) {
        platformResourcesDescriptorService.getByNodeId(nodeId).setStaticData(
                toFloat(platformResourcesMXBean.getTotalPhysicalMemorySize()),
                toFloat(platformResourcesMXBean.getTotalSwapSpaceSize())
        );
    }

    /**
     * Convert average value to a percentage.
     */
    private static float toFloat(double value) {
        if(value == -1) {
            return -1;
        }
        return Math.round(value * 1000f) / 10f;
    }

    /**
     * Converts bytes value to Mega bytes.
     */
    private static float toFloat(long value) {
        if(value == -1) {
            return -1;
        }
        return Math.round((value / (1024f * 1024f)) * 10f) / 10f;
    }
}
