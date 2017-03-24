package com.depli.data.object;

import java.lang.management.MemoryUsage;

/**
 * Keeps memory MX Bean data
 *
 * Created by lpsandaruwan on 3/24/17.
 */

public class MemoryData {

    private MemoryUsage heapMemory;
    private MemoryUsage nonHeapMemory;
    private int awaitFinalizationObjectCount;

    public MemoryData() {}

    public void setData(MemoryUsage heapMemory, MemoryUsage nonHeapMemory, int awaitFinalizationObjectCount) {
        this.heapMemory = heapMemory;
        this.nonHeapMemory = nonHeapMemory;
        this.awaitFinalizationObjectCount = awaitFinalizationObjectCount;
    }

    public MemoryUsage getHeapMemory() {
        return heapMemory;
    }

    public MemoryUsage getNonHeapMemory() {
        return nonHeapMemory;
    }

    public int getAwaitFinalizationObjectCount() {
        return awaitFinalizationObjectCount;
    }

    public void setHeapMemory(MemoryUsage heapMemory) {
        this.heapMemory = heapMemory;
    }

    public void setNonHeapMemory(MemoryUsage nonHeapMemory) {
        this.nonHeapMemory = nonHeapMemory;
    }

    public void setAwaitFinalizationObjectCount(int awaitFinalizationObjectCount) {
        this.awaitFinalizationObjectCount = awaitFinalizationObjectCount;
    }
}
