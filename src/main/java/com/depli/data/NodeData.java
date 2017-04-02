package com.depli.data;

import com.depli.data.object.StatisticsData;
import com.depli.remote.*;

/**NodeData
 * Combined model of all the data and stats of a node.
 *
 * Created by lpsandaruwan on 3/24/17.
 */

public class NodeData {

    private final DJMXConnection djmxConnection;
    private StatisticsData statisticsData;

    private boolean isInitialized;
    private boolean isReachable;

    // temp variable to keep timestamps
    private long previousJvmCpuTime;
    private long previousJvmUptime;

    private final DClassLoadingMXBean dClassLoadingMXBean;
    private final DMemoryMXBean dMemoryMXBean;
    private final DOperatingSystemMXBean dOperatingSystemMXBean;
    private final DRuntimeMXBean dRuntimeMXBean;
    private final DPEOperatingSystemMXBean dpeOperatingSystemMXBean;
    private final DThreadMXBean dThreadMXBean;

    public NodeData(DJMXConnection djmxConnection) {
        this.djmxConnection = djmxConnection;
        this.statisticsData = new StatisticsData();

        this.isInitialized = false;
        this.isReachable = false;

        this.previousJvmCpuTime = 0;
        this.previousJvmUptime = 0;

        this.dClassLoadingMXBean = new DClassLoadingMXBean(djmxConnection);
        this.dMemoryMXBean = new DMemoryMXBean(djmxConnection);
        this.dOperatingSystemMXBean = new DOperatingSystemMXBean(djmxConnection);
        this.dRuntimeMXBean = new DRuntimeMXBean(djmxConnection);
        this.dpeOperatingSystemMXBean = new DPEOperatingSystemMXBean(djmxConnection);
        this.dThreadMXBean = new DThreadMXBean(djmxConnection);
    }

    // set false on isInitialized and isReachable values on connection failure, to check and reload connection
    public void falseAllInitializedData() {
        isReachable = false;
        isInitialized = false;
    }

    public void refreshInstantData() {
        dClassLoadingMXBean.refreshData();
        dMemoryMXBean.refreshData();
        dOperatingSystemMXBean.refreshData();
        dpeOperatingSystemMXBean.refreshData();

        // Update statistics data
        updateStatisticsData();
    }

    public void refreshData() {
        dRuntimeMXBean.refreshData();
        dThreadMXBean.refreshData();
    }

    public DJMXConnection getDjmxConnection() {
        return djmxConnection;
    }

    public StatisticsData getStatisticsData() {
        return statisticsData;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public boolean isReachable() {
        return isReachable;
    }

    public DClassLoadingMXBean getdClassLoadingMXBean() {
        return dClassLoadingMXBean;
    }

    public DMemoryMXBean getdMemoryMXBean() {
        return dMemoryMXBean;
    }

    public DOperatingSystemMXBean getdOperatingSystemMXBean() {
        return dOperatingSystemMXBean;
    }

    public DRuntimeMXBean getdRuntimeMXBean() {
        return dRuntimeMXBean;
    }

    public DPEOperatingSystemMXBean getDpeOperatingSystemMXBean() {
        return dpeOperatingSystemMXBean;
    }

    public DThreadMXBean getdThreadMXBean() {
        return dThreadMXBean;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    public void setReachable(boolean reachable) {
        isReachable = reachable;
    }

    // Get JVM CPU usage
    public float getJvmCpuUsage() {
        if(dpeOperatingSystemMXBean.getPeOperatingSystemMXBean() != null) {
            float cpuUsage = (
                    dpeOperatingSystemMXBean.getPeOperatingSystemMXBean().getProcessCpuTime() - previousJvmCpuTime
            ) / (
                    (
                            dRuntimeMXBean.getRuntimeMXBean().getUptime() - previousJvmUptime
                    ) * 10000F * dOperatingSystemMXBean.getOperatingSystemMXBean().getAvailableProcessors()
            );

            // Set old timestamp values
            previousJvmCpuTime = dpeOperatingSystemMXBean.getPeOperatingSystemMXBean().getProcessCpuTime();
            previousJvmUptime = dRuntimeMXBean.getRuntimeMXBean().getUptime();

            return (float) Math.round((cpuUsage * 10)) / 10;
        }

        return 0;
    }

    // Update statistics data
    public void updateStatisticsData() {
        // set classes related data
        statisticsData.setLoadedClassCount(dClassLoadingMXBean.getClassLoadingData().getLoadedClassCount());

        // set cpu related data
        statisticsData.setJvmCpuUsageData(getJvmCpuUsage());

        // set memory related data
        statisticsData.setUsedHeapMemory(dMemoryMXBean.getMemoryData().getHeapMemory().getUsed());
        statisticsData.setUsedNonHeapMemory(dMemoryMXBean.getMemoryData().getNonHeapMemory().getUsed());

        // set jvm uptime
        statisticsData.setJvmUptime(dRuntimeMXBean.getRuntimeMXBean().getUptime());

        // set thread related data
        statisticsData.setDaemonThreadCount(dThreadMXBean.getThreadMXBean().getDaemonThreadCount());
        statisticsData.setPeakThreadCount(dThreadMXBean.getThreadMXBean().getPeakThreadCount());
        statisticsData.setLiveThreadCount(dThreadMXBean.getThreadMXBean().getThreadCount());
        statisticsData.setTotalStartedThreadCount(dThreadMXBean.getThreadMXBean().getTotalStartedThreadCount());

        // set host related data
        statisticsData.setHostCpuUsage(dpeOperatingSystemMXBean.getPeOperatingSystemData().getHostCpuUsage());
        statisticsData.setHostFreePhysicalMemory(dpeOperatingSystemMXBean.getPeOperatingSystemData().getFreePhysicalMemory());
        statisticsData.setHostTotalPhysicalMemory(dpeOperatingSystemMXBean.getPeOperatingSystemData().getTotalPhysicalMemory());
    }
}
