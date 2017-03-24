package com.depli.data.object;

/**
 * Keeps thread MX Bean data
 *
 * Created by lpsandaruwan on 3/24/17.
 */

public class ThreadData {

    private long[] deadLockedThreads;
    private long[] awaitMonitorDeadLockedThreads;
    private long[] threadIds;
    private long currentThreadCpuTime;
    private long currentThreadUserTime;
    private int daemonThreadCount;
    private int peakThreadCount;
    private int liveThreadCount;
    private long totalStartedThreadCount;

    public ThreadData() {}

    public void setData(long[] deadLockedThreads,
                      long[] awaitMonitorDeadLockedThreads,
                      long[] threadIds,
                      long currentThreadCpuTime,
                      long currentThreadUserTime,
                      int daemonThreadCount,
                      int peakThreadCount,
                      int liveThreadCount,
                      long totalStartedThreadCount) {
        this.deadLockedThreads = deadLockedThreads;
        this.awaitMonitorDeadLockedThreads = awaitMonitorDeadLockedThreads;
        this.threadIds = threadIds;
        this.currentThreadCpuTime = currentThreadCpuTime;
        this.currentThreadUserTime = currentThreadUserTime;
        this.daemonThreadCount = daemonThreadCount;
        this.peakThreadCount = peakThreadCount;
        this.liveThreadCount = liveThreadCount;
        this.totalStartedThreadCount = totalStartedThreadCount;
    }

    public long[] getDeadLockedThreads() {
        return deadLockedThreads;
    }

    public long[] getAwaitMonitorDeadLockedThreads() {
        return awaitMonitorDeadLockedThreads;
    }

    public long[] getThreadIds() {
        return threadIds;
    }

    public long getCurrentThreadCpuTime() {
        return currentThreadCpuTime;
    }

    public long getCurrentThreadUserTime() {
        return currentThreadUserTime;
    }

    public int getDaemonThreadCount() {
        return daemonThreadCount;
    }

    public int getPeakThreadCount() {
        return peakThreadCount;
    }

    public int getLiveThreadCount() {
        return liveThreadCount;
    }

    public long getTotalStartedThreadCount() {
        return totalStartedThreadCount;
    }

    public void setDeadLockedThreads(long[] deadLockedThreads) {
        this.deadLockedThreads = deadLockedThreads;
    }

    public void setAwaitMonitorDeadLockedThreads(long[] awaitMonitorDeadLockedThreads) {
        this.awaitMonitorDeadLockedThreads = awaitMonitorDeadLockedThreads;
    }

    public void setThreadIds(long[] threadIds) {
        this.threadIds = threadIds;
    }

    public void setCurrentThreadCpuTime(long currentThreadCpuTime) {
        this.currentThreadCpuTime = currentThreadCpuTime;
    }

    public void setCurrentThreadUserTime(long currentThreadUserTime) {
        this.currentThreadUserTime = currentThreadUserTime;
    }

    public void setDaemonThreadCount(int daemonThreadCount) {
        this.daemonThreadCount = daemonThreadCount;
    }

    public void setPeakThreadCount(int peakThreadCount) {
        this.peakThreadCount = peakThreadCount;
    }

    public void setLiveThreadCount(int liveThreadCount) {
        this.liveThreadCount = liveThreadCount;
    }

    public void setTotalStartedThreadCount(long totalStartedThreadCount) {
        this.totalStartedThreadCount = totalStartedThreadCount;
    }
}
