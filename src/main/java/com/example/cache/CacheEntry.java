package com.example.cache;

import java.time.Instant;

public class CacheEntry<V> {
    private V value;


    private Instant entryTime;

    public CacheEntry(V v) {
        this.value = v;
        this.entryTime = Instant.now();
    }

    public V getValue() {
        return value;
    }

    public Instant getEntryTime() {
        return entryTime;
    }
}
