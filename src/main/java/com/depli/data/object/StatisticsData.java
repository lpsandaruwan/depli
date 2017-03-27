package com.depli.data.object;

/**
 * Created by lpsandaruwan on 3/26/17.
 */


public class StatisticsData {
    // jmx node status
    private boolean isConnected;

    // cpu laoding data
    private float cpuUsage;
    private float[] cpuUsageData;

    // memory loading data
    private long usedHeapMemory;
    private long usedNonHeapMemory;

    public StatisticsData() {
        this.cpuUsageData = new float[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.isConnected = false;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public long getUsedHeapMemory() {
        return usedHeapMemory;
    }

    public long getUsedNonHeapMemory() {
        return usedNonHeapMemory;
    }

    public void setUsedHeapMemory(long usedHeapMemory) {
        this.usedHeapMemory = usedHeapMemory;
    }

    public void setUsedNonHeapMemory(long usedNonHeapMemory) {
        this.usedNonHeapMemory = usedNonHeapMemory;
    }

    public float getCpuUsage() {
        return cpuUsage;
    }

    public float[] getCpuUsageData() {
        return cpuUsageData;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public void setCpuUsage(float cpuUsage) {
        this.cpuUsage = cpuUsage;

        // update cpu usage data array
        int index = 1;

        while (index < cpuUsageData.length) {
            cpuUsageData[index - 1] = cpuUsageData[index];
            index++;
        }

        cpuUsageData[index - 1] = cpuUsage;
    }
}
