package com.example.advthread;

import java.util.LinkedList;
import java.util.Queue;

public class CustomThreadPool {
    private Queue<Runnable> taskQueue = new LinkedList<>();
    private volatile boolean isShutdown;

    public CustomThreadPool(int threadSize) {
        createThreads(threadSize);
    }

    public void submit (Runnable runnable) {
        synchronized (taskQueue) {
            taskQueue.offer(runnable);
            taskQueue.notifyAll();;
        }
    }

    public void shutdown() {
        synchronized (taskQueue) {
            isShutdown = true;
            taskQueue.notifyAll();
        }
    }

    private void createThreads(int threadSize) {

        for (int i = 0; i < threadSize; i++) {
            Thread t1 = new Thread (() -> {
                while (true) {
                    synchronized (taskQueue) {
                        if (isShutdown) {
                            break;
                        }

                        if (taskQueue.isEmpty()) {
                            try {
                                taskQueue.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            };
                        }

                        Runnable task = taskQueue.poll();
                        if (task != null) {
                            task.run();
                        }

                    } // sync
                } // while
            });

            t1.start();
        }
    }

    public static void main(String[] args) {
        CustomThreadPool customThread = new CustomThreadPool(4);
        customThread.submit(() -> {
            System.out.println("Running task 1" + Thread.currentThread().getName());
        });

        customThread.submit(() -> {
            System.out.println("Running task 2" + Thread.currentThread().getName());
        });

        customThread.shutdown();

    }
}
