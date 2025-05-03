package com.example.advthread;

import java.util.stream.IntStream;

public class ParallelStream {

    public static void main(String[] args) {
        int result = IntStream.rangeClosed(1, 4)
                .parallel().sum();
        System.out.println("result: " + result);
    }
}
