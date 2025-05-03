package com.example.advthread;

public class VolatileExample {

    static volatile boolean ready;
    static  int number;

    static class Reader extends Thread {

        public void run() {
            System.out.println("value of ready " + ready);
            // if ready is not volatile true set in main thread won't be visible (not guranteed)
            while (!ready) {
                Thread.yield();
            }
            System.out.println("Value of number" + number);
        }
    }

    public static void main(String[] args) {
        new Reader().start();
        number = 20;
        ready = true;


    }

}
