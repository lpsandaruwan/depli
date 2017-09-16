package com.depli.utility.mediator.impl;

import com.depli.service.store.descriptor.MemoryUsageDescriptorService;
import com.depli.utility.mediator.MemoryUsageMXBeanMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.management.MemoryMXBean;

/**
 * Memory usage MXBean mediator implementation.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Component
public class MemoryUsageMXBeanMediatorImpl implements MemoryUsageMXBeanMediator {

    @Autowired
    private MemoryUsageDescriptorService memoryUsageDescriptorService;

    @Async
    @Override
    public void mediateDynamicData(Long nodeId, MemoryMXBean memoryMXBean) {
        memoryUsageDescriptorService.getByNodeId(nodeId).setDynamicData(
                memoryMXBean.getHeapMemoryUsage(),
                memoryMXBean.getNonHeapMemoryUsage(),
                memoryMXBean.getObjectPendingFinalizationCount()
        );
    }
}
