package com.depli.store.cache.descriptor;

/**
 * OperatingSystemDataObserver
 * <p>
 * Keeps operating system MX Bean store
 * <p>
 * Created by lpsandaruwan on 3/24/17.
 */

public class OperatingSystemData {

    private String architecture;
    private int cpuCount;
    private String operatingSystemName;
    private String operatingSystemVersion;

    // default constructor
    public OperatingSystemData() {
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
