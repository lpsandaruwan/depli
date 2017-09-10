package com.depli.store.cache.descriptor;

import java.lang.management.MemoryUsage;

/**
 * Keeps memory MX Bean store
 * <p>
 * Created by lpsandaruwan on 3/24/17.
 */

public class MemoryData {

    private final DMemoryUsage heapMemory;
    private final DMemoryUsage nonHeapMemory;
    private int awaitFinalizationObjectCount;

    public MemoryData() {
        heapMemory = new DMemoryUsage();
        nonHeapMemory = new DMemoryUsage();
    }

    public void setData(MemoryUsage heapMemory, MemoryUsage nonHeapMemory, int awaitFinalizationObjectCount) {
        this.heapMemory.setData(heapMemory);
        this.nonHeapMemory.setData(nonHeapMemory);
        this.awaitFinalizationObjectCount = awaitFinalizationObjectCount;
    }

    public DMemoryUsage getHeapMemory() {
        return heapMemory;
    }

    public DMemoryUsage getNonHeapMemory() {
        return nonHeapMemory;
    }

    public int getAwaitFinalizationObjectCount() {
        return awaitFinalizationObjectCount;
    }

    public void setAwaitFinalizationObjectCount(int awaitFinalizationObjectCount) {
        this.awaitFinalizationObjectCount = awaitFinalizationObjectCount;
    }
}
