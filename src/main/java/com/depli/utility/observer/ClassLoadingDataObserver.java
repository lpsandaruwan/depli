package com.depli.utility.observer;

import com.depli.store.cache.descriptor.ClassDescriptor;

import java.io.IOException;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

/**
 * ClassLoadingDataObserver
 * <p>
 * Depli implementation for loading observer ClassLoadingMXBean
 * Load class loading system management interface of the observer JVM.
 * <p>
 * Created by lpsandaruwan on 3/23/17.
 */

public class ClassLoadingDataObserver {

    private JMXConnectionObserver jmxConnectionObserver;
    private ClassLoadingMXBean classLoadingMXBean;
    private ClassDescriptor classDescriptor;

    public ClassLoadingDataObserver(JMXConnectionObserver jmxConnectionObserver) {
        this.jmxConnectionObserver = jmxConnectionObserver;
        this.classDescriptor = new ClassDescriptor(jmxConnectionObserver.getJmxNode().getNodeId());
    }

    public ClassLoadingMXBean getClassLoadingMXBean() {
        return classLoadingMXBean;
    }

    public ClassDescriptor getClassLoadingData() {
        return classDescriptor;
    }

    // Load observer ClassLoadingMXBean
    public ClassLoadingMXBean initialize() throws IOException {
        classLoadingMXBean = ManagementFactory.newPlatformMXBeanProxy(
                jmxConnectionObserver.getmBeanServerConnection(),
                ManagementFactory.CLASS_LOADING_MXBEAN_NAME,
                ClassLoadingMXBean.class
        );

        return classLoadingMXBean;
    }

    // Refresh and get ClassObjectLoadingData
    public ClassDescriptor refreshData() {
        classDescriptor.setAllData(
                classLoadingMXBean.getLoadedClassCount(),
                classLoadingMXBean.getTotalLoadedClassCount(),
                classLoadingMXBean.getUnloadedClassCount()
        );
        return classDescriptor;
    }
}
