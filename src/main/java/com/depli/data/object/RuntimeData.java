package com.depli.data.object;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Keeps runtime MX Bean data
 *
 * Created by lpsandaruwan on 3/24/17.
 */

public class RuntimeData {

    private String[] bootstrapClassPath;
    private String[] systemClassPath;
    private List<String> inputArguments;
    private String[] javaLibraryPath;
    private String managementInterfaceVersion;
    private String runningJvmName;
    private String jvmStartTime;
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
                        String jvmName,
                        String jvmVendor,
                        String jvmVersion) {
        this.bootstrapClassPath = bootstrapClassPath.split(":", -1);
        this.systemClassPath = systemClassPath.split(":", -1);
        this.inputArguments = inputArguments;
        this.javaLibraryPath = javaLibraryPath.split("::", -1);
        this.managementInterfaceVersion = managementInterfaceVersion;
        this.runningJvmName = runningJvmName;
        this.jvmStartTime = String.valueOf(LocalDateTime.ofInstant(Instant.ofEpochMilli(jvmStartTime), ZoneId.systemDefault())).replace("T", " ");
        this.jvmName = jvmName;
        this.jvmVendor = jvmVendor;
        this.jvmVersion = jvmVersion;
    }

    public String[] getBootstrapClassPath() {
        return bootstrapClassPath;
    }

    public String[] getSystemClassPath() {
        return systemClassPath;
    }

    public List<String> getInputArguments() {
        return inputArguments;
    }

    public String[] getJavaLibraryPath() {
        return javaLibraryPath;
    }

    public String getManagementInterfaceVersion() {
        return managementInterfaceVersion;
    }

    public String getRunningJvmName() {
        return runningJvmName;
    }

    public String getJvmStartTime() {
        return jvmStartTime;
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

    public void setBootstrapClassPath(String[] bootstrapClassPath) {
        this.bootstrapClassPath = bootstrapClassPath;
    }

    public void setSystemClassPath(String[] systemClassPath) {
        this.systemClassPath = systemClassPath;
    }

    public void setInputArguments(List<String> inputArguments) {
        this.inputArguments = inputArguments;
    }

    public void setJavaLibraryPath(String[] javaLibraryPath) {
        this.javaLibraryPath = javaLibraryPath;
    }

    public void setManagementInterfaceVersion(String managementInterfaceVersion) {
        this.managementInterfaceVersion = managementInterfaceVersion;
    }

    public void setRunningJvmName(String runningJvmName) {
        this.runningJvmName = runningJvmName;
    }

    public void setJvmStartTime(String jvmStartTime) {
        this.jvmStartTime = jvmStartTime;
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
