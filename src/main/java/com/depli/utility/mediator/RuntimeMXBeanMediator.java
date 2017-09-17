package com.depli.utility.mediator;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * Runtime MXBean Mediator
 * <p>
 * Converts/mediates data observed from RuntimeMXBeans for storing data in
 * RuntimeDescriptor objects.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

public interface RuntimeMXBeanMediator {

  /**
   * Mediates consumed dynamic data from {@link ManagementFactory#getRuntimeMXBean} to
   * RuntimeDescriptor object which represents the JMX node Id.
   *
   * @param nodeId node Id of the JMX remote connection
   * @param runtimeMXBean runtime management bean for appropriate nodeId
   */
  public void mediateDynamicData(Long nodeId, RuntimeMXBean runtimeMXBean);

  /**
   * Mediates consumed static data from {@link ManagementFactory#getRuntimeMXBean} to
   * RuntimeDescriptor object which represents the JMX node Id.
   *
   * @param nodeId node Id of the JMX remote connection
   * @param runtimeMXBean runtime management bean for appropriate nodeId
   */
  public void mediateStaticData(Long nodeId, RuntimeMXBean runtimeMXBean);
}
