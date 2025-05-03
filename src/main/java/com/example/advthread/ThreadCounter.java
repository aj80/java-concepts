package com.example.advthread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadCounter {
    static class CountHolder {
        private static AtomicInteger counter = new AtomicInteger(0);

        // public synchronized static void  increment()
        public  static void  increment() {
            counter.getAndIncrement();
        }

        public static int getCount() {
            return counter.get();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i< 1000 ; i++) {
            Callable<Integer> task = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    CountHolder.increment();
                    return CountHolder.getCount();
                }
            };

            tasks.add(task);
        }

        List<Future<Integer>> futures = executorService.invokeAll(tasks);
        futures.forEach(x -> {
            try {
                x.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.shutdown();

        System.out.println("Counter value is " + CountHolder.getCount());
    }
}
