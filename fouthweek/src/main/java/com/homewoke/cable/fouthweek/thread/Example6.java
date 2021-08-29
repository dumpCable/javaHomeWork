package com.homewoke.cable.fouthweek.thread;

import java.util.concurrent.Semaphore;

public class Example6 {



    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);
        CommonValue value = new CommonValue();
        MyThread me = new MyThread(value, sem);
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(me);
        thread.start();
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(value.getValue());
    }

    static class CommonValue{
        private int value;


        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }


    }

    static class MyThread implements Runnable {

        private CommonValue value;
        private Semaphore sem;

        MyThread(CommonValue value, Semaphore sem) {
            this.value = value;
            this.sem = sem;
        }

        public void run() {
            value.value =  CommonUtil.getIndex();
            sem.release();
        }

    }
}
