package com.depli.utility.scheduler.task.impl;

import com.depli.service.store.connector.ConnectionTreeService;
import com.depli.store.cache.connector.ConnectionTree;
import com.depli.utility.mediator.ClassLoadingMXBeanMediator;
import com.depli.utility.mediator.MemoryMXBeanMediator;
import com.depli.utility.mediator.PlatformResourcesMXBeanMediator;
import com.depli.utility.mediator.RuntimeMXBeanMediator;
import com.depli.utility.mediator.ThreadMXBeanMediator;
import com.depli.utility.scheduler.task.DynamicDataMediatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Scheduler Factory
 *
 * Interface for the component which includes the Async methods to refresh dynamic descriptor data
 * in caches periodically.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Component
public class DynamicDataMediatorFactoryImpl implements DynamicDataMediatorFactory {

  @Autowired
  private ConnectionTreeService connectionTreeService;

  @Autowired
  private ClassLoadingMXBeanMediator classLoadingMXBeanMediator;

  @Autowired
  private MemoryMXBeanMediator memoryMXBeanMediator;

  @Autowired
  private PlatformResourcesMXBeanMediator platformResourcesMXBeanMediator;

  @Autowired
  private RuntimeMXBeanMediator runtimeMXBeanMediator;

  @Autowired
  private ThreadMXBeanMediator threadMXBeanMediator;

  @Async
  @Override
  public void mediate(Long nodeId) {
    ConnectionTree connectionTree = connectionTreeService.getByNodeId(nodeId);

    classLoadingMXBeanMediator.mediateDynamicData(nodeId, connectionTree.getClassLoadingMXBean());
    memoryMXBeanMediator.mediateDynamicData(nodeId, connectionTree.getMemoryMXBean());
    platformResourcesMXBeanMediator
        .mediateDynamicData(nodeId, connectionTree.getPlatformResourcesMXBean());
    runtimeMXBeanMediator.mediateDynamicData(nodeId, connectionTree.getRuntimeMXBean());
    threadMXBeanMediator.mediateDynamicData(nodeId, connectionTree.getThreadMXBean());
  }
}
