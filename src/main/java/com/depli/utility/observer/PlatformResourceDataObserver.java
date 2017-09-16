package com.depli.utility.observer;


import com.depli.store.cache.descriptor.PlatformResourcesDescriptor;

import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * DPEOperatingSystemMXBEan
 * <p>
 * Depli implementation for initializing observer com.sun.management.OperatingSystemMxBean
 * Load operating system monitoring interface of the observer
 * <p>
 * Created by lpsandaruwan on 3/26/17.
 */

public class PlatformResourceDataObserver {

    private JMXConnectionObserver jmxConnectionObserver;
    private PlatformResourcesDescriptor platformResourcesDescriptor;

    private com.sun.management.OperatingSystemMXBean peOperatingSystemMXBean;

    public PlatformResourceDataObserver(JMXConnectionObserver jmxConnectionObserver) {
        this.jmxConnectionObserver = jmxConnectionObserver;
        this.platformResourcesDescriptor = new PlatformResourcesDescriptor();
    }

    public PlatformResourcesDescriptor getPlatformResourcesDescriptor() {
        return platformResourcesDescriptor;
    }

    public com.sun.management.OperatingSystemMXBean getPeOperatingSystemMXBean() {
        return peOperatingSystemMXBean;
    }

    // Initialize observer sun OperatingSystemMXBean
    public com.sun.management.OperatingSystemMXBean initialize() throws IOException {
        peOperatingSystemMXBean = ManagementFactory.newPlatformMXBeanProxy(
                jmxConnectionObserver.getmBeanServerConnection(),
                ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME,
                com.sun.management.OperatingSystemMXBean.class
        );

        return peOperatingSystemMXBean;
    }

    // Refresh PlatformResourcesDescriptor
    public PlatformResourcesDescriptor refreshData() {
        platformResourcesDescriptor.setData(
                peOperatingSystemMXBean.getSystemCpuLoad(),
                peOperatingSystemMXBean.getFreeSwapSpaceSize(),
                peOperatingSystemMXBean.getFreePhysicalMemorySize(),
                peOperatingSystemMXBean.getProcessCpuLoad(),
                peOperatingSystemMXBean.getTotalPhysicalMemorySize(),
                peOperatingSystemMXBean.getTotalSwapSpaceSize()
        );
        return platformResourcesDescriptor;
    }
}
