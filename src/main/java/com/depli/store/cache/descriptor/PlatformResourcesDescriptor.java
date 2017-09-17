package com.depli.store.cache.descriptor;

/**
 * Platform resources System Descriptor
 * <p>
 * Blueprint to keep memory usage data observed from com.sun.management.OperatingSystemMXBean of appropriate
 * remote JMX connection in runtime. This blueprint keeps several fields for keeping system resources usage about
 * the operating system on which the Java virtual machine is running.
 * <p>
 *
 * @author lpsandaruwan
 * @since 3/28/17
 */

public class PlatformResourcesDescriptor {

    /*
     * CPU utilization percentage of the host machine where Java virtual machine runs.
     */
    private float hostCpuUsage;

    /*
     * Free amount of physical system memory of the host machine where Java virtual machine runs.
     */
    private float freePhysicalMemory;

    /*
     * Free amount of swap memory of the host machine where Java virtual machine runs.
     */
    private float freeSwapSpace;

    /*
     * CPU utilization related to running Java virtual machine.
     */
    private float jvmCpuUsage;

    /*
     * Total amount of physical system memory of the host machine where Java virtual machine runs.
     */
    private float totalPhysicalMemory;

    /*
     * Total amount of swap memory of the host machine where Java virtual machine runs.
     */
    private float totalSwapSpace;

    public PlatformResourcesDescriptor() {
        hostCpuUsage = -1;
        freePhysicalMemory = -1;
        freeSwapSpace = -1;
        jvmCpuUsage = -1;
        totalPhysicalMemory = -1;
        totalSwapSpace = -1;
    }


    /**
     * Returns CPU utilization percentage of the host machine where Java virtual machine runs.
     *
     * @return host CPU utilization percentage
     */
    public float getHostCpuUsage() {
        return hostCpuUsage;
    }


    /**
     * Sets CPU utilization percentage of the host machine where Java virtual machine runs.
     *
     * @param hostCpuUsage host CPU utilization percentage
     */
    public void setHostCpuUsage(float hostCpuUsage) {
        this.hostCpuUsage = hostCpuUsage;
    }

    /**
     * Returns free amount of swap memory of the host machine where Java virtual machine runs.
     *
     * @return free swap memory amount
     */
    public float getFreeSwapSpace() {
        return freeSwapSpace;
    }

    /**
     * Sets free amount of swap memory of the host machine where Java virtual machine runs.
     *
     * @param freeSwapSpace free swap memory amount
     */
    public void setFreeSwapSpace(float freeSwapSpace) {
        this.freeSwapSpace = freeSwapSpace;
    }

    /**
     * Returns free amount of physical system memory of the host machine where Java virtual machine runs.
     *
     * @return free amount of physical memory
     */
    public float getFreePhysicalMemory() {
        return freePhysicalMemory;
    }

    /**
     * Sets free amount of physical system memory of the host machine where Java virtual machine runs.
     *
     * @param freePhysicalMemory free amount of physical memory
     */
    public void setFreePhysicalMemory(float freePhysicalMemory) {
        this.freePhysicalMemory = freePhysicalMemory;
    }

    /**
     * Returns CPU utilization related to running Java virtual machine.
     *
     * @return CPU usage percentage of java virtual machine
     */
    public float getJvmCpuUsage() {
        return jvmCpuUsage;
    }

    /**
     * Sets CPU utilization related to running Java virtual machine.
     *
     * @param jvmCpuUsage CPU usage percentage of java virtual machine
     */
    public void setJvmCpuUsage(float jvmCpuUsage) {
        this.jvmCpuUsage = jvmCpuUsage;
    }

    /**
     * Returns total amount of physical system memory of the host machine where Java virtual machine runs.
     *
     * @return total amount of physical memory
     */
    public float getTotalPhysicalMemory() {
        return totalPhysicalMemory;
    }

    /**
     * Sets total amount of physical system memory of the host machine where Java virtual machine runs.
     *
     * @param totalPhysicalMemory total amount of physical memory
     */
    public void setTotalPhysicalMemory(float totalPhysicalMemory) {
        this.totalPhysicalMemory = totalPhysicalMemory;
    }

    /**
     * Returns total amount of swap memory of the host machine where Java virtual machine runs.
     *
     * @return total swap memory amount
     */
    public float getTotalSwapSpace() {
        return totalSwapSpace;
    }

    /**
     * Sets total amount of swap memory of the host machine where Java virtual machine runs.
     *
     * @param totalSwapSpace total swap memory amount
     */
    public void setTotalSwapSpace(float totalSwapSpace) {
        this.totalSwapSpace = totalSwapSpace;
    }

    /**
     * Sets variable data fields over time.
     *
     * @param freePhysicalMemory free amount of physical memory
     * @param freeSwapSpace      free swap memory amount
     * @param hostCpuUsage       host CPU utilization percentage
     * @param jvmCpuUsage        CPU usage percentage of java virtual machine
     */
    public void setDynamicData(float freePhysicalMemory, float freeSwapSpace, float hostCpuUsage, float jvmCpuUsage) {
        this.freePhysicalMemory = freePhysicalMemory;
        this.freeSwapSpace = freeSwapSpace;
        this.hostCpuUsage = hostCpuUsage;
        this.jvmCpuUsage = jvmCpuUsage;
    }

    /**
     * Sets static data fields.
     *
     * @param totalPhysicalMemory total amount of physical memory
     * @param totalSwapSpace      total swap memory amount
     */
    public void setStaticData(float totalPhysicalMemory, float totalSwapSpace) {
        this.totalPhysicalMemory = totalPhysicalMemory;
        this.totalSwapSpace = totalSwapSpace;
    }
}
