package com.depli.utility.observer;

import com.depli.store.cache.descriptor.ClassLoadingDescriptor;
import com.depli.service.store.cache.ClassLoadingDescriptorCacheService;
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
    private ClassLoadingDescriptorCacheService classLoadingDescriptorCacheService;

    private JMXConnectionObserver jmxConnectionObserver;
    private ClassLoadingMXBean classLoadingMXBean;
    private ClassLoadingDescriptor classLoadingDescriptor;

    public ClassLoadingDataObserver(JMXConnectionObserver jmxConnectionObserver) {
        this.jmxConnectionObserver = jmxConnectionObserver;
        this.classLoadingDescriptor = new ClassLoadingDescriptor();
    }

    public ClassLoadingMXBean getClassLoadingMXBean() {
        return classLoadingMXBean;
    }

    public ClassLoadingDescriptor getClassLoadingData() {
        return classLoadingDescriptor;
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
    public ClassLoadingDescriptor refreshData() {
        classLoadingDescriptor.setData(
                classLoadingMXBean.getLoadedClassCount(),
                classLoadingMXBean.getTotalLoadedClassCount(),
                classLoadingMXBean.getUnloadedClassCount()
        );

        try {
            classLoadingDescriptorCacheService.getCache().put(jmxConnectionObserver.getJmxNode().getNodeId(), classLoadingDescriptor);
        } catch (Exception e) {

        }
        return classLoadingDescriptor;
    }
}
