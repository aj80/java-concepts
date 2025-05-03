package com.example.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PhoneCombination {

    static List<String> combinations = new ArrayList<>();

    private static Map<Character, String> letters = Map.of(
            '2', "abc"
            , '3', "def"
    );

    private static String phoneDigits;

    private static void backTrack(int index, StringBuilder path) {
        // If the path is the same length as digits, we have a complete combination
        if (index == phoneDigits.length()) {
        // if (path.length() == phoneDigits.length()) {
            combinations.add(path.toString());
            return; // Backtrack
        }

        // Get the letters that the current digit maps to, and loop through them
        String possibleLetters = letters.get(phoneDigits.charAt(index));
        for (char letter : possibleLetters.toCharArray()) {
            // Add the letter to our current path
            path.append(letter);
            // Move on to the next digit
            backTrack(index + 1, path);
            // Backtrack by removing the letter before moving onto the next
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println("letters " + letters);
        int index = 0;
        phoneDigits = "2";

        backTrack(index, new StringBuilder());
        System.out.println("Result is : " + combinations);

    }
}
