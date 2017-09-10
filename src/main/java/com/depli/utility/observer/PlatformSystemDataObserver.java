package com.depli.utility.observer;


import com.depli.store.cache.descriptor.PEOperatingSystemData;

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
    private PEOperatingSystemData peOperatingSystemData;

    private com.sun.management.OperatingSystemMXBean peOperatingSystemMXBean;

    public PlatformSystemDataObserver(JMXConnectionObserver jmxConnectionObserver) {
        this.jmxConnectionObserver = jmxConnectionObserver;
        this.peOperatingSystemData = new PEOperatingSystemData();
    }

    public PEOperatingSystemData getPeOperatingSystemData() {
        return peOperatingSystemData;
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

    // Refresh PEOperatingSystemData
    public PEOperatingSystemData refreshData() {
        peOperatingSystemData.setData(
                peOperatingSystemMXBean.getSystemCpuLoad(),
                peOperatingSystemMXBean.getFreeSwapSpaceSize(),
                peOperatingSystemMXBean.getFreePhysicalMemorySize(),
                peOperatingSystemMXBean.getProcessCpuLoad(),
                peOperatingSystemMXBean.getTotalPhysicalMemorySize(),
                peOperatingSystemMXBean.getTotalSwapSpaceSize()
        );
        return peOperatingSystemData;
    }
}
