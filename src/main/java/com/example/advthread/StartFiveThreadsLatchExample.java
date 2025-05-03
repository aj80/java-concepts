package com.example.advthread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class StartFiveThreadsLatchExample {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        for(int i = 0; i< 5 ; i++) {
            Thread t1 = new Thread() {
                public void run() {
                    System.out.println("Hello World");
                    latch.countDown();
                }
            };
            t1.start();
        }

        latch.await();
        System.out.println("Exiting main thread using countdown latch");

        /* Another latch */
        CountDownLatch latch2 = new CountDownLatch(5);
        Thread t1 = new Thread (() -> {
            for(int i = 0; i < 5 ; i++) {
                System.out.println("Hello World 2");
                latch2.countDown();
            }
        });

        t1.start();



        latch2.await();
        System.out.println("Exiting main thread using countdown latch-2");


    }
}
