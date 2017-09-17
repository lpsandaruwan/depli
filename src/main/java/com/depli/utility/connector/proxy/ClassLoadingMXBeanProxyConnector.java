package com.depli.utility.connector.proxy;

import javax.management.MBeanServerConnection;
import java.io.IOException;
import java.lang.management.ClassLoadingMXBean;

/**
 * Class loading MXBean proxy connector
 *
 * Provides methods for proxy connection initiation for remote class loading management extension bean.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

public interface ClassLoadingMXBeanProxyConnector {

    /**
     * Returns class loading management extension bean for appropriate management bean server connection.
     *
     * @param serverConnection management bean server connection for appropriate remote java virtual machine
     * @return class loading management extension bean
     */
    public ClassLoadingMXBean getConnection(MBeanServerConnection serverConnection) throws IOException;
}
