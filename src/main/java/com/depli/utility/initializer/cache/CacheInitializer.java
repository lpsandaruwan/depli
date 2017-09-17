package com.depli.utility.initializer.cache;

import java.io.IOException;

/**
 * Cache Initializer
 *
 * Initializes and stores objects in appropriate cache store.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

public interface CacheInitializer {

    /**
     * Initializes elements and store them in appropriate cache store for given JMXNode entity Id.
     *
     * @param nodeId nodeId of JMXNode entity
     */
    public void initialize(Long nodeId) throws IOException;
}
