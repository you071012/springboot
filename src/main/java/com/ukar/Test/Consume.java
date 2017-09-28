package com.ukar.Test;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2017/9/28 0028.
 */
public class Consume implements Runnable{
    @Override
    public void run() {
        BlockingQueue<String> queue = QueueDemo.getInstance().getQueue();
        System.out.println("queue的长度为" + queue.size());
        try {
            while(true){
                Thread.sleep(5000);
                String take = queue.take();
                System.out.println("queue取出数据" + take);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
