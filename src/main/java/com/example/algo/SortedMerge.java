package com.example.algo;

import java.util.Arrays;

public class SortedMerge {

    public static int[] sortMerge(int[] num1, int[] num2) {
        int p1 = 0;
        int p2 = 0;
        int m = num1.length;
        int n = num2.length ;

        int[] num1Copy = new int[m+n];

        for (int i = 0 ; i < m ; i++ ){
            num1Copy[i] = num1[i];
        }

        for (int i = n ; i < m+n ; i++ ){
            num1Copy[i] = 0;
        }

        int[] num = new int[m+n];

        for (int p = 0; p < m+n; p++) {
            if (p2 >= n ||  (p1 < m && num1Copy[p1] < num2[p2] )) {
                num[p] = num1Copy[p1++];
            } else {
                num[p] = num2[p2++];
            }
        }
        return num;
    }


    public static void main(String[] args) {
        // int[] result = sortMerge(new int[] {1,2,3}, new int[] {2,5,6});
        int[] result = sortMerge(new int[] {2, 4, 6}, new int[] {1, 2, 3});
        System.out.println("output " + Arrays.toString(result));
    }
}
