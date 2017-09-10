package com.depli.store.cache.descriptor;

import java.lang.management.MemoryUsage;

/**
 * DMemoryUsage
 * <p>
 * Depli's implementation of MemoryUsage class to keep store in MB.
 * <p>
 *
 * @author Lahiru Pathirage
 * @since 3/28/17
 */

public class DMemoryUsage {

    private float init;
    private float used;
    private float committed;
    private float max;

    // default constructor
    public DMemoryUsage() {
    }

    public void setData(MemoryUsage memoryUsage) {
        if (init == -1) {
            this.init = -1;
        } else {
            this.init = Math.round((memoryUsage.getInit() / (1024f * 1024f)) * 10f) / 10f;
        }

        if (used == -1) {
            this.used = -1;
        } else {
            this.used = Math.round((memoryUsage.getUsed() / (1024f * 1024f)) * 10f) / 10f;
        }

        if (committed == -1) {
            this.committed = -1;
        } else {
            this.committed = Math.round((memoryUsage.getCommitted() / (1024f * 1024f)) * 10f) / 10f;
        }

        if (max == -1) {
            this.max = -1;
        } else {
            this.max = Math.round((memoryUsage.getMax() / (1024f * 1024f)) * 10f) / 10f;
        }
    }

    public float getCommitted() {
        return committed;
    }

    public void setCommitted(float committed) {
        this.committed = committed;
    }

    public float getInit() {
        return init;
    }

    public void setInit(float init) {
        this.init = init;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getUsed() {
        return used;
    }

    public void setUsed(float used) {
        this.used = used;
    }
}
