package com.depli.utility.connector.proxy;

import java.io.IOException;
import java.lang.management.OperatingSystemMXBean;
import javax.management.MBeanServerConnection;

/**
 * Operating system MXBean proxy connector
 *
 * Provides methods for proxy connection initiation for remote operating system management extension
 * bean.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

public interface OperatingSystemMXBeanProxyConnector {

  /**
   * Returns operating system management extension bean for appropriate management bean server
   * connection.
   *
   * @param serverConnection management bean server connection for appropriate remote java virtual
   * machine
   * @return operating system extension bean
   */
  public OperatingSystemMXBean getConnection(MBeanServerConnection serverConnection)
      throws IOException;
}
