package com.example.advthread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,  () -> System.out.println("Completed all 5 Threads"));

        Runnable task = () -> {
            System.out.println("Hello world");
            try {
                System.out.println("Thread " + Thread.currentThread().getName() + "waiting for barrier");
                cyclicBarrier.await();
                System.out.println("Thread " + Thread.currentThread().getName() + "resuming ...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        };

        for (int i=0; i< 5 ; i++) {
            Thread t = new Thread (task);
            t.start();
        }


        System.out.println("Main THread");
    }
}
