package com.ukar.thread.pool;

import com.aliyun.openservices.shade.io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * @author jia.you
 * @date 2020/01/07
 */
public class ThreadPoolTest {

    public ThreadPoolExecutor threadPoolExecutor;

    public ThreadPoolTest() {
        /**
         * corePoolSize:指定了线程池中的线程数量，它的数量决定了添加的任务是开辟新的线程去执行，还是放到workQueue任务队列中去；
         * maximumPoolSize:指定了线程池中的最大线程数量，这个参数会根据你使用的workQueue任务队列的类型，决定线程池会开辟的最大线程数量；
         * keepAliveTime:当线程池中空闲线程数量超过corePoolSize时，多余的线程会在多长时间内被销毁；
         * unit:keepAliveTime的单位
         * workQueue:任务队列，被添加到线程池中，但尚未被执行的任务；它一般分为直接提交队列、有界任务队列、无界任务队列、优先任务队列几种；
         * threadFactory:线程工厂，用于创建线程，一般用默认即可；
         * handler:拒绝策略；当任务太多来不及处理时，如何拒绝任务；
         */
        threadPoolExecutor = new ThreadPoolExecutor(5, 5, 2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }

    public static void main(String[] args) throws Exception {
        ThreadPoolTest threadPoolTest = new ThreadPoolTest();
        Future<String> future1 = threadPoolTest.threadPoolExecutor.submit(() -> threadPoolTest.get(2000L));
        Future<String> future2 = threadPoolTest.threadPoolExecutor.submit(() -> threadPoolTest.get(5000L));
        System.out.println(111);

        //future.get() 方法会阻塞
        System.out.println(future2.get());
        System.out.println(future1.get());

        threadPoolTest.threadPoolExecutor.shutdown();
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
