package com.depli.utility.mediator;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * Thread MXBean Mediator
 * <p>
 * Converts/mediates data observed from ThreadMXBeans for storing data in
 * ThreadDescriptor objects.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

public interface ThreadMXBeanMediator {

  /**
   * Mediates consumed dynamic data from {@link ManagementFactory#getThreadMXBean} to
   * ThreadDescriptor object which represents the JMX node Id.
   *
   * @param nodeId node Id of the JMX remote connection
   * @param threadMXBean thread management bean for appropriate nodeId
   */
  public void mediateDynamicData(Long nodeId, ThreadMXBean threadMXBean);
}
