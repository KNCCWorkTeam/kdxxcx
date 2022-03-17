package com.kdxxcx.util.Time;

public class Timer {
    long startTime;
    long endTime;

    public Timer(long startTime) {
        this.startTime = startTime;
    }

    public Timer() {
        init();
    }
    void init() {
        this.startTime = System.currentTimeMillis();
    }

    public long getTime() {
        return System.currentTimeMillis()-startTime;
    }

    public long stop() {
        this.endTime = System.currentTimeMillis();
        return getTime();
    }

    public void clean() {
        startTime = 0;
        endTime = 0;
    }

    public void rerun() {
        clean();
        init();
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}
