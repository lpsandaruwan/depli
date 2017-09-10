package com.depli.domain.descriptor;

/**
 * Keeps class loading MX Bean domain
 * <p>
 * Created by lpsandaruwan on 3/24/17.
 */

public class ClassLoadingData {

    private int loadedClassCount;
    private int[] loadedClassCountData;
    private long totalLoadedClassCount;
    private long unloadedClassCount;

    public ClassLoadingData() {
        this.loadedClassCountData = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    public void setData(int loadedClassCount, long totalLoadedClassCount, long unloadedClassCount) {
        this.loadedClassCount = loadedClassCount;
        this.totalLoadedClassCount = totalLoadedClassCount;
        this.unloadedClassCount = unloadedClassCount;

        // update loaded class count domain array
        int index = 1;

        while (index < loadedClassCountData.length) {
            loadedClassCountData[index - 1] = loadedClassCountData[index];
            index++;
        }
        loadedClassCountData[index - 1] = loadedClassCount;
    }

    public int getLoadedClassCount() {
        return loadedClassCount;
    }

    public void setLoadedClassCount(int loadedClassCount) {
        this.loadedClassCount = loadedClassCount;
    }

    public int[] getLoadedClassCountData() {
        return loadedClassCountData;
    }

    public long getTotalLoadedClassCount() {
        return totalLoadedClassCount;
    }

    public void setTotalLoadedClassCount(long totalLoadedClassCount) {
        this.totalLoadedClassCount = totalLoadedClassCount;
    }

    public long getUnloadedClassCount() {
        return unloadedClassCount;
    }

    public void setUnloadedClassCount(long unloadedClassCount) {
        this.unloadedClassCount = unloadedClassCount;
    }
}
