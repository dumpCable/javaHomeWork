package com.homewoke.cable.fouthweek.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Example9 {

    public static int value;


    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5,
                1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(100));
        CountDownLatch latch = new CountDownLatch(1);

        MyThread me = new MyThread(latch);

        executor.execute(me);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(value);
        executor.shutdown();
    }



    static class MyThread implements Runnable {



        private CountDownLatch latch;
        MyThread(CountDownLatch latch) {

            this.latch = latch;
        }

        public void run() {
            value =  CommonUtil.getIndex();
            latch.countDown();
        }

    }
}
