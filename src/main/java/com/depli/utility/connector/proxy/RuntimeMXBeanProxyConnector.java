package com.depli.utility.connector.proxy;

import java.io.IOException;
import java.lang.management.RuntimeMXBean;
import javax.management.MBeanServerConnection;

/**
 * Runtime MXBean proxy connector
 * <p>
 * Provides methods for proxy connection initiation for remote runtime management extension bean.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

public interface RuntimeMXBeanProxyConnector {

  /**
   * Returns runtime management extension bean for appropriate management bean server connection.
   *
   * @param serverConnection management bean server connection for appropriate remote java virtual
   * machine
   * @return runtime management extension bean
   */
  public RuntimeMXBean getConnection(MBeanServerConnection serverConnection) throws IOException;
}
