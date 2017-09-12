package com.depli.store.cache.descriptor;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class Loading Data Descriptor
 * <p>
 * Blueprint to keep class loading data observed from {@link ManagementFactory#getClassLoadingMXBean} of appropriate
 * remote JMX connection in runtime.
 *
 * @author Lahiru Pathirage
 * @since 3/22/17
 */

public class ClassLoadingDataDescriptor {

    /*
     * Number of classes that are currently loaded in the Java virtual machine.
     */
    private int loadedClassCount;

    /*
     * Total number of classes that have been loaded since the Java virtual
     * machine has started execution.
     */
    private long totalLoadedClassCount;

    /*
     * Total number of classes unloaded since the Java virtual machine
     * has started execution.
     */
    private long unloadedClassCount;

    /*
    * Array of number of classes that are currently loaded in the Java virtual machine
    * at number of particular intervals.
    */
    private ArrayList<Integer> loadedClassCountGraphData;

    public ClassLoadingDataDescriptor() {
        this.loadedClassCountGraphData = new ArrayList<>(Arrays.asList(
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        ));
        this.loadedClassCount = 0;
        this.totalLoadedClassCount = 0;
        this.unloadedClassCount = 0;
    }

    /**
     * Returns the number of classes that are currently loaded in the
     * Java virtual machine.
     *
     * @return the number of currently loaded classes.
     */
    public int getLoadedClassCount() {
        return loadedClassCount;
    }

    /**
     * Sets the number of classes that are currently loaded in the
     * Java virtual machine.
     *
     * @param loadedClassCount the number of currently loaded classes.
     */
    public void setLoadedClassCount(int loadedClassCount) {
        this.loadedClassCount = loadedClassCount;
    }

    /**
     * Returns the total number of classes that have been loaded since
     * the Java virtual machine has started execution.
     *
     * @return the total number of classes loaded.
     */
    public long getTotalLoadedClassCount() {
        return totalLoadedClassCount;
    }

    /**
     * Sets the total number of classes that have been loaded since
     * the Java virtual machine has started execution.
     *
     * @param  totalLoadedClassCount the total number of classes loaded.
     *
     */
    public void setTotalLoadedClassCount(long totalLoadedClassCount) {
        this.totalLoadedClassCount = totalLoadedClassCount;
    }

    /**
     * Returns the total number of classes unloaded since the Java virtual machine
     * has started execution.
     *
     * @return the total number of unloaded classes.
     */
    public long getUnloadedClassCount() {
        return unloadedClassCount;
    }

    /**
     * Sets the total number of classes unloaded since the Java virtual machine
     * has started execution.
     *
     * @param unloadedClassCount the total number of unloaded classes.
     */
    public void setUnloadedClassCount(long unloadedClassCount) {
        this.unloadedClassCount = unloadedClassCount;
    }

    /**
     * Returns the list of number of classes in a particular number of time
     * intervals that are currently loaded in the Java virtual machine for
     * the purpose of graphing.
     *
     * @return the list of number of currently loaded classes in a particular
     * number of time intervals.
     */
    public List<Integer> getLoadedClassCountGraphData() {
        return loadedClassCountGraphData;
    }

    /**
     * Sets the last value of the list of number of classes in a particular number
     * of time intervals that are currently loaded in the Java virtual machine for
     * the purpose of graphing.
     *
     * @param loadedClassCountGraphDataValue the latest value of number of currently loaded classes
     *                                       in the latest time interval.
     */
    public void setLoadedClassCountGraphData(int loadedClassCountGraphDataValue) {
        this.loadedClassCountGraphData.remove(0);
        this.loadedClassCountGraphData.add(loadedClassCountGraphDataValue);
    }

    public void setAllData(int loadedClassCount, long totalLoadedClassCount, long unloadedClassCount) {
        this.setLoadedClassCount(loadedClassCount);
        this.setTotalLoadedClassCount(totalLoadedClassCount);
        this.setUnloadedClassCount(unloadedClassCount);
        this.setLoadedClassCountGraphData(loadedClassCount);
    }
}
