package com.depli.utility.mediator;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

/**
 * Class Loading MXBean Mediator
 * <p>
 * Converts/mediates data observed from ClassLoadingMXBean for storing data on
 * ClassLoadingDescriptor objects.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */

public interface ClassLoadingMXBeanMediator {

    /**
     * Mediates consumed loadedClassCount, totalLoadedClassCount, unloadedClassCount data from
     * {@link ManagementFactory#getClassLoadingMXBean} to ClassLoadingDescriptor object which
     * represents the JMX node Id.
     *
     * @param nodeId             node ID of the JMX remote connection
     * @param classLoadingMXBean class loading management bean for appropriate nodeId
     */
    public void mediateDynamicData(Long nodeId, ClassLoadingMXBean classLoadingMXBean);

}
