package com.depli.store.cache.descriptor;

import java.lang.management.ManagementFactory;

/**
 * Operating System Descriptor
 * <p>
 * Entity to keep memory usage data observed from {@link ManagementFactory#getOperatingSystemMXBean} of appropriate
 * remote JMX connection in runtime.
 *
 * @author lpsandaruwan
 * @since 3/24/17
 */

public class OperatingSystemDescriptor {

    private String architecture;
    private int cpuCount;
    private String operatingSystemName;
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

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }

    public String getOperatingSystemName() {
        return operatingSystemName;
    }

    public void setOperatingSystemName(String operatingSystemName) {
        this.operatingSystemName = operatingSystemName;
    }

    public String getOperatingSystemVersion() {
        return operatingSystemVersion;
    }

    public void setOperatingSystemVersion(String operatingSystemVersion) {
        this.operatingSystemVersion = operatingSystemVersion;
    }
}
