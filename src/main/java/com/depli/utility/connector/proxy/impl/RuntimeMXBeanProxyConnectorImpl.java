package com.depli.utility.connector.proxy.impl;

import com.depli.utility.connector.proxy.RuntimeMXBeanProxyConnector;
import org.springframework.stereotype.Component;

import javax.management.MBeanServerConnection;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * Runtime MXBean proxy connector implementation
 * <p>
 * Provides methods for proxy connection initiation for remote runtime management extension bean.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Component
public class RuntimeMXBeanProxyConnectorImpl implements RuntimeMXBeanProxyConnector {

    @Override
    public RuntimeMXBean getConnection(MBeanServerConnection serverConnection) throws IOException {
        return ManagementFactory.newPlatformMXBeanProxy(
                serverConnection,
                ManagementFactory.RUNTIME_MXBEAN_NAME,
                RuntimeMXBean.class
        );
    }
}
