package com.test.config.test.threadpool;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

public class BasicTest {

    public static void main(String[] args){
        // TODO
        System.out.println("hello world!");
        // 1. thread 类


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 100, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>(3),new BasicThreadFactory.Builder(). );


    }

    public static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("1");
        }
    }

}
