package com.example.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorSafeRemoval {

    public static void main(String[] args) {

        // safe removal starts
        List<Integer> nums = new ArrayList<>();
        nums.add(10);
        nums.add(20);
        Iterator<Integer> iterator = nums.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove(); // safe removal
        }

        System.out.println(nums.size());
        // safe removal ends
    }
}
