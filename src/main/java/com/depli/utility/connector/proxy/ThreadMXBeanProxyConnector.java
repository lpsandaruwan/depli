package com.depli.utility.connector.proxy;

import java.io.IOException;
import java.lang.management.ThreadMXBean;
import javax.management.MBeanServerConnection;

/**
 * Thread MXBean proxy connector
 * <p>
 * Provides methods for proxy connection initiation for remote thread management extension bean.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

public interface ThreadMXBeanProxyConnector {

  /**
   * Returns thread management extension bean for appropriate management bean server connection.
   *
   * @param serverConnection management bean server connection for appropriate remote java virtual
   * machine
   * @return thread management extension bean
   */
  public ThreadMXBean getConnection(MBeanServerConnection serverConnection) throws IOException;
}
