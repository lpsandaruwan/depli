package com.depli.utility.mediator.impl;

import com.depli.service.store.descriptor.ClassLoadingDescriptorService;
import com.depli.service.store.graph.ClassLoadingGraphDataService;
import com.depli.utility.mediator.ClassLoadingMXBeanMediator;
import java.lang.management.ClassLoadingMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Class loading MXBean mediator implementation.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Component
public class ClassLoadingMXBeanMediatorImpl implements ClassLoadingMXBeanMediator {

  @Autowired
  private ClassLoadingDescriptorService classLoadingDescriptorService;

  @Autowired
  private ClassLoadingGraphDataService classLoadingGraphDataService;

  @Async
  @Override
  public void mediateDynamicData(Long nodeId, ClassLoadingMXBean classLoadingMXBean) {
    int loadedClassCount = classLoadingMXBean.getLoadedClassCount();
    classLoadingDescriptorService.getByNodeId(nodeId).setDynamicData(
        loadedClassCount,
        classLoadingMXBean.getTotalLoadedClassCount(),
        classLoadingMXBean.getUnloadedClassCount()
    );

    classLoadingGraphDataService.getByNodeId(nodeId).setDynamicData(loadedClassCount);
  }
}
