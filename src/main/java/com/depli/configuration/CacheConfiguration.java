package com.depli.configuration;

import infinispan.autoconfigure.embedded.InfinispanCacheConfigurer;
import infinispan.autoconfigure.embedded.InfinispanGlobalConfigurer;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;

import static com.depli.constant.CacheName.*;
import static com.depli.constant.CacheName.THREAD_DATA;

/**
 * Created by lpsandaruwan on 9/10/17.
 */
@Component
public class CacheConfiguration {

    private static final String LOGGER_TEXT_DEFINING_CACHE = "Defining {} configuration";
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Injects {@link org.infinispan.configuration.global.GlobalConfiguration}.
     */
    @Bean
    public InfinispanGlobalConfigurer globalConfiguration() {
        logger.info("Defining Global Configuration");
        return () -> GlobalConfigurationBuilder
                .defaultClusteredBuilder()
                .globalJmxStatistics().allowDuplicateDomains(true)
                .build();
    }

    /**
     * Injects class loading data cache {@link Configuration}.
     */
    @Bean
    public InfinispanCacheConfigurer classLoadingDataConfigurer() {
        logger.info(LOGGER_TEXT_DEFINING_CACHE, CLASS_LOADING_DATA.getCacheName());
        return manager -> {
            org.infinispan.configuration.cache.Configuration ispnConfig = new ConfigurationBuilder()
                    .clustering().cacheMode(CacheMode.DIST_SYNC)
                    .build();

            manager.defineConfiguration(CLASS_LOADING_DATA.getCacheName(), ispnConfig);
        };
    }

    /**
     * Injects node data cache {@link Configuration}.
     */
    @Bean("nodeDataCacheConfigurer")
    public InfinispanCacheConfigurer nodeDataCacheConfigurer() {
        logger.info(LOGGER_TEXT_DEFINING_CACHE, NODE_DATA.getCacheName());
        return manager -> {
            org.infinispan.configuration.cache.Configuration ispnConfig = new ConfigurationBuilder()
                    .clustering().cacheMode(CacheMode.DIST_SYNC)
                    .build();

            manager.defineConfiguration(NODE_DATA.getCacheName(), ispnConfig);
        };
    }

    /**
     * Injects memory usage data cache {@link Configuration}.
     */
    @Bean
    public InfinispanCacheConfigurer memoryUsageDataCacheConfigurer() {
        logger.info(LOGGER_TEXT_DEFINING_CACHE, MEMORY_USAGE_DATA.getCacheName());
        return manager -> {
            org.infinispan.configuration.cache.Configuration ispnConfig = new ConfigurationBuilder()
                    .clustering().cacheMode(CacheMode.DIST_SYNC)
                    .build();

            manager.defineConfiguration(MEMORY_USAGE_DATA.getCacheName(), ispnConfig);
        };
    }

    /**
     * Injects operating system data cache {@link Configuration}.
     */
    @Bean
    public InfinispanCacheConfigurer operatingSystemDataCacheConfigurer() {
        logger.info(LOGGER_TEXT_DEFINING_CACHE, OPERATING_SYSTEM_DATA.getCacheName());
        return manager -> {
            org.infinispan.configuration.cache.Configuration ispnConfig = new ConfigurationBuilder()
                    .clustering().cacheMode(CacheMode.DIST_SYNC)
                    .build();

            manager.defineConfiguration(OPERATING_SYSTEM_DATA.getCacheName(), ispnConfig);
        };
    }

    /**
     * Injects platform system data cache {@link Configuration}.
     */
    @Bean
    public InfinispanCacheConfigurer platformSystemDataCacheConfigurer() {
        logger.info(LOGGER_TEXT_DEFINING_CACHE, PLATFORM_SYSTEM_DATA.getCacheName());
        return manager -> {
            org.infinispan.configuration.cache.Configuration ispnConfig = new ConfigurationBuilder()
                    .clustering().cacheMode(CacheMode.DIST_SYNC)
                    .build();

            manager.defineConfiguration(PLATFORM_SYSTEM_DATA.getCacheName(), ispnConfig);
        };
    }

    /**
     * Injects runtime data cache {@link Configuration}.
     */
    @Bean
    public InfinispanCacheConfigurer runtimeDataCacheConfigurer() {
        logger.info(LOGGER_TEXT_DEFINING_CACHE, RUNTIME_DATA.getCacheName());
        return manager -> {
            org.infinispan.configuration.cache.Configuration ispnConfig = new ConfigurationBuilder()
                    .clustering().cacheMode(CacheMode.DIST_SYNC)
                    .build();

            manager.defineConfiguration(RUNTIME_DATA.getCacheName(), ispnConfig);
        };
    }

    /**
     * Injects statistics data cache {@link Configuration}.
     */
    @Bean
    public InfinispanCacheConfigurer statisticsDataCacheConfigurer() {
        logger.info(LOGGER_TEXT_DEFINING_CACHE, STATISTICS_DATA.getCacheName());
        return manager -> {
            org.infinispan.configuration.cache.Configuration ispnConfig = new ConfigurationBuilder()
                    .clustering().cacheMode(CacheMode.DIST_SYNC)
                    .build();

            manager.defineConfiguration(STATISTICS_DATA.getCacheName(), ispnConfig);
        };
    }

    /**
     * Injects thread data cache {@link Configuration}.
     */
    @Bean
    public InfinispanCacheConfigurer threadDataCacheConfigurer() {
        logger.info(LOGGER_TEXT_DEFINING_CACHE, THREAD_DATA.getCacheName());
        return manager -> {
            org.infinispan.configuration.cache.Configuration ispnConfig = new ConfigurationBuilder()
                    .clustering().cacheMode(CacheMode.DIST_SYNC)
                    .build();

            manager.defineConfiguration(THREAD_DATA.getCacheName(), ispnConfig);
        };
    }
}
