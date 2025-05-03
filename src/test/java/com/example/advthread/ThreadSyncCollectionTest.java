package com.example.advthread;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ThreadSyncCollectionTest {
    ThreadSyncCollection subject;

    @BeforeEach
    void setUp() {
        this.subject = new ThreadSyncCollection();
    }

    @AfterEach
    void tearDown() {
        this.subject = null;
    }

    @Test
    public void testCompute() throws InterruptedException {
        // ACT
        int result = this.subject.compute(5, 1000);
        Assertions.assertEquals(5000, result, "Invalid result");

    }
}