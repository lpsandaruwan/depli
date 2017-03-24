package com.depli.remote;

import com.depli.data.object.MemoryData;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * Depli implementation for loading remote MemoryMXBean
 * Load memory management interface of the remote JVM.
 *
 * Created by lpsandaruwan on 3/23/17.
 */

public class DMemoryMXBean {

    private DJMXConnection djmxConnection;
    private MemoryMXBean memoryMXBean;
    private MemoryData memoryData;

    public DMemoryMXBean(DJMXConnection djmxConnection) {
        this.djmxConnection = djmxConnection;
        this.memoryData = new MemoryData();
    }

    public MemoryMXBean getMemoryMXBean() {
        return memoryMXBean;
    }

    public MemoryData getMemoryData() {
        return memoryData;
    }

    // Initialize remote MemoryMXBean
    public MemoryMXBean initialize() throws IOException {
        memoryMXBean = ManagementFactory.newPlatformMXBeanProxy(
                djmxConnection.getmBeanServerConnection(),
                ManagementFactory.MEMORY_MXBEAN_NAME,
                MemoryMXBean.class
        );

        return memoryMXBean;
    }

    // Refresh and get MemoryData
    public MemoryData refreshData() {
        memoryData.setData(
                memoryMXBean.getHeapMemoryUsage(),
                memoryMXBean.getNonHeapMemoryUsage(),
                memoryMXBean.getObjectPendingFinalizationCount()
        );
        return memoryData;
    }
}
