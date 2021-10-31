package com.example.lock;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantLockTest implements Runnable {

    private ReentrantLock reentrantLock = new ReentrantLock(true); //是否设置为公平锁

    public void get() {
        log.info("2 enter thread name-->" + Thread.currentThread().getName());
        reentrantLock.lock();
        log.info("3 get thread name-->" + Thread.currentThread().getName());
        set();
        reentrantLock.unlock();
        log.info("5 leave run thread name-->" + Thread.currentThread().getName());
    }

    public void set() {
        reentrantLock.lock();
        log.info("4 set thread name-->" + Thread.currentThread().getName());
        reentrantLock.unlock();
    }

    @Override
    public void run() {
        log.info("1 run thread name-->" + Thread.currentThread().getName());
        get();
    }

    public static void main(String[] args) {
        ReentrantLockTest test = new ReentrantLockTest();
        for (int i = 0; i < 10; i++) {
            new Thread(test, "thread-" + i).start();
        }
    }
}
