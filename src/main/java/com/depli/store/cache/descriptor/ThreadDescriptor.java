package com.depli.store.cache.descriptor;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;

/**
 * Thread Descriptor
 * <p>
 * Entity to keep memory usage data observed from {@link ManagementFactory#getThreadMXBean} of appropriate
 * remote JMX connection in runtime.
 *
 * @author Lahiru Pathirage
 * @since 3/22/17
 */

public class ThreadDescriptor {

    /*
     * The list of thread info for each thread with of stack trace elements.
     */
    private ThreadInfo[] threadInfoList;

    // default constructor
    public ThreadDescriptor() {
    }

    public void setData(ThreadInfo[] threadInfoList) {
        this.threadInfoList = threadInfoList;
    }

    /**
     * Returns the list of thread info for each thread with of stack trace elements.
     *
     * @return a the list of thread info
     */
    public ThreadInfo[] getThreadInfoList() {
        return threadInfoList;
    }

    /**
     * Sets the list of thread info for each thread with of stack trace elements.
     *
     * @param threadInfoList a the list of thread info
     */
    public void setThreadInfoList(ThreadInfo[] threadInfoList) {
        this.threadInfoList = threadInfoList;
    }
}
