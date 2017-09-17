package com.depli.utility.connector.proxy.impl;

import com.depli.utility.connector.proxy.ClassLoadingMXBeanProxyConnector;
import org.springframework.stereotype.Component;

import javax.management.MBeanServerConnection;
import java.io.IOException;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

/**
 * Class loading MXBean proxy connector implementation
 * <p>
 * Provides methods for proxy connection initiation for remote class loading management extension bean.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

@Component
public class ClassLoadingMXBeanProxyConnectorImpl implements ClassLoadingMXBeanProxyConnector {

    @Override
    public ClassLoadingMXBean getConnection(MBeanServerConnection serverConnection) throws IOException {
        return ManagementFactory.newPlatformMXBeanProxy(
                serverConnection,
                ManagementFactory.CLASS_LOADING_MXBEAN_NAME,
                ClassLoadingMXBean.class
        );
    }
}
