package com.depli.remote;

import com.depli.data.object.OperatingSystemData;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**DOperatingSystemMXBean
 *
 * Depli implementation for initializing remote OperatingSystemMxBean
 * Load operating system monitoring interface of the remote
 *
 * Created by lpsandaruwan on 3/23/17.
 */

public class DOperatingSystemMXBean {

    private DJMXConnection djmxConnection;
    private OperatingSystemMXBean operatingSystemMXBean;
    private OperatingSystemData operatingSystemData;

    public DOperatingSystemMXBean(DJMXConnection djmxConnection) {
        this.djmxConnection = djmxConnection;
        this.operatingSystemData = new OperatingSystemData();
    }

    public OperatingSystemMXBean getOperatingSystemMXBean() {
        return operatingSystemMXBean;
    }

    public OperatingSystemData getOperatingSystemData() {
        return operatingSystemData;
    }

    // Initialize remote OperatingSystemMXBean
    public OperatingSystemMXBean initialize() throws IOException {
        operatingSystemMXBean = ManagementFactory.newPlatformMXBeanProxy(
                djmxConnection.getmBeanServerConnection(),
                ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME,
                OperatingSystemMXBean.class
        );

        return operatingSystemMXBean;
    }

    // Refresh and get OperatingSystemData object
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
