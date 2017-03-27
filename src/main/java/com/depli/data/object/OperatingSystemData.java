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
    private double systemLoadAveragePerMinute;
    private String operatingSystemVersion;

    public OperatingSystemData () {}

    public void setData(String architecture,
                        int cpuCount,
                        String operatingSystemName,
                        double systemLoadAveragePerMinute,
                        String operatingSystemVersion) {
        this.architecture = architecture;
        this.cpuCount = cpuCount;
        this.operatingSystemName = operatingSystemName;
        this.systemLoadAveragePerMinute = systemLoadAveragePerMinute;
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

    public double getSystemLoadAveragePerMinute() {
        return systemLoadAveragePerMinute;
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

    public void setSystemLoadAveragePerMinute(double systemLoadAveragePerMinute) {
        this.systemLoadAveragePerMinute = systemLoadAveragePerMinute;
    }

    public void setOperatingSystemVersion(String operatingSystemVersion) {
        this.operatingSystemVersion = operatingSystemVersion;
    }
}
