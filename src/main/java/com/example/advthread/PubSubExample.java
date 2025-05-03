package com.example.advthread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PubSubExample {
    private BlockingQueue<Integer> queue = new PriorityBlockingQueue<>(10);

    public void pubSub() throws InterruptedException {
        Thread producer = new Thread() {
            public void run() {
                for (int i = 0; i< 1000; i++) {
                    try {
                        queue.put(i);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        Thread consumer = new Thread() {
            public void run() {
                while (true) {
                    try {
                        int message = queue.take();
                        System.out.println("Message received: " + message);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };


        producer.start();
        consumer.start();

        Runtime.getRuntime().addShutdownHook(new Thread (() -> {
            consumer.interrupt();
        } ));

    }
}
