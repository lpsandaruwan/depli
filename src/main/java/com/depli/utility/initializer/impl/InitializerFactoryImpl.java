package com.depli.utility.initializer.impl;

import com.depli.service.store.cache.connector.ConnectionTreeCacheService;
import com.depli.service.store.cache.descriptor.ClassLoadingDescriptorCacheService;
import com.depli.service.store.cache.descriptor.MemoryDescriptorCacheService;
import com.depli.service.store.cache.descriptor.OperatingSystemDescriptorCacheService;
import com.depli.service.store.cache.descriptor.PlatformResourcesDescriptorCacheService;
import com.depli.service.store.cache.descriptor.RuntimeDescriptorCacheService;
import com.depli.service.store.cache.descriptor.ThreadDescriptorCacheService;
import com.depli.service.store.cache.graph.ClassLoadingGraphDataCacheService;
import com.depli.service.store.cache.graph.ProcessingUnitGraphDataCacheService;
import com.depli.store.persistent.entity.JMXNode;
import com.depli.store.persistent.repository.JMXNodeRepository;
import com.depli.utility.initializer.InitializerFactory;
import com.depli.utility.initializer.cache.impl.ClassLoadingDescriptorCacheInitializer;
import com.depli.utility.initializer.cache.impl.ClassLoadingGraphDataCacheInitializer;
import com.depli.utility.initializer.cache.impl.ConnectionTreeInitializer;
import com.depli.utility.initializer.cache.impl.MemoryDescriptorCacheInitializer;
import com.depli.utility.initializer.cache.impl.OperatingSystemDescriptorCacheInitializer;
import com.depli.utility.initializer.cache.impl.PlatformResourcesDescriptorCacheInitializer;
import com.depli.utility.initializer.cache.impl.ProcessingUnitGraphDataCacheInitializer;
import com.depli.utility.initializer.cache.impl.RuntimeDescriptorCacheInitializer;
import com.depli.utility.initializer.cache.impl.ThreadDescriptorCacheInitializer;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Initializer Factory implementation
 *
 * Initializes caches and data stores.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Component
public class InitializerFactoryImpl implements InitializerFactory {

  @Autowired
  private ConnectionTreeCacheService connectionTreeCacheService;

  @Autowired
  private ClassLoadingDescriptorCacheService classLoadingDescriptorCacheService;

  @Autowired
  private MemoryDescriptorCacheService memoryDescriptorCacheService;

  @Autowired
  private OperatingSystemDescriptorCacheService operatingSystemDescriptorCacheService;

  @Autowired
  private PlatformResourcesDescriptorCacheService platformResourcesDescriptorCacheService;

  @Autowired
  private RuntimeDescriptorCacheService runtimeDescriptorCacheService;

  @Autowired
  private ThreadDescriptorCacheService threadDescriptorCacheService;

  @Autowired
  private ClassLoadingGraphDataCacheService classLoadingGraphDataCacheService;

  @Autowired
  private ProcessingUnitGraphDataCacheService processingUnitGraphDataCacheService;

  @Autowired
  private ClassLoadingDescriptorCacheInitializer classLoadingDescriptorCacheInitializer;

  @Autowired
  private ConnectionTreeInitializer connectionTreeInitializer;

  @Autowired
  private MemoryDescriptorCacheInitializer memoryDescriptorCacheInitializer;

  @Autowired
  private OperatingSystemDescriptorCacheInitializer operatingSystemDescriptorCacheInitializer;

  @Autowired
  private PlatformResourcesDescriptorCacheInitializer platformResourcesDescriptorCacheInitializer;

  @Autowired
  private RuntimeDescriptorCacheInitializer runtimeDescriptorCacheInitializer;

  @Autowired
  private ThreadDescriptorCacheInitializer threadDescriptorCacheInitializer;

  @Autowired
  private ClassLoadingGraphDataCacheInitializer classLoadingGraphDataCacheInitializer;

  @Autowired
  private ProcessingUnitGraphDataCacheInitializer processingUnitGraphDataCacheInitializer;

  @Autowired
  private JMXNodeRepository jmxNodeRepository;

  @Override
  public void initialize() throws IOException {
    /* Clear cache store before initializing if exists */
    connectionTreeCacheService.clearCache();
    classLoadingDescriptorCacheService.clearCache();
    memoryDescriptorCacheService.clearCache();
    operatingSystemDescriptorCacheService.clearCache();
    platformResourcesDescriptorCacheService.clearCache();
    runtimeDescriptorCacheService.clearCache();
    threadDescriptorCacheService.clearCache();
    classLoadingGraphDataCacheService.clearCache();
    processingUnitGraphDataCacheService.clearCache();

    /* Iterate JMXNode entities and initialize cache stores */
    for (JMXNode jmxNode : jmxNodeRepository.findAll()) {
      Long nodeId = jmxNode.getNodeId();

      connectionTreeInitializer.initialize(jmxNode);
      classLoadingDescriptorCacheInitializer.initialize(nodeId);
      memoryDescriptorCacheInitializer.initialize(nodeId);
      operatingSystemDescriptorCacheInitializer.initialize(nodeId);
      platformResourcesDescriptorCacheInitializer.initialize(nodeId);
      runtimeDescriptorCacheInitializer.initialize(nodeId);
      threadDescriptorCacheInitializer.initialize(nodeId);
      classLoadingGraphDataCacheInitializer.initialize(nodeId);
      processingUnitGraphDataCacheInitializer.initialize(nodeId);
    }
  }
}
