package com.example.advthread;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PubSubExampleTest {

    private PubSubExample subject;

    @BeforeEach
    void setUp() {
        this.subject = new PubSubExample();
    }

    @Test
    public void testPubSub() throws InterruptedException {
        // ACT
        this.subject.pubSub();
    }

    @AfterEach
    void tearDown() {
        this.subject = null;
    }
}