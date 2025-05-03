package com.example.advthread;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class CompletableFutureExample {


    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        Supplier<String> supplier1 = () ->  {
            System.out.println("In CF1" + Thread.currentThread());
            return "Hello";
        };

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(supplier1);

        System.out.println("In main Thread " + Thread.currentThread());

        Supplier<String> supplier2 = () ->  {
            System.out.println("In CF2" + Thread.currentThread());
            return "Test";
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(supplier2, executorService);

        System.out.println("In main Thread about to call cf1.join " + Thread.currentThread());
        String value1 = cf1.join();

        System.out.println("cf1 value1 " + value1);

        String value2 = cf2.join();
        System.out.println("cf2 value2 " + value2);

        executorService.shutdown();

        //output:
        /*
        In CF1Thread[#21,ForkJoinPool.commonPool-worker-1,5,main]
        In main Thread Thread[#1,main,5,main]
        In main Thread about to call cf1.join Thread[#1,main,5,main]
        In CF2Thread[#22,pool-1-thread-1,5,main]
        cf1 value1 Hello
        cf2 value2 Test
         */
    }
}
