package com.depli.remote;

import com.depli.data.object.ThreadData;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/** DThreadMXBean
 *
 * Depli implementation for loading remote ThreadMXBean
 * Load thread management interface of the remote.
 *
 * Created by lpsandaruwan on 3/23/17.
 */

public class DThreadMXBean {

    private DJMXConnection djmxConnection;
    private ThreadMXBean threadMXBean;
    private ThreadData threadData;

    public DThreadMXBean(DJMXConnection djmxConnection) {
        this.djmxConnection = djmxConnection;
        this.threadData = new ThreadData();
    }

    public ThreadMXBean getThreadMXBean() {
        return threadMXBean;
    }

    public ThreadData getThreadData() {
        return threadData;
    }

    // Initialize remote ThreadMXBean
    public ThreadMXBean initialize() throws IOException {
        threadMXBean = ManagementFactory.newPlatformMXBeanProxy(
                djmxConnection.getmBeanServerConnection(),
                ManagementFactory.THREAD_MXBEAN_NAME,
                ThreadMXBean.class
        );

        return threadMXBean;
    }

    // Refresh and get ThreadData object
    public ThreadData refreshdata() {
        threadData.setData(
                threadMXBean.findDeadlockedThreads(),
                threadMXBean.findMonitorDeadlockedThreads(),
                threadMXBean.getAllThreadIds(),
                threadMXBean.getCurrentThreadCpuTime(),
                threadMXBean.getCurrentThreadUserTime(),
                threadMXBean.getDaemonThreadCount(),
                threadMXBean.getPeakThreadCount(),
                threadMXBean.getThreadCount(),
                threadMXBean.getTotalStartedThreadCount()
        );

        return threadData;
    }
}
