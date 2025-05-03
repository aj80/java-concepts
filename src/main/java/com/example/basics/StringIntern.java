package com.example.basics;

public class StringIntern {

    public static void main(String[] agrs) {
        String s1= "abc";
        String s2= new String("abc");
        String s3 = s1.intern();
        String s4 = s2.intern();
        String s5 ="abc";

        if (s1 == s2) {
            System.out.println("s1 and s2 are == ");
        } else {
            System.out.println("s1 and s2 NOT ==");
        }

        if (s1 == s3) {
            System.out.println("s1 and s3 (intern) are == ");
        } else {
            System.out.println("s1 and s3 NOT ==");
        }

        if (s2 == s4) {
            System.out.println("s2 and s4 (intern) are == ");
        } else {
            System.out.println("s2 and s4 NOT ==");
        }

        if (s1 == s5) {
            System.out.println("s1 and s5  are == ");
        } else {
            System.out.println("s1 and s5 NOT ==");
        }


        /*
        s1 and s2 NOT ==
        s1 and s3 (intern) are ==
        s2 and s4 NOT ==
        s1and s5 (intern) are ==
         */

    }
}
