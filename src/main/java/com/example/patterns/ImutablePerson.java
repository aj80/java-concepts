package com.example.patterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ImutablePerson {

    private final String name;

    private final int age;

    private final List<String> hobbies;

    ImutablePerson(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        // defensive copy
        this.hobbies = new ArrayList<>(hobbies);
    }

    ImutablePerson(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.hobbies = builder.hobbies;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getHobbies() {
        return Collections.unmodifiableList(this.hobbies);
    }

    public static class Builder {
        private String name;

        private  int age;

        private  List<String> hobbies;

        public Builder name(String value) {
            this.name = value;
            return this;
        }

        public Builder age(int value) {
            this.age = value;
            return this;
        }

        public Builder hobbies(List<String>  value) {
            this.hobbies= value;
            return this;
        }

        public ImutablePerson build() {
            return new ImutablePerson(this);
        }




    }
}
