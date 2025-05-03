package com.example.advthread;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;

public class ParallelStreamExampleTest {

    private ParallelStreamExample subject;

    @BeforeEach
    void setUp() {
        this.subject = new ParallelStreamExample();
    }

    @Test
    void testParallelSteam() throws ExecutionException, InterruptedException {
        Instant start = Instant.now();
        long result = this.subject.sumParallel();
        Instant end = Instant.now();
        System.out.println("Result is " + result + "Time taken" + Duration.between(start, end));
        start = Instant.now();
        long result2 = this.subject.sumParallelUsingForkJoinPool();
        end = Instant.now();
        System.out.println("Result2 is " + result2 + "Time taken" + Duration.between(start, end));

    }

    @AfterEach
    void tearDown() {
    }
}