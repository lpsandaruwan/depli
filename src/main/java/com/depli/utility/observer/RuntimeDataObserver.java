package com.depli.utility.observer;

import com.depli.store.cache.descriptor.RuntimeData;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * RuntimeDataObserver
 * <p>
 * Depli implementation for loading observer RuntimeMXBean
 * Load runtime management interface of the observer
 * <p>
 * Created by lpsandaruwan on 3/23/17.
 */

public class RuntimeDataObserver {

    private JMXConnectionObserver jmxConnectionObserver;
    private RuntimeMXBean runtimeMXBean;
    private RuntimeData runtimeData;

    public RuntimeDataObserver(JMXConnectionObserver jmxConnectionObserver) {
        this.jmxConnectionObserver = jmxConnectionObserver;
        this.runtimeData = new RuntimeData();
    }

    public RuntimeMXBean getRuntimeMXBean() {
        return runtimeMXBean;
    }

    public RuntimeData getRuntimeData() {
        return runtimeData;
    }

    // Initialize observer RuntimeMXBean
    public RuntimeMXBean initialize() throws IOException {
        runtimeMXBean = ManagementFactory.newPlatformMXBeanProxy(
                jmxConnectionObserver.getmBeanServerConnection(),
                ManagementFactory.RUNTIME_MXBEAN_NAME,
                RuntimeMXBean.class
        );

        return runtimeMXBean;
    }

    // Refresh and get RuntimeDataObserver object
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
