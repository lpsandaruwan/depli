package com.depli.utility.component;

/**
 * Created by Lahiru Pathirage on 8/6/2017.
 */

public interface DataRefresherComponent {
    public void refreshInstantNodeData(long nodeId);

    public void refreshNodeData(long nodeId);

    public void iterateAndRefreshInstantNodeDataMap() throws InterruptedException;

    public void iterateAndRefreshNodeDataMap() throws InterruptedException;
}
