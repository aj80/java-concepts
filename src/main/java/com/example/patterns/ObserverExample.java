package com.example.patterns;

import java.util.ArrayList;
import java.util.List;

public class ObserverExample {

    interface Observer {
        void accept(int data);
    }

    interface Subject {
        void add (Observer observer);
        void remove (Observer observer);

        void notifyData(int data);
    }

    static class ObserverImpl implements Observer {
        private String name;

        ObserverImpl(String nam) {
            this.name = nam;
        }

        @Override
        public void accept(int data) {
            System.out.println(String.format(" %s : Received: %s ", this.name, data) );
        }
    }

    static class ObserverData implements Subject {
        List<Observer> observers = new ArrayList<>();
        List<Integer> data = new ArrayList<>();

        @Override
        public void add(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void remove(Observer observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyData(int msg) {
            data.add(msg);
            for (Observer observer : observers) {
                observer.accept(msg);
            }
        }
    }

    public static void main(String[] args) {
        ObserverData obsData = new ObserverData();
        obsData.add(new ObserverImpl("abc"));
        obsData.add(new ObserverImpl("xyz"));
        obsData.add(new ObserverImpl("foo"));

        for (int i = 0; i < 10; i++) {
            obsData.notifyData(i);
        }
    }
}
