package com.example.algo;

public class BestTimeSellStock {

    public static void main(String[] args) {
        int prices[]  = {7,1,5,3,6,4};
        int minPrice = Integer.MAX_VALUE;
        int profit = 0;

        for (int i= 0; i< prices.length ; i++) {
            if (prices[i] < minPrice ) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > profit) {
                profit = prices[i] - minPrice;
            }
        }

        System.out.println("Max profit: " + profit);
    }
}
