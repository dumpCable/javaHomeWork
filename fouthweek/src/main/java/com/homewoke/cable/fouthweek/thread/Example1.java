package com.homewoke.cable.fouthweek.thread;

public class Example1 {
    public static void main(String[] args) {
        MyThread me = new MyThread();
        Thread thread = new Thread(me);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(me.getValue());
    }

    static class MyThread implements Runnable {
        private int value;

        public void run() {
            value =  CommonUtil.getIndex();
        }

        public int getValue(){
            return value;
        }
    }
}
