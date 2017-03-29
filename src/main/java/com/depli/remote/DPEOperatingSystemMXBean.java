package com.depli.remote;


import com.depli.data.object.PEOperatingSystemData;

import java.io.IOException;
import java.lang.management.ManagementFactory;

/** DPEOperatingSystemMXBEan
 *
 * Depli implementation for initializing remote com.sun.management.OperatingSystemMxBean
 * Load operating system monitoring interface of the remote
 *
 * Created by lpsandaruwan on 3/26/17.
 */

public class DPEOperatingSystemMXBean {

    private DJMXConnection djmxConnection;
    private PEOperatingSystemData peOperatingSystemData;

    private com.sun.management.OperatingSystemMXBean peOperatingSystemMXBean;

    public DPEOperatingSystemMXBean(DJMXConnection djmxConnection) {
        this.djmxConnection = djmxConnection;
        this.peOperatingSystemData = new PEOperatingSystemData();
    }

    public PEOperatingSystemData getPeOperatingSystemData() {
        return peOperatingSystemData;
    }

    public com.sun.management.OperatingSystemMXBean getPeOperatingSystemMXBean() {
        return peOperatingSystemMXBean;
    }

    // Initialize remote sun OperatingSystemMXBean
    public com.sun.management.OperatingSystemMXBean initialize() throws IOException {
        peOperatingSystemMXBean = ManagementFactory.newPlatformMXBeanProxy(
                djmxConnection.getmBeanServerConnection(),
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
