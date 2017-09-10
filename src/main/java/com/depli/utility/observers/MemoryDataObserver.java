package com.depli.utility.observers;

import com.depli.domain.descriptor.MemoryData;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

/**
 * MemoryDataObserver
 * <p>
 * Depli implementation for loading observers MemoryMXBean
 * Load memory management interface of the observers JVM.
 * <p>
 * Created by lpsandaruwan on 3/23/17.
 */

public class MemoryDataObserver {

    private JMXConnectionObserver JMXConnectionObserver;
    private MemoryMXBean memoryMXBean;
    private MemoryData memoryData;

    public MemoryDataObserver(JMXConnectionObserver JMXConnectionObserver) {
        this.JMXConnectionObserver = JMXConnectionObserver;
        this.memoryData = new MemoryData();
    }

    public MemoryMXBean getMemoryMXBean() {
        return memoryMXBean;
    }

    public MemoryData getMemoryData() {
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
    public MemoryData refreshData() {
        memoryData.setData(
                memoryMXBean.getHeapMemoryUsage(),
                memoryMXBean.getNonHeapMemoryUsage(),
                memoryMXBean.getObjectPendingFinalizationCount()
        );
        return memoryData;
    }
}
