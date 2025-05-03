package com.example.advthread.pitfall;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RaceCondition {
    private  volatile int  counter;

    // Pitfall-1: if critical section of the code accessing shared resources
    // is not syncronized, we may have a race condition and corrupted data
    // Note: volatile only provide visibility across thread not atomicity

    // Pitfall-3: There is a performance overhead managing the synchronization
    // especially in high- concurrency scenerios. In this case use AtomicInteger
    private synchronized void increment() {
        this.counter++;
    }

    public static void main(String[] args) {
        ExecutorService execService =  Executors.newFixedThreadPool(10);
        RaceCondition rc= new RaceCondition();

        for(int i= 0 ; i < 10000; i++) {
            execService.submit(() -> rc.increment());
        }

        execService.shutdown();

        // pitfall-2: below will allow to sync with other threads and main thread
        // to execute after all other threads completed execution.
        while(!execService.isTerminated()) {
            // do nothing just wait for sync
        }

        System.out.println("counter value now: " + rc.counter);


    }
}
