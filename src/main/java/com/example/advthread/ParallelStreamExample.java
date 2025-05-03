package com.example.advthread;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class ParallelStreamExample {

    public long sumParallel() {
        long firstNum = 1;
        long lastNum = 1000000;

        List<Long> list = LongStream.rangeClosed(firstNum, lastNum).boxed().collect(Collectors.toList());

        long result = list.parallelStream().reduce(0L, Long::sum);// method reference
        return result;
    }

    public long sumParallelUsingForkJoinPool() throws ExecutionException, InterruptedException {
        long firstNum = 1;
        long lastNum = 1000000;
        long result = 0;

        List<Long> list = LongStream.rangeClosed(firstNum, lastNum).boxed().collect(Collectors.toList());
        ForkJoinPool threadPool = new ForkJoinPool(4);
        try {
            result = threadPool.submit(() -> list.parallelStream().reduce(0L, Long::sum)).get();
        } finally {
            // if not memory leak
            threadPool.shutdown();
        }



        return result;
    }
}
