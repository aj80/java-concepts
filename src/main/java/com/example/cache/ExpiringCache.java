package com.example.cache;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.*;

public class ExpiringCache<K,V> {
    // in secs
    private long expiryTime = 30 ;

    private Map<K, CacheEntry<V>> cache = new ConcurrentHashMap<>();

    public void add(K key, V value) {
        this.cache.put(key, new CacheEntry(value));
    }

    private boolean isExpired(K key) {
        boolean flag = false;
        if (this.cache.containsKey(key)) {
            CacheEntry entry = this.cache.get(key);
            flag = Duration.between(entry.getEntryTime(), Instant.now()).getSeconds() > expiryTime;
        }
        return flag;
    }

    public V get(K key) {
        V retValue = null;

        if (this.cache.containsKey(key)) {
            CacheEntry<V> entry = this.cache.get(key);
            if (this.isExpired(key)) {
                this.cache.remove(key);
            } else {
                retValue = entry.getValue();
            }
        }
        return retValue;
    }


    public void scheduleCacheCleanup() {
        ScheduledExecutorService execService = Executors.newScheduledThreadPool(2);
        execService.scheduleAtFixedRate(() -> {
            System.out.println("scheduled task: " + LocalDateTime.now());
            // this works because of ConcurrentHashMap
            for(Map.Entry<K, CacheEntry<V>> entry : cache.entrySet()) {
                if (isExpired(entry.getKey())) {
                    System.out.println("Removing Key: " + entry.getKey());
                    this.cache.remove(entry.getKey());
                }
            }

        }, 1000, 30000, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        ExpiringCache<String, String> subject = new ExpiringCache<>();
        subject.scheduleCacheCleanup();



        subject.add("key1", "value1");
        Thread.sleep(20000);
        String value = subject.get("key1");
        Thread.sleep(20000);
        value = subject.get("key1");
        subject.add("key2", "value2");
    }

}
