package com.depli.utility.mediator.impl;

import com.depli.service.store.descriptor.RuntimeDescriptorService;
import com.depli.utility.mediator.RuntimeMXBeanMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.management.RuntimeMXBean;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

/**
 * Runtime MXBean mediator implementation.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Component
public class RuntimeMXBeanMediatorImpl implements RuntimeMXBeanMediator {

    @Autowired
    private RuntimeDescriptorService runtimeDescriptorService;

    @Async
    @Override
    public void mediateDynamicData(Long nodeId, RuntimeMXBean runtimeMXBean) {
        long jvmUptime = runtimeMXBean.getUptime();
        runtimeDescriptorService.getByNodeId(nodeId).setDynamicData(
                String.format(
                        "%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(jvmUptime),
                        TimeUnit.MILLISECONDS.toMinutes(jvmUptime) % TimeUnit.HOURS.toMinutes(1),
                        TimeUnit.MILLISECONDS.toSeconds(jvmUptime) % TimeUnit.MINUTES.toSeconds(1)
                )
        );
    }

    @Override
    public void mediateStaticData(Long nodeId, RuntimeMXBean runtimeMXBean) {
        runtimeDescriptorService.getByNodeId(nodeId).setPathInfoData(
                runtimeMXBean.getBootClassPath().split(":", -1),
                runtimeMXBean.getClassPath().split(":", -1),
                runtimeMXBean.getInputArguments(),
                runtimeMXBean.getLibraryPath().split("::", -1)
        );

        runtimeDescriptorService.getByNodeId(nodeId).setJvmMetadata(
                runtimeMXBean.getName(),
                runtimeMXBean.getSpecName(),
                String.valueOf(
                        LocalDateTime.ofInstant(
                                Instant.ofEpochMilli(runtimeMXBean.getStartTime()),
                                ZoneId.systemDefault()
                        )).replace("T", " "),
                runtimeMXBean.getVmName(),
                runtimeMXBean.getVmVendor(),
                runtimeMXBean.getVmVersion()
        );
    }
}
