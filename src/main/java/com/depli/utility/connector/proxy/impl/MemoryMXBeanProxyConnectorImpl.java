package com.depli.utility.connector.proxy.impl;

import com.depli.utility.connector.proxy.MemoryMXBeanProxyConnector;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import javax.management.MBeanServerConnection;
import org.springframework.stereotype.Component;

/**
 * Memory MXBean proxy connector implementation
 *
 * Provides methods for proxy connection initiation for remote memory management extension bean.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

@Component
public class MemoryMXBeanProxyConnectorImpl implements MemoryMXBeanProxyConnector {

  @Override
  public MemoryMXBean getConnection(MBeanServerConnection serverConnection) throws IOException {
    return ManagementFactory.newPlatformMXBeanProxy(
        serverConnection,
        ManagementFactory.MEMORY_MXBEAN_NAME,
        MemoryMXBean.class
    );
  }
}
