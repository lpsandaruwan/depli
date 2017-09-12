package com.depli.utility.observer;

import com.depli.store.cache.descriptor.ThreadDescriptor;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * ThreadDataObserver
 * <p>
 * Depli implementation for loading observer ThreadMXBean
 * Load thread management interface of the observer.
 * <p>
 * Created by lpsandaruwan on 3/23/17.
 */

public class ThreadDataObserver {

    private JMXConnectionObserver jmxConnectionObserver;
    private ThreadMXBean threadMXBean;
    private ThreadDescriptor threadDescriptor;

    public ThreadDataObserver(JMXConnectionObserver jmxConnectionObserver) {
        this.jmxConnectionObserver = jmxConnectionObserver;
        this.threadDescriptor = new ThreadDescriptor();
    }

    public ThreadMXBean getThreadMXBean() {
        return threadMXBean;
    }

    public ThreadDescriptor getThreadDescriptor() {
        return threadDescriptor;
    }

    // Initialize observer ThreadMXBean
    public ThreadMXBean initialize() throws IOException {
        threadMXBean = ManagementFactory.newPlatformMXBeanProxy(
                jmxConnectionObserver.getmBeanServerConnection(),
                ManagementFactory.THREAD_MXBEAN_NAME,
                ThreadMXBean.class
        );

        return threadMXBean;
    }

    // Refresh and get ThreadDataObserver object
    public ThreadDescriptor refreshData() {
        threadDescriptor.setData(threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds(), Integer.MAX_VALUE));

        return threadDescriptor;
    }
}
