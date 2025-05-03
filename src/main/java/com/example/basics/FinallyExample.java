package com.example.basics;

public class FinallyExample {

    public static int test(int i) {
        try {
            if (i == 0)
                throw new Exception();
            return 0;
        } catch (Exception e) {
            return 1;
        } finally {
            return 2;
        }
    }

    public static void main(String[] args) {
        int result = test(0);
        System.out.println("result :" + result);
    }
}
