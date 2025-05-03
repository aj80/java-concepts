package com.example.advthread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCoord {
    static Lock lock = new ReentrantLock();
    static  boolean numberTurn = true;
    static Condition numberCondition = lock.newCondition();
    static Condition CharCondition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {

        Thread t11 = new Thread() {
            public void run() {
                int[] nums = new int [] {1,2,3,4,5};

                for (int i = 0; i < nums.length; i++) {
                    try {
                        lock.lock();
                        // while(!numberTurn) {
                        if (!numberTurn) {
                                numberCondition.await();
                        }

                        System.out.print( nums[i] + ";");
                        numberTurn = false;
                        CharCondition.signal();

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };

        Thread t12 = new Thread() {
            public void run() {
                char[] chars = new char [] {'a', 'b', 'c', 'd', 'e'};

                for (int i = 0; i < chars.length; i++) {
                    try {
                        lock.lock();

                        // while(numberTurn) {
                        if (numberTurn) {
                                CharCondition.await();
                        }
                        System.out.print( chars[i] + ";");
                        numberTurn = true;
                        numberCondition.signal();

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };

        t11.start();
        t12.start();
        t11.join();
        t12.join();


    }
}
