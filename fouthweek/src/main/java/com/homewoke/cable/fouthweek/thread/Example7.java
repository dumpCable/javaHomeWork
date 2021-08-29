package com.homewoke.cable.fouthweek.thread;

import java.util.concurrent.*;

public class Example7 {



    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5,
                1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(100));
        CountDownLatch latch = new CountDownLatch(1);
        CommonValue value = new CommonValue();
        MyThread me = new MyThread(value, latch);

        executor.execute(me);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(value.getValue());
        executor.shutdown();
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

        private CountDownLatch latch;
        MyThread(CommonValue value, CountDownLatch latch) {
            this.value = value;
            this.latch = latch;
        }

        public void run() {
            value.value =  CommonUtil.getIndex();
            latch.countDown();
        }

    }
}
