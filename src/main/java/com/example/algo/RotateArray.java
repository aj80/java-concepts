package com.example.algo;

import java.util.Arrays;

public class RotateArray {
    public static void rotate(int[] nums, int k) {
        int[] newNums = new int[nums.length];

        for (int i = 0 ; i < nums.length; i++) {
            int x = (i+k)%nums.length; // for example (0 + 3)/7 > 4
            newNums[(i+k)%nums.length] = nums[i];
        }

        for (int i = 0 ; i <  nums.length; i++) {
            nums[i] = newNums[i];
        }

    }

    public static void main(String[] args) {

        int[] input = new int[] {1,2,3,4,5,6,7};

        rotate(input, 3);

        // [5, 6, 7, 1, 2, 3, 4]
        System.out.println("Result "+ Arrays.toString(input));
    }
}
