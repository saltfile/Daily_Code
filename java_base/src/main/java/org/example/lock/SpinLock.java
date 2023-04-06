package org.example.lock;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    public AtomicReference<Thread> cas = new AtomicReference<>();


    public void lock() {
        Thread current = Thread.currentThread();
        // 利用CAS
        while (!cas.compareAndSet(null, current)) {
            // DO nothing
        }
    }
    public void unlock() {
        Thread current = Thread.currentThread();
        cas.compareAndSet(current, null);
    }






}
