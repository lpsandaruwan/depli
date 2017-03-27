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

    private long previousJvmCpuTime;
    private long previousJvmUptime;

    private boolean isInitialized;

    private final DClassLoadingMXBean dClassLoadingMXBean;
    private final DMemoryMXBean dMemoryMXBean;
    private final DOperatingSystemMXBean dOperatingSystemMXBean;
    private final DRuntimeMXBean dRuntimeMXBean;
    private final DSunOperatingSystemMXBean dSunOperatingSystemMXBean;
    private final DThreadMXBean dThreadMXBean;

    public NodeData(DJMXConnection djmxConnection) {
        this.djmxConnection = djmxConnection;
        this.statisticsData = new StatisticsData();

        this.previousJvmCpuTime = 0;
        this.previousJvmUptime = 0;
        this.isInitialized = false;

        this.dClassLoadingMXBean = new DClassLoadingMXBean(djmxConnection);
        this.dMemoryMXBean = new DMemoryMXBean(djmxConnection);
        this.dOperatingSystemMXBean = new DOperatingSystemMXBean(djmxConnection);
        this.dRuntimeMXBean = new DRuntimeMXBean(djmxConnection);
        this.dSunOperatingSystemMXBean = new DSunOperatingSystemMXBean(djmxConnection);
        this.dThreadMXBean = new DThreadMXBean(djmxConnection);
    }

    public void refreshData() {
        dClassLoadingMXBean.refreshData();
        dMemoryMXBean.refreshData();
        dOperatingSystemMXBean.refreshData();
        dRuntimeMXBean.refreshData();
        dThreadMXBean.refreshdata();

        // Update statistics data
        updateStatisticsData();
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

    public DSunOperatingSystemMXBean getdSunOperatingSystemMXBean() {
        return dSunOperatingSystemMXBean;
    }

    public DThreadMXBean getdThreadMXBean() {
        return dThreadMXBean;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    // Get JVM CPU usage
    public float getJvmCpuUsage() {
        if(dSunOperatingSystemMXBean.getSunOperatingSystemMXBean() != null) {
            float cpuUsage = (
                    dSunOperatingSystemMXBean.getSunOperatingSystemMXBean().getProcessCpuTime() - previousJvmCpuTime
            ) / (
                    (
                            dRuntimeMXBean.getRuntimeMXBean().getUptime() - previousJvmUptime
                    ) * 10000F * dOperatingSystemMXBean.getOperatingSystemMXBean().getAvailableProcessors()
            );

            // Set old timestamp values
            previousJvmCpuTime = dSunOperatingSystemMXBean.getSunOperatingSystemMXBean().getProcessCpuTime();
            previousJvmUptime = dRuntimeMXBean.getRuntimeMXBean().getUptime();

            return (float) Math.round((cpuUsage * 10)) / 10;
        }

        return 0;
    }

    // Update statistics data
    public void updateStatisticsData() {
        // set cpu related data
        statisticsData.setCpuUsage(getJvmCpuUsage());

        // set memory related data
        statisticsData.setUsedHeapMemory(getdMemoryMXBean().getMemoryData().getHeapMemory().getUsed());
        statisticsData.setUsedNonHeapMemory(getdMemoryMXBean().getMemoryData().getNonHeapMemory().getUsed());
    }
}
