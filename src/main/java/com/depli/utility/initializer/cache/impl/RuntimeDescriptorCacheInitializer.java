package com.depli.utility.initializer.cache.impl;

import com.depli.service.store.connector.ConnectionTreeService;
import com.depli.service.store.descriptor.RuntimeDescriptorService;
import com.depli.store.cache.descriptor.RuntimeDescriptor;
import com.depli.utility.initializer.cache.CacheInitializer;
import com.depli.utility.mediator.RuntimeMXBeanMediator;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Runtime Descriptor Cache Initializer implementation
 *
 * Initializes and store RuntimeDescriptor objects in RuntimeDescriptor cache store.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Component
public class RuntimeDescriptorCacheInitializer implements CacheInitializer {

  @Autowired
  private RuntimeDescriptorService runtimeDescriptorService;

  @Autowired
  private RuntimeMXBeanMediator runtimeMXBeanMediator;

  @Autowired
  private ConnectionTreeService connectionTreeService;

  @Override
  public void initialize(Long nodeId) throws IOException {
    runtimeDescriptorService.save(nodeId, new RuntimeDescriptor());

        /* Mediate static data from RuntimeMXBean to runtime descriptor in cache */
    runtimeMXBeanMediator.mediateStaticData(
        nodeId,
        connectionTreeService.getByNodeId(nodeId).getRuntimeMXBean()
    );
  }
}
