package com.depli.store.cache.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Processing unit graph data
 *
 * Store data list to draw a graph for central processing units.
 *
 * @author lpsandaruwan
 * @since 9/18/17
 */

public class ProcessingUnitGraphData {

  /*
   * List to keep Y point values for the host CPU utilization graph.
   */
  private List<Float> hostCpuGraphData;

  /*
   * List to keep Y point values for the JVM CPU utilization graph.
   */
  private List<Float> jvmCpuGraphData;

  /*
   * Number of points in X axis.
   */
  private int pointIndex;

  public ProcessingUnitGraphData(int pointIndex) {
    this.pointIndex = pointIndex;

    hostCpuGraphData = new ArrayList<>();
    jvmCpuGraphData = new ArrayList<>();

    /* Initialize graph data values for given point index */
    refreshLists();
  }

  /**
   * Returns a List object containing host CPU utilization values for Y axis.
   *
   * @return List object containing host CPU utilization values
   */
  public List<Float> getHostCpuGraphData() {
    return hostCpuGraphData;
  }

  /**
   * Updates List object consisting float values, host CPU utilization values for Y axis.
   *
   * @param currentUtilizationPercentage latest value of host CPU utilization
   */
  public void setHostCpuGraphData(Float currentUtilizationPercentage) {
    hostCpuGraphData.remove(0);
    hostCpuGraphData.add(currentUtilizationPercentage);
  }

  /**
   * Returns a List object containing JVM CPU utilization values for Y axis.
   *
   * @return List object containing JVM CPU utilization values
   */
  public List<Float> getJvmCpuGraphData() {
    return jvmCpuGraphData;
  }

  /**
   * Updates List object consisting float values, JVM CPU utilization values for Y axis.
   *
   * @param currentUtilizationPercentage latest value of JVM CPU utilization
   */
  public void setJvmCpuGraphData(Float currentUtilizationPercentage) {
    jvmCpuGraphData.remove(0);
    jvmCpuGraphData.add(currentUtilizationPercentage);
  }

  /**
   * Returns number of points in X axis value.
   *
   * @return number of points in X axis amount
   */
  public int getPointIndex() {
    return pointIndex;
  }

  /**
   * Set number of points in X axis value and update list contents.
   *
   * @param pointIndex number of points in X axis amount
   */
  public void setPointIndex(int pointIndex) {
    this.pointIndex = pointIndex;
    refreshLists();
  }

  /**
   * Set dynamic data fields.
   *
   * @param hostCpuGraphData host CPU utilization percentage
   * @param jvmCpuGraphData JVM CPU utilization percentage
   */
  public void setDynamicData(Float hostCpuGraphData, Float jvmCpuGraphData) {
    this.setHostCpuGraphData(hostCpuGraphData);
    this.setJvmCpuGraphData(jvmCpuGraphData);
  }

  /**
   * Refresh graph data list according to the pointIndex
   */
  public void refreshLists() {
    hostCpuGraphData.clear();
    jvmCpuGraphData.clear();

    for (int i = 0; i < pointIndex; i++) {
      hostCpuGraphData.add(0f);
      jvmCpuGraphData.add(0f);
    }
  }
}
