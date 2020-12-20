package org.springaop.chapter.four.schema.introduction;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterTrackerImpl implements CounterTracker {

    public CounterTrackerImpl() {
        count = new AtomicInteger(0);
    }

    public int getCounter() {
        return count.get();
    }

    public void increaseCounter() {
        count.incrementAndGet();
    }

    private AtomicInteger count;
}
