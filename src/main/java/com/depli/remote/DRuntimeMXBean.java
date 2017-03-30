package com.depli.remote;

import com.depli.data.object.RuntimeData;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/** DRuntimeMXBean
 *
 * Depli implementation for loading remote RuntimeMXBean
 * Load runtime management interface of the remote
 *
 * Created by lpsandaruwan on 3/23/17.
 */

public class DRuntimeMXBean {

    private DJMXConnection djmxConnection;
    private RuntimeMXBean runtimeMXBean;
    private RuntimeData runtimeData;

    public DRuntimeMXBean(DJMXConnection djmxConnection) {
        this.djmxConnection = djmxConnection;
        this.runtimeData = new RuntimeData();
    }

    public RuntimeMXBean getRuntimeMXBean() {
        return runtimeMXBean;
    }

    public RuntimeData getRuntimeData() {
        return runtimeData;
    }

    // Initialize remote RuntimeMXBean
    public RuntimeMXBean initialize() throws IOException {
        runtimeMXBean = ManagementFactory.newPlatformMXBeanProxy(
                djmxConnection.getmBeanServerConnection(),
                ManagementFactory.RUNTIME_MXBEAN_NAME,
                RuntimeMXBean.class
        );

        return runtimeMXBean;
    }

    // Refresh and get RuntimeData object
    public RuntimeData refreshData() {
        runtimeData.setData(
                runtimeMXBean.getBootClassPath(),
                runtimeMXBean.getClassPath(),
                runtimeMXBean.getInputArguments(),
                runtimeMXBean.getLibraryPath(),
                runtimeMXBean.getName(),
                runtimeMXBean.getSpecName(),
                runtimeMXBean.getStartTime(),
                runtimeMXBean.getVmName(),
                runtimeMXBean.getVmVendor(),
                runtimeMXBean.getVmVersion()
                );
        return runtimeData;
    }
}
