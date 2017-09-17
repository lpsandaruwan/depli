package com.depli.utility.connector.proxy.impl;

import com.depli.utility.connector.proxy.OperatingSystemMXBeanProxyConnector;
import org.springframework.stereotype.Component;

import javax.management.MBeanServerConnection;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 * Operating system MXBean proxy connector implementation
 * <p>
 * Provides methods for proxy connection initiation for remote operating system management extension bean.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Component
public class OperatingSystemMXBeanProxyConnectorImpl implements OperatingSystemMXBeanProxyConnector {

    @Override
    public OperatingSystemMXBean getConnection(MBeanServerConnection serverConnection) throws IOException {
        return ManagementFactory.newPlatformMXBeanProxy(
                serverConnection,
                ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME,
                OperatingSystemMXBean.class
        );
    }
}
