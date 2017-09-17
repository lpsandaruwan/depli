package com.depli.utility.scheduler.task;

/**
 * Dynamic Data Mediator Factory
 *
 * Interface for the Async methods to refresh dynamic descriptor data in caches.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

public interface DynamicDataMediatorFactory {

  /**
   * Async method to refresh dynamic data in cache stores for given nodeId
   *
   * @param nodeId JMXNode Id
   */
  public void mediate(Long nodeId);
}
