package com.homewoke.cable.fouthweek.thread;

import java.util.concurrent.*;

public class Example3 {
    public static void main(String[] args) {

        FutureTask<Integer> task = new FutureTask<Integer>(new MyThread() );
        Thread me = new Thread(task);
        me.start();
        try {
            int result = task.get(1000, TimeUnit.MILLISECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    static class MyThread implements Callable<Integer> {


        public Integer call() throws Exception {
            return CommonUtil.getIndex();
        }
    }
}
