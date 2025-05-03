package com.example.algo;

public class Palindrome {

    static boolean isPalin2(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c: word.toCharArray()) {
            sb.append(c);
        }

        String reversed = sb.reverse().toString();
        return word.equals(reversed);
    }

    static boolean isPalin(String word) {
        int left = 0;
        int right = word.length()-1;
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String word = "level";
        System.out.println("Is Palin: "  + isPalin(word));

        System.out.println("Is Palin2: "  + isPalin2(word));
    }
}
