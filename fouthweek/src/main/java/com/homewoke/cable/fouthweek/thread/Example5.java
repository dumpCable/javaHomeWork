package com.homewoke.cable.fouthweek.thread;

public class Example5 {



    public static void main(String[] args) {
        CommonValue value = new CommonValue();
        MyThread me = new MyThread(value);
        Thread thread = new Thread(me);
        thread.start();
        while(!value.breakFlag){

        }


        System.out.println(value.getValue());
    }

    static class CommonValue{
        private int value;

        private boolean breakFlag;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public boolean isBreakFlag() {
            return breakFlag;
        }

        public void setBreakFlag(boolean breakFlag) {
            this.breakFlag = breakFlag;
        }
    }

    static class MyThread implements Runnable {

        private CommonValue value;

        MyThread(CommonValue value) {
            this.value = value;
        }

        public void run() {
            value.value =  CommonUtil.getIndex();
            value.breakFlag = true;
        }

    }
}
