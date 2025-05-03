package com.example.cache;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ExpiringCacheTest {

    private ExpiringCache<String, String> subject;

    @BeforeEach
    void setUp() {
        subject = new ExpiringCache();
    }

    @AfterEach
    void tearDown() {
        subject = null;
    }

    @Test
    public void testExpiringCache() throws InterruptedException {

        this.subject.add("key1", "value1");
        Thread.sleep(20000);
        String value = this.subject.get("key1");
        assertNotNull(value, "Should be not null");
        Thread.sleep(20000);
        value = this.subject.get("key1");
        assertNull(value, "Should be  null");
    }
}