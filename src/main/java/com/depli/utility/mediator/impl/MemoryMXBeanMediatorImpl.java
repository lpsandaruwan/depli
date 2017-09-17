package com.depli.utility.mediator.impl;

import com.depli.service.store.descriptor.MemoryDescriptorService;
import com.depli.utility.mediator.MemoryMXBeanMediator;
import java.lang.management.MemoryMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Memory usage MXBean mediator implementation.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Component
public class MemoryMXBeanMediatorImpl implements MemoryMXBeanMediator {

  @Autowired
  private MemoryDescriptorService memoryDescriptorService;

  @Async
  @Override
  public void mediateDynamicData(Long nodeId, MemoryMXBean memoryMXBean) {
    memoryDescriptorService.getByNodeId(nodeId).setDynamicData(
        memoryMXBean.getHeapMemoryUsage(),
        memoryMXBean.getNonHeapMemoryUsage(),
        memoryMXBean.getObjectPendingFinalizationCount()
    );
  }
}
