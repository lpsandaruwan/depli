package com.depli.utility.observer;

import com.depli.store.cache.descriptor.ThreadData;

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
    private ThreadData threadData;

    public ThreadDataObserver(JMXConnectionObserver jmxConnectionObserver) {
        this.jmxConnectionObserver = jmxConnectionObserver;
        this.threadData = new ThreadData();
    }

    public ThreadMXBean getThreadMXBean() {
        return threadMXBean;
    }

    public ThreadData getThreadData() {
        return threadData;
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
    public ThreadData refreshData() {
        threadData.setData(threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds(), Integer.MAX_VALUE));

        return threadData;
    }
}
