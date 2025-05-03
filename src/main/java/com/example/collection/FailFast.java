package com.example.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFast {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> nums = new ArrayList<>();
        // List<Integer> nums = new CopyOnWriteArrayList<>();
        nums.add(10);
        nums.add(20);

        Thread t1 = new Thread (() -> {
            Iterator<Integer> iterator = nums.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
                nums.add(95);
            }
        });

        Thread t2 = new Thread(() -> nums.add(40));

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(nums);
        }
}
