package com.example.lock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizedLockTest implements Runnable {
    public synchronized void get() {
        log.info("2 enter thread name-->" + Thread.currentThread().getName());
        log.info("3 get thread name-->" + Thread.currentThread().getName());
        set();
        log.info("5 leave run thread name-->" + Thread.currentThread().getName());
    }

    public synchronized void set() {
        log.info("4 set thread name-->" + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        log.info("1 run thread name-->" + Thread.currentThread().getName());
        get();
    }

    public static void main(String[] args) {
        SynchronizedLockTest test = new SynchronizedLockTest();
        for (int i = 0; i < 10; i++) {
            new Thread(test, "thread-" + i).start();
        }
    }
}
