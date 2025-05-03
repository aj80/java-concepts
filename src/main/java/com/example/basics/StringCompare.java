package com.example.basics;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

public class StringCompare {

    public static void main(String[] args) {
        String[] words = new String[] {"apple", "banana", "Banana"};

        Arrays.sort(words);

        // [Banana, apple, banana]
        System.out.println("Result:  " + Arrays.toString(words));

        System.out.println("apple.compareTo(banana) Result:  "  + "apple".compareTo("banana"));
        System.out.println("apple.compareTo(Apple) Result:  "  + "Apple".compareTo("apple"));
        System.out.println("apple.compareTo(apple) Result:  "  + "apple".compareTo("apple"));

        //Note gives compile error
        // String words2 = new String[] {"éclair", "eagle", "Éclair", "apple"};

        String[] words22 = { "éclair", "eagle", "Éclair", "apple", "Apple" };
        Collator collator = Collator.getInstance(Locale.FRENCH);
        Arrays.sort(words22, collator);

        System.out.println("compare words22 as per rules of locale Result:  "  + Arrays.toString(words22));




    }
}
