package com.depli.store.cache.descriptor;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;

/**
 * Thread Descriptor
 *
 * Entity to keep memory usage data observed from {@link ManagementFactory#getThreadMXBean} of appropriate
 * remote JMX connection in runtime.
 *
 * @author Lahiru Pathirage
 * @since 3/22/17
 */

public class ThreadDescriptor {

    private ThreadInfo[] threadInfos;

    // default constructor
    public ThreadDescriptor() {
    }

    public void setData(ThreadInfo[] threadInfos) {
        this.threadInfos = threadInfos;
    }

    public ThreadInfo[] getThreadInfos() {
        return threadInfos;
    }

    public void setThreadInfos(ThreadInfo[] threadInfos) {
        this.threadInfos = threadInfos;
    }
}
