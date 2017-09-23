package com.depli.store.cache.descriptor;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

/**
 * Memory Usage Descriptor
 *
 * Blueprint to keep memory usage data observed from {@link ManagementFactory#getMemoryMXBean} of
 * appropriate remote JMX connection in runtime.
 *
 * @author lpsandaruwan
 * @since 3/24/17
 */

public class MemoryDescriptor {

  /*
   * Memory usage details about runtime data area from which memory for all class instances and
   * arrays are allocated.
   */
  private final MemoryUsageData heapMemory;

  /*
   * Memory usage details about stored per-class structures such as a runtime constant pool,
   * field and method data, and the code for methods and constructors.
   */
  private final MemoryUsageData nonHeapMemory;

  /*
   * The approximate number of objects for which finalization is pending.
   */
  private int awaitFinalizationObjectCount;

  public MemoryDescriptor() {
    heapMemory = new MemoryUsageData();
    nonHeapMemory = new MemoryUsageData();
    awaitFinalizationObjectCount = 0;
  }

  /**
   * Returns the current memory usage of the heap that is used for object allocation.
   *
   * @return MemoryUsage object representing the heap memory usage
   */
  public MemoryUsageData getHeapMemory() {
    return heapMemory;
  }

  /**
   * Returns the current memory usage of non-heap memory that is used by the Java virtual machine.
   *
   * @return MemoryUsage object representing the non-heap memory usage.
   */
  public MemoryUsageData getNonHeapMemory() {
    return nonHeapMemory;
  }


  public int getAwaitFinalizationObjectCount() {
    return awaitFinalizationObjectCount;
  }

  public void setAwaitFinalizationObjectCount(int awaitFinalizationObjectCount) {
    this.awaitFinalizationObjectCount = awaitFinalizationObjectCount;
  }

  public void setDynamicData(MemoryUsage heapMemory, MemoryUsage nonHeapMemory,
      int awaitFinalizationObjectCount) {
    this.heapMemory.setDynamicData(heapMemory);
    this.nonHeapMemory.setDynamicData(nonHeapMemory);
    this.awaitFinalizationObjectCount = awaitFinalizationObjectCount;
  }
}
