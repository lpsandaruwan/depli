package com.depli.utility.connector.proxy.impl;

import com.depli.utility.connector.proxy.PlatformResourcesMXBeanProxyConnector;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServerConnection;
import org.springframework.stereotype.Component;

/**
 * com.sun.management Operating system MXBean proxy connector implementation <p> Provides methods
 * for proxy connection initiation for remote com.sun.management operating system management
 * extension bean. <p> NB: Please note that there are no management beans called
 * PlatformResourcesMXBean in Java management factory, the name is for the reference purposes only.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Component
public class PlatformResourcesMXBeanProxyConnectorImpl implements
    PlatformResourcesMXBeanProxyConnector {

  @Override
  public com.sun.management.OperatingSystemMXBean getConnection(
      MBeanServerConnection serverConnection)
      throws IOException {
    return ManagementFactory.newPlatformMXBeanProxy(
        serverConnection,
        ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME,
        com.sun.management.OperatingSystemMXBean.class
    );
  }
}
