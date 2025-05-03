package com.example.advthread;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class CompletableFutureThenApplyExample {


    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        Supplier<String> supplier1 = () ->  {
            System.out.println("In CF1" + Thread.currentThread());
            return "Hello";
        };

        System.out.println("In main Thread about to call cf1.join " + Thread.currentThread());

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(supplier1);

        CompletableFuture<String> cf2 = cf1.thenApply(result -> {
                    System.out.println("In CF1 thenApply" + Thread.currentThread());
                    return result + " World";
                });


        String value1 = cf2.join();

        System.out.println("cf2 value1 " + value1);


        //output:
        /* thenApplyAsync (always runs on that thread)
            In main Thread about to call cf1.join Thread[#1,main,5,main]
            In CF1Thread[#21,ForkJoinPool.commonPool-worker-1,5,main]
            In CF1 thenApplyThread[#21,ForkJoinPool.commonPool-worker-1,5,main]
            cf2 value1 Hello World
         */

        /* thenApply (can run on main or sometime on ForkJoinPool)
            In main Thread about to call cf1.join Thread[#1,main,5,main]
            In CF1Thread[#21,ForkJoinPool.commonPool-worker-1,5,main]
            In CF1 thenApplyThread[#1,main,5,main]
            cf2 value1 Hello World
         */
    }
}
