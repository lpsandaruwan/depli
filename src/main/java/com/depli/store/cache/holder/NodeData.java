package com.depli.store.cache.holder;

import com.depli.store.cache.descriptor.StatisticsData;
import com.depli.utility.observer.*;

/**
 * NodeData
 * Combined model of all the store and stats of a node.
 * <p>
 * Created by lpsandaruwan on 3/24/17.
 */

public class NodeData {

    private final com.depli.utility.observer.JMXConnectionObserver jmxConnectionObserver;
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

    public NodeData(JMXConnectionObserver jmxConnectionObserver) {
        this.jmxConnectionObserver = jmxConnectionObserver;
        this.statisticsData = new StatisticsData();

        this.isInitialized = false;

        this.previousJvmCpuTime = 0;
        this.previousJvmUptime = 0;

        this.classLoadingDataObserver = new ClassLoadingDataObserver(jmxConnectionObserver);
        this.memoryDataObserver = new MemoryDataObserver(jmxConnectionObserver);
        this.operatingSystemDataObserver = new OperatingSystemDataObserver(jmxConnectionObserver);
        this.runtimeDataObserver = new RuntimeDataObserver(jmxConnectionObserver);
        this.platformSystemDataObserver = new PlatformSystemDataObserver(jmxConnectionObserver);
        this.threadDataObserver = new ThreadDataObserver(jmxConnectionObserver);
    }

    public void refreshInstantData() {
        classLoadingDataObserver.refreshData();
        memoryDataObserver.refreshData();
        operatingSystemDataObserver.refreshData();
        platformSystemDataObserver.refreshData();

        // Update statistics store
        updateStatisticsData();
    }

    public void refreshData() {
        runtimeDataObserver.refreshData();
        threadDataObserver.refreshData();
    }

    public JMXConnectionObserver getJmxConnectionObserver() {
        return jmxConnectionObserver;
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

    // Update statistics store
    public void updateStatisticsData() {
        // set classes related store
        statisticsData.setLoadedClassCount(classLoadingDataObserver.getClassLoadingData().getLoadedClassCount());

        // set cpu related store
        statisticsData.setJvmCpuUsageData(getJvmCpuUsage());

        // set memory related store
        statisticsData.setUsedHeapMemory(memoryDataObserver.getMemoryUsageDescriptor().getHeapMemory().getUsed());
        statisticsData.setUsedNonHeapMemory(memoryDataObserver.getMemoryUsageDescriptor().getNonHeapMemory().getUsed());

        // set jvm uptime
        statisticsData.setJvmUptime(runtimeDataObserver.getRuntimeMXBean().getUptime());

        // set thread related store
        statisticsData.setDaemonThreadCount(threadDataObserver.getThreadMXBean().getDaemonThreadCount());
        statisticsData.setPeakThreadCount(threadDataObserver.getThreadMXBean().getPeakThreadCount());
        statisticsData.setLiveThreadCount(threadDataObserver.getThreadMXBean().getThreadCount());
        statisticsData.setTotalStartedThreadCount(threadDataObserver.getThreadMXBean().getTotalStartedThreadCount());

        // set host related store
        statisticsData.setHostCpuUsage(platformSystemDataObserver.getPlatformSystemDescriptor().getHostCpuUsage());
        statisticsData.setHostFreePhysicalMemory(platformSystemDataObserver.getPlatformSystemDescriptor().getFreePhysicalMemory());
        statisticsData.setHostTotalPhysicalMemory(platformSystemDataObserver.getPlatformSystemDescriptor().getTotalPhysicalMemory());
    }
}
