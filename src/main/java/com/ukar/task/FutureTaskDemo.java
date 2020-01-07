package com.ukar.task;

import java.util.concurrent.*;

/**
 * @author jia.you
 * @date 2019/01/17
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws Exception {
        FutureTaskDemo futureTaskDemo = new FutureTaskDemo();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        FutureTask task1 = new FutureTask(() -> futureTaskDemo.get(2000L));
        FutureTask task2 = new FutureTask(() -> futureTaskDemo.get(5000L));
        threadPoolExecutor.execute(task1);
        threadPoolExecutor.execute(task2);

        System.out.println("do something ......");
        System.out.println(task1.get());
        System.out.println(task2.get());
        threadPoolExecutor.shutdown();

    }

    public String get(long sleepTime){
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(sleepTime);
    }
}
