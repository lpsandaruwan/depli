package com.depli.data.object;

/**
 * Keeps class loading MX Bean data
 *
 * Created by lpsandaruwan on 3/24/17.
 */

public class ClassLoadingData {

    private int loadedClassCount;
    private long totalLoadedClassCount;
    private long unloadedClassCount;

    public  ClassLoadingData() {}

    public void setData(int loadedClassCount, long totalLoadedClassCount, long unloadedClassCount) {
        this.loadedClassCount = loadedClassCount;
        this.totalLoadedClassCount = totalLoadedClassCount;
        this.unloadedClassCount = unloadedClassCount;
    }

    public int getLoadedClassCount() {
        return loadedClassCount;
    }

    public long getTotalLoadedClassCount() {
        return totalLoadedClassCount;
    }

    public long getUnloadedClassCount() {
        return unloadedClassCount;
    }

    public void setLoadedClassCount(int loadedClassCount) {
        this.loadedClassCount = loadedClassCount;
    }

    public void setTotalLoadedClassCount(long totalLoadedClassCount) {
        this.totalLoadedClassCount = totalLoadedClassCount;
    }

    public void setUnloadedClassCount(long unloadedClassCount) {
        this.unloadedClassCount = unloadedClassCount;
    }
}
