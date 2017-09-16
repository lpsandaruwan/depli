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
    MEMORY_USAGE("memoryUsageCache"),
    OPERATING_SYSTEM("operatingSystemCache"),
    PLATFORM_RESOURCES("platformResourcesCache"),
    RUNTIME("runtimeCache"),
    STATISTICS("statisticsCache"),
    THREAD("threadCache");

    private String value;

    CacheName(String value) {
        this.value = value;
    }

    public String getCacheName() {
        return value;
    }
}
