package com.homewoke.cable.fouthweek.thread;

public class Example2 {
    public static void main(String[] args) {
        MyThread me = new MyThread();
        me.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(me.getValue());
    }

    static class MyThread extends Thread {
        private int value;

        @Override
        public void run() {
            value =  CommonUtil.getIndex();
        }

        public int getValue(){
            return value;
        }
    }
}
