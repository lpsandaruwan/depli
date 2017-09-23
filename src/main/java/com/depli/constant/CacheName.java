package com.depli.constant;

/**
 * Cache Names
 * To be used by the key, value data store to set and get cache by cache name.
 *
 * @author lpsandaruwan
 * @since 9/10/17
 */

public class CacheName {

  public static final String CONNECTION_TREE_CACHE = "connectionTreeCache";

  public static final String CLASS_LOADING_DESCRIPTOR_CACHE = "classLoadingDescriptorCache";
  public static final String MEMORY_USAGE_DESCRIPTOR_CACHE = "memoryUsageDescriptorCache";
  public static final String OPERATING_SYSTEM_DESCRIPTOR_CACHE = "operatingSystemDescriptorCache";
  public static final String PLATFORM_RESOURCES_DESCRIPTOR_CACHE = "platformResourcesDescriptorCache";
  public static final String RUNTIME_DESCRIPTOR_CACHE = "runtimeDescriptorCache";
  public static final String THREAD_DESCRIPTOR_CACHE = "threadDescriptorCache";

  public static final String CPU_GRAPH_DATA_CACHE = "cpuGraphDataCache";
  public static final String CLASS_LOADING_GRAPH_DATA_CACHE = "classLoadingGraphDataCache";

  private CacheName() {
  }
}
