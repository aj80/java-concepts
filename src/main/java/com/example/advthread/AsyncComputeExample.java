package com.example.advthread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncComputeExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String>  completableFuture = CompletableFuture.supplyAsync(() ->
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "Hello";
                }
                );

        CompletableFuture<String>  future = completableFuture.thenApply(s -> s + "World");

        System.out.println("future output " + future.get());



        System.out.println("Progam completed");

    }

}
