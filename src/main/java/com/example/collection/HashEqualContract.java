package com.example.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashEqualContract {

    public static class Person {
        private String name;

        private int age;

        public Person (String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(name, person.name);
        }
        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    public static void main(String[] args) {
        Person person1 = new Person("xyz", 10);

        Person person2 = new Person("xyz", 10);

        Person person3 = new Person("xyz", 10);

        Map<Person, String> map = new HashMap<>();
        map.put(person1, "Good");
        map.put(person2, "Better");
        map.put(person3, "Avg");

        // make change to person2
        person2.setName("state-change");

        System.out.println("Value of person1 > " + map.get(person1));
        System.out.println("Value of person2 > " + map.get(person2));
        System.out.println("Value of person3 > " + map.get(person3));
        System.out.println("Value of person2 again > " + map.get(person2));

    }
}
