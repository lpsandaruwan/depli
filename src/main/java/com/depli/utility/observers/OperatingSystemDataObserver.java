package com.depli.utility.observers;

import com.depli.domain.descriptor.OperatingSystemData;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 * OperatingSystemDataObserver
 * <p>
 * Depli implementation for initializing observers OperatingSystemMxBean
 * Load operating system monitoring interface of the observers
 * <p>
 * Created by lpsandaruwan on 3/23/17.
 */

public class OperatingSystemDataObserver {

    private JMXConnectionObserver JMXConnectionObserver;
    private OperatingSystemMXBean operatingSystemMXBean;
    private OperatingSystemData operatingSystemData;

    public OperatingSystemDataObserver(JMXConnectionObserver JMXConnectionObserver) {
        this.JMXConnectionObserver = JMXConnectionObserver;
        this.operatingSystemData = new OperatingSystemData();
    }

    public OperatingSystemMXBean getOperatingSystemMXBean() {
        return operatingSystemMXBean;
    }

    public OperatingSystemData getOperatingSystemData() {
        return operatingSystemData;
    }

    // Initialize observers OperatingSystemMXBean
    public OperatingSystemMXBean initialize() throws IOException {
        operatingSystemMXBean = ManagementFactory.newPlatformMXBeanProxy(
                JMXConnectionObserver.getmBeanServerConnection(),
                ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME,
                OperatingSystemMXBean.class
        );

        return operatingSystemMXBean;
    }

    // Refresh and get OperatingSystemDataObserver object
    public OperatingSystemData refreshData() {
        operatingSystemData.setData(
                operatingSystemMXBean.getArch(),
                operatingSystemMXBean.getAvailableProcessors(),
                operatingSystemMXBean.getName(),
                operatingSystemMXBean.getVersion()
        );
        return operatingSystemData;
    }
}
