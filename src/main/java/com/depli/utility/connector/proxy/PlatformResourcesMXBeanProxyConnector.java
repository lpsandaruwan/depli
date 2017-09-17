package com.depli.utility.connector.proxy;

import com.sun.management.OperatingSystemMXBean;
import java.io.IOException;
import javax.management.MBeanServerConnection;

/**
 * com.sun.management Operating system MXBean proxy connector <p> Provides methods for proxy
 * connection initiation for remote com.sun.management operating system management extension bean.
 * <p> NB: Please note that there are no management beans called PlatformResourcesMXBean in Java
 * management factory, the name is for the reference purposes only.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

public interface PlatformResourcesMXBeanProxyConnector {

  /**
   * Returns opearating system management extension bean for appropriate management bean server
   * connection.
   *
   * @param serverConnection management bean server connection for appropriate remote java virtual
   * machine
   * @return com.sun.management operating system management extension bean
   */
  public OperatingSystemMXBean getConnection(MBeanServerConnection serverConnection)
      throws IOException;
}
