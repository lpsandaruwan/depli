package com.depli.data;

import com.depli.remote.*;
import com.depli.data.object.*;

/**NodeData
 * Combined model of all the data and stats of a node.
 *
 * Created by lpsandaruwan on 3/24/17.
 */

public class NodeData {

    private final DJMXConnection djmxConnection;
    private boolean isInitialized;

    private final DClassLoadingMXBean dClassLoadingMXBean;
    private final DMemoryMXBean dMemoryMXBean;
    private final DOperatingSystemMXBean dOperatingSystemMXBean;
    private final DRuntimeMXBean dRuntimeMXBean;
    private final DThreadMXBean dThreadMXBean;

    public NodeData(DJMXConnection djmxConnection) {
        this.djmxConnection = djmxConnection;
        this.isInitialized = false;

        this.dClassLoadingMXBean = new DClassLoadingMXBean(djmxConnection);
        this.dMemoryMXBean = new DMemoryMXBean(djmxConnection);
        this.dOperatingSystemMXBean = new DOperatingSystemMXBean(djmxConnection);
        this.dRuntimeMXBean = new DRuntimeMXBean(djmxConnection);
        this.dThreadMXBean = new DThreadMXBean(djmxConnection);
    }

    public DJMXConnection getDjmxConnection() {
        return djmxConnection;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public DClassLoadingMXBean getdClassLoadingMXBean() {
        return dClassLoadingMXBean;
    }

    public DMemoryMXBean getdMemoryMXBean() {
        return dMemoryMXBean;
    }

    public DOperatingSystemMXBean getdOperatingSystemMXBean() {
        return dOperatingSystemMXBean;
    }

    public DRuntimeMXBean getdRuntimeMXBean() {
        return dRuntimeMXBean;
    }

    public DThreadMXBean getdThreadMXBean() {
        return dThreadMXBean;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }
}
