package com.depli.utility.observer;

import com.depli.store.cache.descriptor.MemoryData;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

/**
 * MemoryDataObserver
 * <p>
 * Depli implementation for loading observer MemoryMXBean
 * Load memory management interface of the observer JVM.
 * <p>
 * Created by lpsandaruwan on 3/23/17.
 */

public class MemoryDataObserver {

    private JMXConnectionObserver jmxConnectionObserver;
    private MemoryMXBean memoryMXBean;
    private MemoryData memoryData;

    public MemoryDataObserver(JMXConnectionObserver jmxConnectionObserver) {
        this.jmxConnectionObserver = jmxConnectionObserver;
        this.memoryData = new MemoryData();
    }

    public MemoryMXBean getMemoryMXBean() {
        return memoryMXBean;
    }

    public MemoryData getMemoryData() {
        return memoryData;
    }

    // Initialize observer MemoryMXBean
    public MemoryMXBean initialize() throws IOException {
        memoryMXBean = ManagementFactory.newPlatformMXBeanProxy(
                jmxConnectionObserver.getmBeanServerConnection(),
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
