package com.depli.utility.initializer;

import java.io.IOException;

/**
 * Initializer Factory
 *
 * Initializes caches and data stores.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

public interface InitializerFactory {

    /**
     * Initialize cache stores. If cache stores are already initialized, they will be cleared.
     */
    public void initialize() throws IOException;
}
