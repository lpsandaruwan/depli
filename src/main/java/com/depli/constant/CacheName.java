package com.depli.constant;

/**
 * Cache Names
 * To be used by the key, value data store to set and get cache by cache name.
 *
 * @author lpsandaruwan
 * @since 9/10/17
 */

public enum CacheName {
    CLASS_LOADING_DATA("classLoadingDataCache"),
    CONNECTORS_TREE("connectorsTree"),
    MEMORY_USAGE_DATA("memoryUsageDataCache"),
    OPERATING_SYSTEM_DATA("operatingSystemDataCache"),
    PLATFORM_SYSTEM_DATA("platformSystemDataCache"),
    RUNTIME_DATA("runtimeDataCache"),
    STATISTICS_DATA("statisticsDataCache"),
    THREAD_DATA("threadDataCache");

    private String value;

    CacheName(String value) {
        this.value = value;
    }

    public String getCacheName() {
        return value;
    }
}
