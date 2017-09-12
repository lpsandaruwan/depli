package com.depli.utility.observer;

import com.depli.store.cache.descriptor.MemoryUsageDescriptor;

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
    private MemoryUsageDescriptor memoryUsageDescriptor;

    public MemoryDataObserver(JMXConnectionObserver jmxConnectionObserver) {
        this.jmxConnectionObserver = jmxConnectionObserver;
        this.memoryUsageDescriptor = new MemoryUsageDescriptor();
    }

    public MemoryMXBean getMemoryMXBean() {
        return memoryMXBean;
    }

    public MemoryUsageDescriptor getMemoryUsageDescriptor() {
        return memoryUsageDescriptor;
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
    public MemoryUsageDescriptor refreshData() {
        memoryUsageDescriptor.setData(
                memoryMXBean.getHeapMemoryUsage(),
                memoryMXBean.getNonHeapMemoryUsage(),
                memoryMXBean.getObjectPendingFinalizationCount()
        );
        return memoryUsageDescriptor;
    }
}
