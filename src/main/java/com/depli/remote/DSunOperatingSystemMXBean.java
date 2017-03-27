package com.depli.remote;


import java.io.IOException;
import java.lang.management.ManagementFactory;

/** DSunOperatingSystemMXBEan
 *
 * Depli implementation for initializing remote com.sun.management.OperatingSystemMxBean
 * Load operating system monitoring interface of the remote
 *
 * Created by lpsandaruwan on 3/26/17.
 */

public class DSunOperatingSystemMXBean {

    private DJMXConnection djmxConnection;

    private com.sun.management.OperatingSystemMXBean sunOperatingSystemMXBean;

    public DSunOperatingSystemMXBean(DJMXConnection djmxConnection) {
        this.djmxConnection = djmxConnection;
    }

    public com.sun.management.OperatingSystemMXBean getSunOperatingSystemMXBean() {
        return sunOperatingSystemMXBean;
    }

    // Initialize remote sun OperatingSystemMXBean
    public com.sun.management.OperatingSystemMXBean initialize() throws IOException {
        sunOperatingSystemMXBean = ManagementFactory.newPlatformMXBeanProxy(
                djmxConnection.getmBeanServerConnection(),
                ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME,
                com.sun.management.OperatingSystemMXBean.class
        );

        return sunOperatingSystemMXBean;
    }
}
