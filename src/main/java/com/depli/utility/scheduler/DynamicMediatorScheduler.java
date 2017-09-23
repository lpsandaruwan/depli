package com.depli.utility.scheduler;

import com.depli.service.store.persistent.JMXNodeService;
import com.depli.store.persistent.entity.JMXNode;
import com.depli.utility.scheduler.task.DynamicDataMediatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Dynamic Mediator Scheduler Factory
 *
 * Interface for the Async methods to refresh dynamic descriptor data in caches periodically.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Component
public class DynamicMediatorScheduler {

  @Autowired
  private JMXNodeService jmxNodeService;

  @Autowired
  private DynamicDataMediatorFactory dynamicDataMediatorFactory;

  @Scheduled(cron = "*/1 * * * * *")
  public void run() {
    for (JMXNode jmxNode : jmxNodeService.findAll()) {
      dynamicDataMediatorFactory.mediate(jmxNode.getNodeId());
    }
  }
}
