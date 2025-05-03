package com.example.advthread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DeadLock {
    private Object obj1 = new Object();
    private Object obj2 = new Object();

    public void simulateDeadLock() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<String> task1 = () -> {
            synchronized (obj1) {
                try {
                    System.out.println(String.format("Theard %s acquired lock on %s ", Thread.currentThread(), "obj1"));
                    Thread.sleep(3000);
                    synchronized (obj2) {
                        System.out.println(String.format("Theard %s acquired lock on %s ", Thread.currentThread(), "obj2"));
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return "done";
        };

        Callable<String> task2 = () -> {
            synchronized (obj2) {
                try {
                    System.out.println(String.format("Theard %s acquired lock on %s ", Thread.currentThread(), "obj2"));
                    Thread.sleep(3000);
                    synchronized (obj1) {
                        System.out.println(String.format("Theard %s acquired lock on %s ", Thread.currentThread(), "obj1"));
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return "done";
        };

        List<Callable<String>> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        try {
            List<Future<String>> futures = executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] agrs) {
        DeadLock deadLock = new DeadLock();
        deadLock.simulateDeadLock();;
    }
}


