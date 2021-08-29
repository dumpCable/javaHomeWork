package com.homewoke.cable.fouthweek.thread;

import java.util.concurrent.*;

public class Example8 {



    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5,
                1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(100));
        CommonValue value = new CommonValue();
        CyclicBarrier barrier = new CyclicBarrier(2);
        MyThread me = new MyThread(value, barrier);

        executor.execute(me);
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
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

        private CyclicBarrier barrier;
        MyThread(CommonValue value, CyclicBarrier barrier) {
            this.value = value;
            this.barrier = barrier;
        }

        public void run() {
            value.value =  CommonUtil.getIndex();
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }
}
