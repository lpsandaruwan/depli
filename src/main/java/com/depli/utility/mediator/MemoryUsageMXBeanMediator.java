package com.depli.utility.mediator;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

/**
 * Memory MXBean Mediator
 *
 * Converts/mediates data observed from MemoryUsageMXBeans for storing data in
 * MemoryUsageDescriptor objects.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

public interface MemoryUsageMXBeanMediator {

    /**
     * Mediates consumed dynamic data from {@link ManagementFactory#getMemoryMXBean} to
     * MemoryUsageDescriptor object which represents the JMX node Id.
     *
     * @param nodeId node Id of the JMX remote connection
     * @param memoryMXBean memory management bean for appropriate nodeId
     */
    public void mediateDynamicData(Long nodeId, MemoryMXBean memoryMXBean);
}
