package com.depli.utilities.observers;

import java.io.IOException;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

/** ClassLoadingDataObserver
 *
 * Depli implementation for loading observers ClassLoadingMXBean
 * Load class loading system management interface of the observers JVM.
 *
 * Created by lpsandaruwan on 3/23/17.
 */

public class ClassLoadingDataObserver {

    private JMXConnectionObserver JMXConnectionObserver;
    private ClassLoadingMXBean classLoadingMXBean;
    private com.depli.data.object.ClassLoadingData classLoadingData;

    public ClassLoadingDataObserver(JMXConnectionObserver JMXConnectionObserver) {
        this.JMXConnectionObserver = JMXConnectionObserver;
        this.classLoadingData = new com.depli.data.object.ClassLoadingData();
    }

    public ClassLoadingMXBean getClassLoadingMXBean() {
        return classLoadingMXBean;
    }

    public com.depli.data.object.ClassLoadingData getClassLoadingData() {
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
    public com.depli.data.object.ClassLoadingData refreshData() {
        classLoadingData.setData(
                classLoadingMXBean.getLoadedClassCount(),
                classLoadingMXBean.getTotalLoadedClassCount(),
                classLoadingMXBean.getUnloadedClassCount()
        );
        return classLoadingData;
    }
}
