package com.example.function;

import java.util.function.Function;

public class Functional {

    public static void main(String[] args) {
        Function<String, String> func = (s) -> s.toUpperCase();

        System.out.println("Run function " + func.apply("abdul"));
    }
}
