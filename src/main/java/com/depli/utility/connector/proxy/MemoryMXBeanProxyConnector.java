package com.depli.utility.connector.proxy;

import javax.management.MBeanServerConnection;
import java.io.IOException;
import java.lang.management.MemoryMXBean;

/**
 * Memory MXBean proxy connector implementation
 *
 * Provides methods for proxy connection initiation for remote memory management extension bean.
 *
 * @author lpsandaruwan
 * @since 9/17/17
 */

public interface MemoryMXBeanProxyConnector {

    /**
     * Returns memory management extension bean for appropriate management bean server connection.
     *
     * @param serverConnection management bean server connection of appropriate java virtual machine
     * @return memory management extension bean
     */
    public MemoryMXBean getConnection(MBeanServerConnection serverConnection) throws IOException;
}
