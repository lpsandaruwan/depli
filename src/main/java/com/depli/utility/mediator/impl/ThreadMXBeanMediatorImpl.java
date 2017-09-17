package com.depli.utility.mediator.impl;

import com.depli.service.store.descriptor.ThreadDescriptorService;
import com.depli.utility.mediator.ThreadMXBeanMediator;
import java.lang.management.ThreadMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Thread MXBean mediator implementation.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Component
public class ThreadMXBeanMediatorImpl implements ThreadMXBeanMediator {

  @Autowired
  private ThreadDescriptorService threadDescriptorService;

  @Async
  @Override
  public void mediateDynamicData(Long nodeId, ThreadMXBean threadMXBean) {
    threadDescriptorService.getByNodeId(nodeId).setDynamicData(
        threadMXBean.getDaemonThreadCount(),
        threadMXBean.getPeakThreadCount(),
        threadMXBean.getThreadCount(),
        threadMXBean.getTotalStartedThreadCount(),
        threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds(), Integer.MAX_VALUE)
    );
  }
}
