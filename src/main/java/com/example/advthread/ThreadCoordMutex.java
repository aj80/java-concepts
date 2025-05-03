package com.example.advthread;

public class ThreadCoordMutex {
    static boolean printNum = true;

    static void threadCoord() throws InterruptedException {
        int nums[] = new int[]{1, 2, 3, 4, 5};
        char chars[] = new char[]{'a', 'b', 'c', 'd', 'e'};

        Object obj = new Object();

        Thread t1 = new Thread() {
            public void run() {
                for (int num : nums) {
                    synchronized (obj) {
                        try {
                            if (!printNum) {
                                obj.wait();
                            }
                            System.out.print(num + " ");
                            printNum = false;
                            obj.notify();
                        }
                         catch (InterruptedException e) {
                             throw new RuntimeException(e);
                         }
                    }
                }

            }
        };

        Thread t2 = new Thread() {
            public void run() {
                for (char c : chars) {
                    synchronized (obj) {
                        try {
                            if (printNum) {
                                obj.wait();
                            }
                            System.out.print(c + " ");
                            printNum = true;
                            obj.notify();
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }
        };


        t1.start();
        t2.start();
        t1.join();
        t2.join();


    }

    public static void main(String[] args) throws InterruptedException {

        threadCoord();


    }
}
