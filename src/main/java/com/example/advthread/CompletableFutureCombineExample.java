package com.example.advthread;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureCombineExample {

    public static void main(String[] args) {

        // receive stock price from 3 different api and do an average

        CompletableFuture<Double> api1 = CompletableFuture.supplyAsync(() -> {
            return Double.valueOf(99.5);
        } );

        CompletableFuture<Double> api2 = CompletableFuture.supplyAsync(() -> {
            return Double.valueOf(99.8);
        } );

        CompletableFuture<Double> api3 = CompletableFuture.supplyAsync(() -> {
            return Double.valueOf(100);
        } );

        CompletableFuture<Double> result = api1.thenCombineAsync(api2, (result1, result2) -> result1 + result2)
                .thenCombineAsync(api3, (addedResult, result3) -> (addedResult+ result3)/3 );



        System.out.println("result is " + result.join());



    }
}
