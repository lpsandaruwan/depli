package com.depli.utility.mediator;

/**
 * Platform Resources MXBean Mediator
 *
 * Converts/mediates data observed from com.sun.management.OperatingSystemMXBean for storing data in
 * OperatingSystemDescriptor objects.
 *
 * NB: Please note that there are no management beans called PlatformResourcesMXBean in Java management factory, the
 * name is for the reference purposes only.
 *
 * @author lpsandaruwan
 * @since 9/16/17
 */


public interface PlatformResourcesMXBeanMediator {

    /**
     * Mediates consumed dynamic data from com.sun.management.OperatingSystemMXBean to PlatformResourcesDescriptor
     * object which represents the JMX node Id.
     *
     * @param nodeId node Id of the JMX remote connection
     * @param platformResourcesMXBean com.sun.management.OperatingSystemMXBean management bean for appropriate nodeId
     */
    public void mediateDynamicData(Long nodeId, com.sun.management.OperatingSystemMXBean platformResourcesMXBean);

    /**
     * Mediates consumed static data from com.sun.management.OperatingSystemMXBean to PlatformResourcesDescriptor
     * object which represents the JMX node Id.
     *
     * @param nodeId node Id of the JMX remote connection
     * @param platformResourcesMXBean com.sun.management.OperatingSystemMXBean management bean for appropriate nodeId
     */
    public void mediateStaticData(Long nodeId, com.sun.management.OperatingSystemMXBean platformResourcesMXBean);
}
