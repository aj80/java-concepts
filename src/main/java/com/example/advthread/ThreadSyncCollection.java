package com.example.advthread;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

public class ThreadSyncCollection {
    private class Counter {
        // private Map<String, Integer> container = new HashMap<>();

        private Map<String, Integer> container = new ConcurrentHashMap<>();

        public Counter() {
            container.put("count", 0);
        }

        // we can just make it syncronized
        /*
        public syncronized void add() {
            int value = container.get("count");
            container.put("count", ++value);
        }
        */

        public  void add() {
            container.compute("count", (key, value) -> Objects.isNull(value) ? 1 : ++value);
        }


        public int getCount() {
            return container.get("count");
        }
    }

    public int compute(int threadCount, int iterateCount) throws InterruptedException {
        Counter counter = new Counter();

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < iterateCount; j++) {
                    counter.add();
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(10000, TimeUnit.MILLISECONDS);
        return counter.getCount();

    }

}
