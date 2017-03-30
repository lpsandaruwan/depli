package com.depli.data.object;

import java.lang.management.ThreadInfo;

/**
 * Keeps thread MX Bean data
 *
 * Created by lpsandaruwan on 3/24/17.
 */

public class ThreadData {

    private ThreadInfo[] threadInfos;

    public ThreadData() {}

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
