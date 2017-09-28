package com.ukar.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Administrator on 2017/9/28 0028.
 */
public class QueueDemo {
    //在自己内部定义自己的一个实例，只供内部调用
    private static final QueueDemo instance = new QueueDemo();
    private BlockingQueue<String> queue = null;
    private QueueDemo(){
        if(queue == null){
            queue = new LinkedBlockingDeque<>(5);
        }

    }
    //这里提供了一个供外部访问本class的静态方法，可以直接访问
    public static QueueDemo getInstance(){
        return instance;
    }

    public BlockingQueue<String> getQueue() {
        return queue;
    }
}
