package com.depli.utilities.observers;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

/** MemoryDataObserver
 *
 * Depli implementation for loading observers MemoryMXBean
 * Load memory management interface of the observers JVM.
 *
 * Created by lpsandaruwan on 3/23/17.
 */

public class MemoryDataObserver {

    private JMXConnectionObserver JMXConnectionObserver;
    private MemoryMXBean memoryMXBean;
    private com.depli.data.object.MemoryData memoryData;

    public MemoryDataObserver(JMXConnectionObserver JMXConnectionObserver) {
        this.JMXConnectionObserver = JMXConnectionObserver;
        this.memoryData = new com.depli.data.object.MemoryData();
    }

    public MemoryMXBean getMemoryMXBean() {
        return memoryMXBean;
    }

    public com.depli.data.object.MemoryData getMemoryData() {
        return memoryData;
    }

    // Initialize observers MemoryMXBean
    public MemoryMXBean initialize() throws IOException {
        memoryMXBean = ManagementFactory.newPlatformMXBeanProxy(
                JMXConnectionObserver.getmBeanServerConnection(),
                ManagementFactory.MEMORY_MXBEAN_NAME,
                MemoryMXBean.class
        );

        return memoryMXBean;
    }

    // Refresh and get MemoryDataObserver
    public com.depli.data.object.MemoryData refreshData() {
        memoryData.setData(
                memoryMXBean.getHeapMemoryUsage(),
                memoryMXBean.getNonHeapMemoryUsage(),
                memoryMXBean.getObjectPendingFinalizationCount()
        );
        return memoryData;
    }
}
