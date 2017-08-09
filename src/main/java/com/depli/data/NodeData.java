package com.depli.data;

import com.depli.data.object.StatisticsData;
import com.depli.utilities.observers.*;

/**
 * NodeData
 * Combined model of all the data and stats of a node.
 * <p>
 * Created by lpsandaruwan on 3/24/17.
 */

public class NodeData {

    private final com.depli.utilities.observers.JMXConnectionObserver JMXConnectionObserver;
    private final ClassLoadingDataObserver classLoadingDataObserver;
    private final MemoryDataObserver memoryDataObserver;
    private final OperatingSystemDataObserver operatingSystemDataObserver;
    private final RuntimeDataObserver runtimeDataObserver;
    private final PlatformSystemDataObserver platformSystemDataObserver;
    private final ThreadDataObserver threadDataObserver;
    private StatisticsData statisticsData;
    private boolean isInitialized;
    // temp variable to keep timestamps
    private long previousJvmCpuTime;
    private long previousJvmUptime;

    public NodeData(JMXConnectionObserver JMXConnectionObserver) {
        this.JMXConnectionObserver = JMXConnectionObserver;
        this.statisticsData = new StatisticsData();

        this.isInitialized = false;

        this.previousJvmCpuTime = 0;
        this.previousJvmUptime = 0;

        this.classLoadingDataObserver = new ClassLoadingDataObserver(JMXConnectionObserver);
        this.memoryDataObserver = new MemoryDataObserver(JMXConnectionObserver);
        this.operatingSystemDataObserver = new OperatingSystemDataObserver(JMXConnectionObserver);
        this.runtimeDataObserver = new RuntimeDataObserver(JMXConnectionObserver);
        this.platformSystemDataObserver = new PlatformSystemDataObserver(JMXConnectionObserver);
        this.threadDataObserver = new ThreadDataObserver(JMXConnectionObserver);
    }

    public void refreshInstantData() {
        classLoadingDataObserver.refreshData();
        memoryDataObserver.refreshData();
        operatingSystemDataObserver.refreshData();
        platformSystemDataObserver.refreshData();

        // Update statistics data
        updateStatisticsData();
    }

    public void refreshData() {
        runtimeDataObserver.refreshData();
        threadDataObserver.refreshData();
    }

    public JMXConnectionObserver getJMXConnectionObserver() {
        return JMXConnectionObserver;
    }

    public StatisticsData getStatisticsData() {
        return statisticsData;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    public ClassLoadingDataObserver getClassLoadingDataObserver() {
        return classLoadingDataObserver;
    }

    public MemoryDataObserver getMemoryDataObserver() {
        return memoryDataObserver;
    }

    public OperatingSystemDataObserver getOperatingSystemDataObserver() {
        return operatingSystemDataObserver;
    }

    public RuntimeDataObserver getRuntimeDataObserver() {
        return runtimeDataObserver;
    }

    public PlatformSystemDataObserver getPlatformSystemDataObserver() {
        return platformSystemDataObserver;
    }

    public ThreadDataObserver getThreadDataObserver() {
        return threadDataObserver;
    }

    // Get JVM CPU usage
    public float getJvmCpuUsage() {
        if (platformSystemDataObserver.getPeOperatingSystemMXBean() != null) {
            float cpuUsage = (
                    platformSystemDataObserver.getPeOperatingSystemMXBean().getProcessCpuTime() - previousJvmCpuTime
            ) / (
                    (
                            runtimeDataObserver.getRuntimeMXBean().getUptime() - previousJvmUptime
                    ) * 10000F * operatingSystemDataObserver.getOperatingSystemMXBean().getAvailableProcessors()
            );

            // Set old timestamp values
            previousJvmCpuTime = platformSystemDataObserver.getPeOperatingSystemMXBean().getProcessCpuTime();
            previousJvmUptime = runtimeDataObserver.getRuntimeMXBean().getUptime();

            return (float) Math.round((cpuUsage * 10)) / 10;
        }

        return 0;
    }

    // Update statistics data
    public void updateStatisticsData() {
        // set classes related data
        statisticsData.setLoadedClassCount(classLoadingDataObserver.getClassLoadingData().getLoadedClassCount());

        // set cpu related data
        statisticsData.setJvmCpuUsageData(getJvmCpuUsage());

        // set memory related data
        statisticsData.setUsedHeapMemory(memoryDataObserver.getMemoryData().getHeapMemory().getUsed());
        statisticsData.setUsedNonHeapMemory(memoryDataObserver.getMemoryData().getNonHeapMemory().getUsed());

        // set jvm uptime
        statisticsData.setJvmUptime(runtimeDataObserver.getRuntimeMXBean().getUptime());

        // set thread related data
        statisticsData.setDaemonThreadCount(threadDataObserver.getThreadMXBean().getDaemonThreadCount());
        statisticsData.setPeakThreadCount(threadDataObserver.getThreadMXBean().getPeakThreadCount());
        statisticsData.setLiveThreadCount(threadDataObserver.getThreadMXBean().getThreadCount());
        statisticsData.setTotalStartedThreadCount(threadDataObserver.getThreadMXBean().getTotalStartedThreadCount());

        // set host related data
        statisticsData.setHostCpuUsage(platformSystemDataObserver.getPeOperatingSystemData().getHostCpuUsage());
        statisticsData.setHostFreePhysicalMemory(platformSystemDataObserver.getPeOperatingSystemData().getFreePhysicalMemory());
        statisticsData.setHostTotalPhysicalMemory(platformSystemDataObserver.getPeOperatingSystemData().getTotalPhysicalMemory());
    }
}
