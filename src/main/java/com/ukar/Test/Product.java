package com.ukar.Test;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2017/9/28 0028.
 */
public class Product implements Runnable{
    @Override
    public void run() {
        System.out.printf("进入生产者.......");

        try {
            for(int i = 0 ; i < 10 ; i++){
                BlockingQueue<String> queue = QueueDemo.getInstance().getQueue();
                System.out.println("queue的长度为" + queue.size());
                queue.put("" + i);
                System.out.println("queue插入了数据" + i);
                Thread.sleep(2000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("结束生产者.......");
    }
}
