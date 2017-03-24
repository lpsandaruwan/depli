package com.depli.remote;

import com.depli.data.object.ClassLoadingData;

import java.io.IOException;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

/**
 * Depli implementation for loading remote ClassLoadingMXBean
 * Load class loading system management interface of the remote JVM.
 *
 * Created by lpsandaruwan on 3/23/17.
 */

public class DClassLoadingMXBean {

    private DJMXConnection djmxConnection;
    private ClassLoadingMXBean classLoadingMXBean;
    private ClassLoadingData classLoadingData;

    public DClassLoadingMXBean(DJMXConnection djmxConnection) {
        this.djmxConnection = djmxConnection;
        this.classLoadingData = new ClassLoadingData();
    }

    public ClassLoadingMXBean getClassLoadingMXBean() {
        return classLoadingMXBean;
    }

    public ClassLoadingData getClassLoadingData() {
        return classLoadingData;
    }

    // Load remote ClassLoadingMXBean
    public ClassLoadingMXBean initialize() throws IOException {
        classLoadingMXBean = ManagementFactory.newPlatformMXBeanProxy(
                djmxConnection.getmBeanServerConnection(),
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
