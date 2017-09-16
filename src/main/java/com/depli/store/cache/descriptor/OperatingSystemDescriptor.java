package com.depli.store.cache.descriptor;

import java.lang.management.ManagementFactory;

/**
 * Operating System Descriptor
 * <p>
 * Entity to keep memory usage data observed from {@link ManagementFactory#getOperatingSystemMXBean} of appropriate
 * remote JMX connection in runtime. This descriptor contains information on the operating system on which
 * the Java virtual machine is running.
 *
 * @author lpsandaruwan
 * @since 3/24/17
 */

public class OperatingSystemDescriptor {

    /*
     * Host operating system architecture.
     */
    private String architecture;

    /*
     * The number of processors available to the virtual machine.
     */
    private int cpuCount;

    /*
     * Host operating system name.
     */
    private String operatingSystemName;

    /*
     * Host operating system version.
     */
    private String operatingSystemVersion;

    // default constructor
    public OperatingSystemDescriptor() {
    }

    public void setData(String architecture,
                        int cpuCount,
                        String operatingSystemName,
                        String operatingSystemVersion) {
        this.architecture = architecture;
        this.cpuCount = cpuCount;
        this.operatingSystemName = operatingSystemName;
        this.operatingSystemVersion = operatingSystemVersion;
    }

    /**
     * Returns the host operating system architecture.
     *
     * @return operating system architecture.
     */
    public String getArchitecture() {
        return architecture;
    }

    /**
     * Sets the host operating system architecture.
     *
     * @param architecture operating system architecture.
     */
    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    /**
     * Returns the number of processors available to the Java virtual machine.
     *
     * @return the number of processors
     */
    public int getCpuCount() {
        return cpuCount;
    }

    /**
     * Sets the value, number of processors available to the Java virtual machine.
     *
     * @param cpuCount the number of processors
     */
    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }

    /**
     * Returns the host operating system name.
     *
     * @return operating system name
     */
    public String getOperatingSystemName() {
        return operatingSystemName;
    }

    /**
     * Sets the host operating system name.
     *
     * @param operatingSystemName operating system name
     */
    public void setOperatingSystemName(String operatingSystemName) {
        this.operatingSystemName = operatingSystemName;
    }

    /**
     * Returns the host operating system version.
     *
     * @return operating system version
     */
    public String getOperatingSystemVersion() {
        return operatingSystemVersion;
    }

    /**
     * Sets the host operating system version.
     *
     * @param operatingSystemVersion operating system version
     */
    public void setOperatingSystemVersion(String operatingSystemVersion) {
        this.operatingSystemVersion = operatingSystemVersion;
    }
}
