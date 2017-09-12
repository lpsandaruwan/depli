package com.depli.utility.observer;


import com.depli.store.cache.descriptor.PlatformSystemDescriptor;

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

public class PlatformSystemDataObserver {

    private JMXConnectionObserver jmxConnectionObserver;
    private PlatformSystemDescriptor platformSystemDescriptor;

    private com.sun.management.OperatingSystemMXBean peOperatingSystemMXBean;

    public PlatformSystemDataObserver(JMXConnectionObserver jmxConnectionObserver) {
        this.jmxConnectionObserver = jmxConnectionObserver;
        this.platformSystemDescriptor = new PlatformSystemDescriptor();
    }

    public PlatformSystemDescriptor getPlatformSystemDescriptor() {
        return platformSystemDescriptor;
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

    // Refresh PlatformSystemDescriptor
    public PlatformSystemDescriptor refreshData() {
        platformSystemDescriptor.setData(
                peOperatingSystemMXBean.getSystemCpuLoad(),
                peOperatingSystemMXBean.getFreeSwapSpaceSize(),
                peOperatingSystemMXBean.getFreePhysicalMemorySize(),
                peOperatingSystemMXBean.getProcessCpuLoad(),
                peOperatingSystemMXBean.getTotalPhysicalMemorySize(),
                peOperatingSystemMXBean.getTotalSwapSpaceSize()
        );
        return platformSystemDescriptor;
    }
}
