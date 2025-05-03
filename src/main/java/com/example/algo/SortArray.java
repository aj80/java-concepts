package com.example.algo;

import java.util.Arrays;

public class SortArray {

    static int[] sort(int[] nums) {
        for (int i=0; i< nums.length; i++) {
            for (int j= i+1; j < nums.length; j++) {
                if (nums[i] > nums[j] ) {
                    int x = nums[i];
                    nums[i] = nums[j];
                    nums[j] = x;
                }
            }
        }
        return nums;
    }


    public static void main(String[] args) {
        int[] result = sort(new int[] {5,1,1,2,0,0});
        System.out.println("output " + Arrays.toString(result));
    }
}
