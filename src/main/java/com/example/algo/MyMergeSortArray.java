package com.example.algo;

import java.util.Arrays;

public class MyMergeSortArray {

    private static void mergeSort(int[] nums) {
        if (nums.length < 2) {
            return;
        }

        int midIndex = nums.length/2;
        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[nums.length - midIndex];

        for (int i=0; i < midIndex; i++ ) {
            leftHalf[i] = nums[i];
        }

        for (int i= midIndex; i < nums.length; i++ ) {
            rightHalf[i - midIndex] = nums[i];
        }
        mergeSort(leftHalf);
        mergeSort(rightHalf);
        merge(nums, leftHalf, rightHalf);
    }

    private static void merge(int[] nums, int[] leftHalf, int[] rightHalf) {
        int i = 0, j = 0 , k = 0;
        while (i < leftHalf.length && j < rightHalf.length) {
            if (leftHalf[i] <= rightHalf[j]) {
                nums[k] = leftHalf[i];
                i++;
            } else {
                nums[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        while (i < leftHalf.length) {
            nums[k] = leftHalf[i];
            i++;
            k++;
        }

        while (j < rightHalf.length) {
            nums[k] = rightHalf[j];
            j++;
            k++;
        }

    }


    public static void main(String[] args) {
        int[] nums = new int[] {10, 1, 60, 30, 5};
        mergeSort(nums);
        System.out.println("output " + Arrays.toString(nums));
    }
}
