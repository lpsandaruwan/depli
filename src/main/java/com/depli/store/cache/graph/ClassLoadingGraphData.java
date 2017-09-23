package com.depli.store.cache.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Class loading graph data
 *
 * Store data list to draw a graph for central processing units.
 *
 * @author lpsandaruwan
 * @since 9/23/17
 */

public class ClassLoadingGraphData {

  /*
   * List to keep Y axis values for the loaded class count graph.
   */
  private List<Integer> loadedClassCountGraphData;

  /*
   * Number of points in X axis.
   */
  private int pointIndex;

  public ClassLoadingGraphData(int pointIndex) {
    this.pointIndex = pointIndex;
    loadedClassCountGraphData = new ArrayList<>();

    /* Initialize graph data values for given point index */
    refreshLists();
  }

  /**
   * Returns a List object containing loaded class count values for Y axis of the class loading
   * graph.
   *
   * @return List object containing loaded class count values values
   */
  public List<Integer> getLoadedClassCountGraphData() {
    return loadedClassCountGraphData;
  }

  /**
   * Returns a List object containing loaded class count values for Y axis of the class loading
   * graph.
   *
   * @param loadedClassCount List object containing loaded class count values values
   */
  public void setLoadedClassCountGraphData(Integer loadedClassCount) {
    loadedClassCountGraphData.remove(0);
    loadedClassCountGraphData.add(loadedClassCount);
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
   * @param loadedClassCount List object containing loaded class count values values
   */
  public void setDynamicData(Integer loadedClassCount) {
    this.setLoadedClassCountGraphData(loadedClassCount);
  }

  /**
   * Refresh graph data list according to the pointIndex
   */
  public void refreshLists() {
    loadedClassCountGraphData.clear();

    for (int i = 0; i < pointIndex; i++) {
      loadedClassCountGraphData.add(0);
    }
  }
}
