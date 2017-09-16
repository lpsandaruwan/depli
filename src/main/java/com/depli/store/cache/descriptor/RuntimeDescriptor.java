package com.depli.store.cache.descriptor;

import java.lang.management.ManagementFactory;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Runtime Descriptor
 * <p>
 * Blueprint to keep memory usage data observed from {@link ManagementFactory#getRuntimeMXBean} of appropriate
 * remote JMX connection in runtime. This blueprint keeps several convenient fields for storing system properties
 * about the Java virtual machine.
 * <p>
 *
 * @author lpsandaruwan
 * @since 3/24/17
 */

public class RuntimeDescriptor {

    /*
     * The boot class path that is used by the bootstrap class loader to search for class files.
     */
    private String[] bootstrapClassPath;

    /*
     * The Java class path that is used by the system class loader to search for class files.
     */
    private String[] systemClassPath;

    /*
     * The input arguments passed to the Java virtual machine which does not include the arguments to
     * the <tt>main</tt> method.
     */
    private List<String> inputArguments;

    /*
     * The Java library path that is used by the system to search for libraries.
     */
    private String[] javaLibraryPath;

    /*
     * The version of the specification for the management interface implemented by the running
     * Java virtual machine.
     */
    private String managementInterfaceVersion;

    /*
     * The name representing the running Java virtual machine.
     */
    private String runningJvmName;

    /*
     * The approximate time when the Java virtual machine started.
     */
    private String jvmStartTime;

    /*
     * The Java virtual machine implementation name.
     */
    private String jvmName;

    /*
     * The Java virtual machine implementation vendor.
     */
    private String jvmVendor;

    /*
     * The Java virtual machine implementation version.
     */
    private String jvmVersion;

    // default constructor
    public RuntimeDescriptor() {
    }

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
        this.jvmStartTime = String.valueOf(LocalDateTime.ofInstant(
                Instant.ofEpochMilli(jvmStartTime), ZoneId.systemDefault())).replace("T", " ");
        this.jvmName = jvmName;
        this.jvmVendor = jvmVendor;
        this.jvmVersion = jvmVersion;
    }

    /**
     * Returns the boot class path that is used by the bootstrap class loader to search for class files.
     *
     * @return the boot class path
     */
    public String[] getBootstrapClassPath() {
        return bootstrapClassPath;
    }

    /**
     * Sets the boot class path that is used by the bootstrap class loader to search for class files.
     *
     * @param bootstrapClassPath the boot class path
     */
    public void setBootstrapClassPath(String[] bootstrapClassPath) {
        this.bootstrapClassPath = bootstrapClassPath;
    }

    /**
     * Returns the Java class path that is used by the system class loader to search for class files.
     *
     * @return Java class path as a list
     */
    public String[] getSystemClassPath() {
        return systemClassPath;
    }

    /**
     * Sets the Java class path that is used by the system class loader to search for class files.
     *
     * @param systemClassPath Java class path as a list
     */
    public void setSystemClassPath(String[] systemClassPath) {
        this.systemClassPath = systemClassPath;
    }

    /**
     * Returns the input arguments passed to the Java virtual machine which does not include the arguments
     * to the <tt>main</tt> method. This method returns an empty list if there is no input argument
     * to the Java virtual machine.
     *
     * @return a list of <tt>String</tt> objects; each element is an argument passed to the Java virtual machine
     */
    public List<String> getInputArguments() {
        return inputArguments;
    }

    /**
     * Sets the input arguments passed to the Java virtual machine which does not include the arguments
     * to the <tt>main</tt> method.
     *
     * @param inputArguments a list of <tt>String</tt> objects; each element is an argument
     *                       passed to the Java virtual machine
     */
    public void setInputArguments(List<String> inputArguments) {
        this.inputArguments = inputArguments;
    }

    /**
     * Returns the Java library path that is used by the system to search for libraries as a list.
     *
     * @return java libraries path as a list
     */
    public String[] getJavaLibraryPath() {
        return javaLibraryPath;
    }

    /**
     * Sets the Java library path that is used by the system to search for libraries as a list.
     *
     * @param javaLibraryPath java libraries path as a list
     */
    public void setJavaLibraryPath(String[] javaLibraryPath) {
        this.javaLibraryPath = javaLibraryPath;
    }

    /**
     * Returns the version of the specification for the management interface implemented by
     * the running Java virtual machine.
     *
     * @return the Java virtual machine specification version
     */
    public String getManagementInterfaceVersion() {
        return managementInterfaceVersion;
    }

    /**
     * Sets the version of the specification for the management interface implemented by
     * the running Java virtual machine.
     *
     * @param managementInterfaceVersion the Java virtual machine specification version
     */
    public void setManagementInterfaceVersion(String managementInterfaceVersion) {
        this.managementInterfaceVersion = managementInterfaceVersion;
    }

    /**
     * Returns the name representing the running Java virtual machine. The returned name string can be any
     * arbitrary string and a Java virtual machine implementation can choose to embed platform-specific
     * useful information in the returned name string. Each running virtual machine could have a different name.
     *
     * @return the name representing the running Java virtual machine
     */
    public String getRunningJvmName() {
        return runningJvmName;
    }

    /**
     * Sets the name representing the running Java virtual machine.
     *
     * @param runningJvmName the name representing the running Java virtual machine
     */
    public void setRunningJvmName(String runningJvmName) {
        this.runningJvmName = runningJvmName;
    }

    /**
     * Returns the approximate time when the Java virtual machine started.
     *
     * @return start time of the Java virtual machine
     */
    public String getJvmStartTime() {
        return jvmStartTime;
    }

    /**
     * Sets the approximate time when the Java virtual machine started.
     *
     * @param jvmStartTime start time of the Java virtual machine
     */
    public void setJvmStartTime(String jvmStartTime) {
        this.jvmStartTime = jvmStartTime;
    }

    /**
     * Returns the Java virtual machine implementation name.
     *
     * @return the Java virtual machine name
     */
    public String getJvmName() {
        return jvmName;
    }

    /**
     * Sets the Java virtual machine implementation name observed by the runtime management extension
     * bean connection.
     *
     * @param jvmName the Java virtual machine name
     */
    public void setJvmName(String jvmName) {
        this.jvmName = jvmName;
    }

    /**
     * Returns the Java virtual machine implementation vendor.
     *
     * @return the Java virtual machine implementation vendor
     */
    public String getJvmVendor() {
        return jvmVendor;
    }

    /**
     * Sets the Java virtual machine implementation vendor.
     *
     * @param jvmVendor the Java virtual machine implementation vendor
     */
    public void setJvmVendor(String jvmVendor) {
        this.jvmVendor = jvmVendor;
    }

    /**
     * Returns the Java virtual machine implementation version.
     *
     * @return the Java virtual machine implementation version
     */
    public String getJvmVersion() {
        return jvmVersion;
    }

    /**
     * Sets the Java virtual machine implementation version.
     *
     * @param jvmVersion the Java virtual machine implementation version
     */
    public void setJvmVersion(String jvmVersion) {
        this.jvmVersion = jvmVersion;
    }
}
