package com.depli.data.object;

import java.util.List;

/**
 * Keeps runtime MX Bean data
 *
 * Created by lpsandaruwan on 3/24/17.
 */

public class RuntimeData {

    private String bootstrapClassPath;
    private String systemClassPath;
    private List<String> inputArguments;
    private String javaLibraryPath;
    private String managementInterfaceVersion;
    private String runningJvmName;
    private long jvmStartTime;
    private long jvmUptime;
    private String jvmName;
    private String jvmVendor;
    private String jvmVersion;

    public RuntimeData() {}

    public void setData(String bootstrapClassPath,
                        String systemClassPath,
                        List<String> inputArguments,
                        String javaLibraryPath,
                        String managementInterfaceVersion,
                        String runningJvmName,
                        long jvmStartTime,
                        long jvmUptime,
                        String jvmName,
                        String jvmVendor,
                        String jvmVersion) {
        this.bootstrapClassPath = bootstrapClassPath;
        this.systemClassPath = systemClassPath;
        this.inputArguments = inputArguments;
        this.javaLibraryPath = javaLibraryPath;
        this.managementInterfaceVersion = managementInterfaceVersion;
        this.runningJvmName = runningJvmName;
        this.jvmStartTime = jvmStartTime;
        this.jvmUptime = jvmUptime;
        this.jvmName = jvmName;
        this.jvmVendor = jvmVendor;
        this.jvmVersion = jvmVersion;
    }

    public String getBootstrapClassPath() {
        return bootstrapClassPath;
    }

    public String getSystemClassPath() {
        return systemClassPath;
    }

    public List<String> getInputArguments() {
        return inputArguments;
    }

    public String getJavaLibraryPath() {
        return javaLibraryPath;
    }

    public String getManagementInterfaceVersion() {
        return managementInterfaceVersion;
    }

    public String getRunningJvmName() {
        return runningJvmName;
    }

    public long getJvmStartTime() {
        return jvmStartTime;
    }

    public long getJvmUptime() {
        return jvmUptime;
    }

    public String getJvmName() {
        return jvmName;
    }

    public String getJvmVendor() {
        return jvmVendor;
    }

    public String getJvmVersion() {
        return jvmVersion;
    }

    public void setBootstrapClassPath(String bootstrapClassPath) {
        this.bootstrapClassPath = bootstrapClassPath;
    }

    public void setSystemClassPath(String systemClassPath) {
        this.systemClassPath = systemClassPath;
    }

    public void setInputArguments(List<String> inputArguments) {
        this.inputArguments = inputArguments;
    }

    public void setJavaLibraryPath(String javaLibraryPath) {
        this.javaLibraryPath = javaLibraryPath;
    }

    public void setManagementInterfaceVersion(String managementInterfaceVersion) {
        this.managementInterfaceVersion = managementInterfaceVersion;
    }

    public void setRunningJvmName(String runningJvmName) {
        this.runningJvmName = runningJvmName;
    }

    public void setJvmStartTime(long jvmStartTime) {
        this.jvmStartTime = jvmStartTime;
    }

    public void setJvmUptime(long jvmUptime) {
        this.jvmUptime = jvmUptime;
    }

    public void setJvmName(String jvmName) {
        this.jvmName = jvmName;
    }

    public void setJvmVendor(String jvmVendor) {
        this.jvmVendor = jvmVendor;
    }

    public void setJvmVersion(String jvmVersion) {
        this.jvmVersion = jvmVersion;
    }
}
