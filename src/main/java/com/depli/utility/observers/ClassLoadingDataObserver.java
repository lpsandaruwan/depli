package com.depli.utility.observers;

import com.depli.domain.descriptor.ClassLoadingData;

import java.io.IOException;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

/**
 * ClassLoadingDataObserver
 * <p>
 * Depli implementation for loading observers ClassLoadingMXBean
 * Load class loading system management interface of the observers JVM.
 * <p>
 * Created by lpsandaruwan on 3/23/17.
 */

public class ClassLoadingDataObserver {

    private JMXConnectionObserver JMXConnectionObserver;
    private ClassLoadingMXBean classLoadingMXBean;
    private ClassLoadingData classLoadingData;

    public ClassLoadingDataObserver(JMXConnectionObserver JMXConnectionObserver) {
        this.JMXConnectionObserver = JMXConnectionObserver;
        this.classLoadingData = new ClassLoadingData();
    }

    public ClassLoadingMXBean getClassLoadingMXBean() {
        return classLoadingMXBean;
    }

    public ClassLoadingData getClassLoadingData() {
        return classLoadingData;
    }

    // Load observers ClassLoadingMXBean
    public ClassLoadingMXBean initialize() throws IOException {
        classLoadingMXBean = ManagementFactory.newPlatformMXBeanProxy(
                JMXConnectionObserver.getmBeanServerConnection(),
                ManagementFactory.CLASS_LOADING_MXBEAN_NAME,
                ClassLoadingMXBean.class
        );

        return classLoadingMXBean;
    }

    // Refresh and get ClassObjectLoadingData
    public ClassLoadingData refreshData() {
        classLoadingData.setData(
                classLoadingMXBean.getLoadedClassCount(),
                classLoadingMXBean.getTotalLoadedClassCount(),
                classLoadingMXBean.getUnloadedClassCount()
        );
        return classLoadingData;
    }
}
