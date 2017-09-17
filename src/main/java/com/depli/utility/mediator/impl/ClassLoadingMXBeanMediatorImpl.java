package com.depli.utility.mediator.impl;

import com.depli.service.store.descriptor.ClassLoadingDescriptorService;
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

  @Async
  @Override
  public void mediateDynamicData(Long nodeId, ClassLoadingMXBean classLoadingMXBean) {
    classLoadingDescriptorService.getByNodeId(nodeId).setDynamicData(
        classLoadingMXBean.getLoadedClassCount(),
        classLoadingMXBean.getTotalLoadedClassCount(),
        classLoadingMXBean.getUnloadedClassCount()
    );
  }
}
