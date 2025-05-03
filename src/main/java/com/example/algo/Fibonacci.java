package com.example.algo;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    static Map<Long, Long> cache = new HashMap<>();

    public static long fib(long num) {
        if (num <= 1)
            return num;

        if (cache.containsKey(num)) {
            return cache.get(num);
        }

        // memoization
        long result = fib(num-1) + fib(num-2);

        cache.put(num, result);
        return result;
    }


    public static void main(String[] args) {

        System.out.println("Result is : " + fib(80));

    }
}
