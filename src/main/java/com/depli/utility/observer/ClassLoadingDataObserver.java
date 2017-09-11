package com.depli.utility.observer;

import com.depli.store.cache.descriptor.ClassLoadingDataDescriptor;
import com.depli.service.store.cache.ClassLoadingDataDescriptorCacheService;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    private ClassLoadingDataDescriptorCacheService classLoadingDataDescriptorCacheService;

    private JMXConnectionObserver jmxConnectionObserver;
    private ClassLoadingMXBean classLoadingMXBean;
    private ClassLoadingDataDescriptor classLoadingDataDescriptor;

    public ClassLoadingDataObserver(JMXConnectionObserver jmxConnectionObserver) {
        this.jmxConnectionObserver = jmxConnectionObserver;
        this.classLoadingDataDescriptor = new ClassLoadingDataDescriptor();
    }

    public ClassLoadingMXBean getClassLoadingMXBean() {
        return classLoadingMXBean;
    }

    public ClassLoadingDataDescriptor getClassLoadingData() {
        return classLoadingDataDescriptor;
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
    public ClassLoadingDataDescriptor refreshData() {
        classLoadingDataDescriptor.setAllData(
                classLoadingMXBean.getLoadedClassCount(),
                classLoadingMXBean.getTotalLoadedClassCount(),
                classLoadingMXBean.getUnloadedClassCount()
        );

        try {
            classLoadingDataDescriptorCacheService.getCache().put(jmxConnectionObserver.getJmxNode().getNodeId(), classLoadingDataDescriptor);
        } catch (Exception e) {

        }
        return classLoadingDataDescriptor;
    }
}
