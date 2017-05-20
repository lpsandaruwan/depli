package com.depli.data.object;

/** OperatingSystemData
 *
 * Keeps operating system MX Bean data
 *
 * Created by lpsandaruwan on 3/24/17.
 */

public class OperatingSystemData {

    private String architecture;
    private int cpuCount;
    private String operatingSystemName;
    private String operatingSystemVersion;

    // default constructor
    public OperatingSystemData () {}

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

    public int getCpuCount() {
        return cpuCount;
    }

    public String getOperatingSystemName() {
        return operatingSystemName;
    }

    public String getOperatingSystemVersion() {
        return operatingSystemVersion;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }

    public void setOperatingSystemName(String operatingSystemName) {
        this.operatingSystemName = operatingSystemName;
    }

    public void setOperatingSystemVersion(String operatingSystemVersion) {
        this.operatingSystemVersion = operatingSystemVersion;
    }
}
