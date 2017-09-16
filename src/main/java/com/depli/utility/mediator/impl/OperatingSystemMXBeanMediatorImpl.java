package com.depli.utility.mediator.impl;

import com.depli.service.store.descriptor.OperatingSystemDescriptorService;
import com.depli.utility.mediator.OperatingSystemMXBeanMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.management.OperatingSystemMXBean;

/**
 * Operating System MXBean mediator implementation.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Component
public class OperatingSystemMXBeanMediatorImpl implements OperatingSystemMXBeanMediator {

    @Autowired
    private OperatingSystemDescriptorService operatingSystemDescriptorService;

    @Override
    public void mediateStaticData(Long nodeId, OperatingSystemMXBean operatingSystemMXBean) {
        operatingSystemDescriptorService.getByNodeId(nodeId).setStaticData(
                operatingSystemMXBean.getArch(),
                operatingSystemMXBean.getAvailableProcessors(),
                operatingSystemMXBean.getName(),
                operatingSystemMXBean.getVersion()
        );
    }
}
