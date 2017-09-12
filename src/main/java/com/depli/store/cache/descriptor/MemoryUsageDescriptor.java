package com.depli.store.cache.descriptor;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

/**
 * Memory Usage Descriptor
 * <p>
 * Blueprint to keep memory usage data observed from {@link ManagementFactory#getMemoryMXBean} of appropriate
 * remote JMX connection in runtime.
 *
 * @author lpsandaruwan
 * @since 3/24/17
 */

public class MemoryUsageDescriptor {

    /*
     * Memory usage details about runtime data area from which memory for all class instances and
     * arrays are allocated.
     */
    private final DMemoryUsage heapMemory;

    /*
     * Memory usage details about stored per-class structures such as a runtime constant pool,
     * field and method data, and the code for methods and constructors.
     */
    private final DMemoryUsage nonHeapMemory;

    /*
     * The approximate number of objects for which finalization is pending.
     */
    private int awaitFinalizationObjectCount;

    public MemoryUsageDescriptor() {
        heapMemory = new DMemoryUsage();
        nonHeapMemory = new DMemoryUsage();
    }

    public void setData(MemoryUsage heapMemory, MemoryUsage nonHeapMemory, int awaitFinalizationObjectCount) {
        this.heapMemory.setData(heapMemory);
        this.nonHeapMemory.setData(nonHeapMemory);
        this.awaitFinalizationObjectCount = awaitFinalizationObjectCount;
    }

    /**
     * Returns the current memory usage of the heap that is used for object allocation.
     *
     * @return MemoryUsage object representing the heap memory usage
     */
    public DMemoryUsage getHeapMemory() {
        return heapMemory;
    }

    /**
     * Returns the current memory usage of non-heap memory that is used by the Java virtual machine.
     *
     * @return MemoryUsage object representing the non-heap memory usage.
     */
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
