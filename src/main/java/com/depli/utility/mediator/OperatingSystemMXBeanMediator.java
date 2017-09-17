package com.depli.utility.mediator;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 * Operating System MXBean Mediator
 * <p>
 * Converts/mediates data observed from OperatingSystemMXBeans for storing data in
 * OperatingSystemDescriptor objects.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

public interface OperatingSystemMXBeanMediator {

  /**
   * Mediates consumed static data from {@link ManagementFactory#getOperatingSystemMXBean} to
   * OperatingSystemDescriptor object which represents the JMX node Id.
   *
   * @param nodeId node ID of the JMX remote connection
   * @param operatingSystemMXBean operating system management bean for appropriate nodeId
   */
  public void mediateStaticData(Long nodeId, OperatingSystemMXBean operatingSystemMXBean);
}
