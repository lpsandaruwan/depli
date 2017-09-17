package com.depli.utility.connector.proxy.impl;

import com.depli.utility.connector.proxy.ThreadMXBeanProxyConnector;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import javax.management.MBeanServerConnection;
import org.springframework.stereotype.Component;

/**
 * Thread MXBean proxy connector implementation
 * <p>
 * Provides methods for proxy connection initiation for remote thread management extension bean.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Component
public class ThreadMXBeanProxyConnectorImpl implements ThreadMXBeanProxyConnector {

  @Override
  public ThreadMXBean getConnection(MBeanServerConnection serverConnection) throws IOException {
    return ManagementFactory.newPlatformMXBeanProxy(
        serverConnection,
        ManagementFactory.THREAD_MXBEAN_NAME,
        ThreadMXBean.class
    );
  }
}
