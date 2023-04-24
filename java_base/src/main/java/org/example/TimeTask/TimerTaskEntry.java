package org.example.TimeTask;

import java.util.TimerTask;

public class TimerTaskEntry implements Comparable<TimerTaskEntry>{
    volatile TimerTaskList timedTaskList;
    TimerTaskEntry next;
    TimerTaskEntry prev;
    private TimerTask timerTask;
    private long expireMs;
    @Override
    public int compareTo(TimerTaskEntry o) {
        return 0;
    }
}
