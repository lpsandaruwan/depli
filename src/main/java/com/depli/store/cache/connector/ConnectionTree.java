package com.depli.store.cache.connector;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;

/**
 * Connection Tree
 * <p>
 * Management extension bean proxy connection tree for appropriate node.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

public class ConnectionTree {

  private MainConnector mainConnector;
  private ClassLoadingMXBean classLoadingMXBean;
  private MemoryMXBean memoryMXBean;
  private OperatingSystemMXBean operatingSystemMXBean;
  private com.sun.management.OperatingSystemMXBean platformResourcesMXBean;
  private RuntimeMXBean runtimeMXBean;
  private ThreadMXBean threadMXBean;

  public ConnectionTree(
      MainConnector mainConnector,
      ClassLoadingMXBean classLoadingMXBean,
      MemoryMXBean memoryMXBean,
      OperatingSystemMXBean operatingSystemMXBean,
      com.sun.management.OperatingSystemMXBean platformResourcesMXBean,
      RuntimeMXBean runtimeMXBean,
      ThreadMXBean threadMXBean
  ) {
    this.mainConnector = mainConnector;
    this.classLoadingMXBean = classLoadingMXBean;
    this.memoryMXBean = memoryMXBean;
    this.operatingSystemMXBean = operatingSystemMXBean;
    this.platformResourcesMXBean = platformResourcesMXBean;
    this.runtimeMXBean = runtimeMXBean;
    this.threadMXBean = threadMXBean;
  }

  public ClassLoadingMXBean getClassLoadingMXBean() {
    return classLoadingMXBean;
  }

  public void setClassLoadingMXBean(ClassLoadingMXBean classLoadingMXBean) {
    this.classLoadingMXBean = classLoadingMXBean;
  }

  public MemoryMXBean getMemoryMXBean() {
    return memoryMXBean;
  }

  public void setMemoryMXBean(MemoryMXBean memoryMXBean) {
    this.memoryMXBean = memoryMXBean;
  }

  public OperatingSystemMXBean getOperatingSystemMXBean() {
    return operatingSystemMXBean;
  }

  public void setOperatingSystemMXBean(OperatingSystemMXBean operatingSystemMXBean) {
    this.operatingSystemMXBean = operatingSystemMXBean;
  }

  public com.sun.management.OperatingSystemMXBean getPlatformResourcesMXBean() {
    return platformResourcesMXBean;
  }

  public void setPlatformResourcesMXBean(
      com.sun.management.OperatingSystemMXBean platformResourcesMXBean) {
    this.platformResourcesMXBean = platformResourcesMXBean;
  }

  public RuntimeMXBean getRuntimeMXBean() {
    return runtimeMXBean;
  }

  public void setRuntimeMXBean(RuntimeMXBean runtimeMXBean) {
    this.runtimeMXBean = runtimeMXBean;
  }

  public ThreadMXBean getThreadMXBean() {
    return threadMXBean;
  }

  public void setThreadMXBean(ThreadMXBean threadMXBean) {
    this.threadMXBean = threadMXBean;
  }

  public MainConnector getMainConnector() {
    return mainConnector;
  }

  public void setMainConnector(MainConnector mainConnector) {
    this.mainConnector = mainConnector;
  }
}
