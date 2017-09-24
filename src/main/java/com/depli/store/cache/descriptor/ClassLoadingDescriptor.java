package com.depli.store.cache.descriptor;

import java.lang.management.ManagementFactory;

/**
 * Class Loading Data Descriptor
 *
 * Blueprint to keep class loading data observed from {@link
 * ManagementFactory#getClassLoadingMXBean} of appropriate remote JMX connection in runtime.
 *
 * @author Lahiru Pathirage
 * @since 3/22/17
 */

public class ClassLoadingDescriptor {

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

  public ClassLoadingDescriptor() {
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
   * @param loadedClassCount the number of currently loaded classes
   */
  public void setLoadedClassCount(int loadedClassCount) {
    this.loadedClassCount = loadedClassCount;
  }

  /**
   * Returns the total number of classes that have been loaded since
   * the Java virtual machine has started execution.
   *
   * @return the total number of classes loaded
   */
  public long getTotalLoadedClassCount() {
    return totalLoadedClassCount;
  }

  /**
   * Sets the total number of classes that have been loaded since
   * the Java virtual machine has started execution.
   *
   * @param totalLoadedClassCount the total number of classes loaded.
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
   * @param unloadedClassCount the total number of unloaded classes
   */
  public void setUnloadedClassCount(long unloadedClassCount) {
    this.unloadedClassCount = unloadedClassCount;
  }


  /**
   * Sets the dynamic data fields.
   *
   * @param loadedClassCount the number of currently loaded classes
   * @param totalLoadedClassCount the total number of classes loaded
   * @param unloadedClassCount the total number of unloaded classes
   */
  public void setDynamicData(int loadedClassCount, long totalLoadedClassCount,
      long unloadedClassCount) {
    this.loadedClassCount = loadedClassCount;
    this.totalLoadedClassCount = totalLoadedClassCount;
    this.unloadedClassCount = unloadedClassCount;
  }
}
