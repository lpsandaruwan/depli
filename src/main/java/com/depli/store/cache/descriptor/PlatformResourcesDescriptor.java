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

    // default constructor
    public PlatformResourcesDescriptor() {
    }

    public void setData(double hostCpuUsage,
                        long freeSwapSpace,
                        long freePhysicalMemory,
                        double jvmCpuUsage,
                        long totalPhysicalMemory,
                        long totalSwapSpace) {
        if (hostCpuUsage == -1) {
            this.hostCpuUsage = -1;
        } else {
            this.hostCpuUsage = Math.round(hostCpuUsage * 100f * 10f) / 10f;
        }

        if (freeSwapSpace == -1) {
            this.freeSwapSpace = -1;
        } else {
            this.freeSwapSpace = Math.round((freeSwapSpace / (1024f * 1024f)) * 10f) / 10f;
        }

        if (freePhysicalMemory == -1) {
            this.freePhysicalMemory = -1;
        } else {
            this.freePhysicalMemory = Math.round((freePhysicalMemory / (1024f * 1024f)) * 10f) / 10f;
        }

        if (jvmCpuUsage == -1) {
            this.jvmCpuUsage = -1;
        } else {
            this.jvmCpuUsage = Math.round(jvmCpuUsage * 10f) / 10f;
        }

        if (totalPhysicalMemory == -1) {
            this.totalPhysicalMemory = -1;
        } else {
            this.totalPhysicalMemory = Math.round((totalPhysicalMemory / (1024f * 1024f)) * 10f) / 10f;
        }

        if (totalSwapSpace == -1) {
            this.totalSwapSpace = -1;
        } else {
            this.totalSwapSpace = Math.round((totalSwapSpace / (1024f * 1024f)) * 10f) / 10f;
        }
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
}
