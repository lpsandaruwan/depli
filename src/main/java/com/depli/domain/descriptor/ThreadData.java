package com.depli.domain.descriptor;

import java.lang.management.ThreadInfo;

/**
 * Keeps thread MX Bean domain
 * <p>
 * Created by lpsandaruwan on 3/24/17.
 */

public class ThreadData {

    private ThreadInfo[] threadInfos;

    // default constructor
    public ThreadData() {
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
