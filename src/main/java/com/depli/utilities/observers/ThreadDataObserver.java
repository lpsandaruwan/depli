package com.depli.utilities.observers;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/** ThreadDataObserver
 *
 * Depli implementation for loading observers ThreadMXBean
 * Load thread management interface of the observers.
 *
 * Created by lpsandaruwan on 3/23/17.
 */

public class ThreadDataObserver {

    private JMXConnectionObserver JMXConnectionObserver;
    private ThreadMXBean threadMXBean;
    private com.depli.data.object.ThreadData threadData;

    public ThreadDataObserver(JMXConnectionObserver JMXConnectionObserver) {
        this.JMXConnectionObserver = JMXConnectionObserver;
        this.threadData = new com.depli.data.object.ThreadData();
    }

    public ThreadMXBean getThreadMXBean() {
        return threadMXBean;
    }

    public com.depli.data.object.ThreadData getThreadData() {
        return threadData;
    }

    // Initialize observers ThreadMXBean
    public ThreadMXBean initialize() throws IOException {
        threadMXBean = ManagementFactory.newPlatformMXBeanProxy(
                JMXConnectionObserver.getmBeanServerConnection(),
                ManagementFactory.THREAD_MXBEAN_NAME,
                ThreadMXBean.class
        );

        return threadMXBean;
    }

    // Refresh and get ThreadDataObserver object
    public com.depli.data.object.ThreadData refreshData() {
        threadData.setData(threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds(), Integer.MAX_VALUE));

        return threadData;
    }
}
