package com.ukar.thread.pool;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.springframework.stereotype.Component;

/**
 * Created by jyou on 2018/4/9.
 */
@Component
public class SimpleThread extends Thread {
    private boolean isRunning;
    private GenericObjectPool pool;

    public boolean getIsRunning() {
        return isRunning;
    }

    public synchronized void setIsRunning(boolean flag) {
        isRunning = flag;
        if (flag) {
            this.notify();
        }
    }

    public void setPool(GenericObjectPool pool) {
        this.pool = pool;
    }

    public SimpleThread() {
        isRunning = false;
    }

    public void destroy() {
        this.interrupt();
    }

    public synchronized void run() {
        try {
            while (true) {
                if (!isRunning) {
                    this.wait();
                } else {
                    System.out.println(this.getName() + "开始处理");
                    sleep(5000);
                    System.out.println(this.getName() + "结束处理");
                    setIsRunning(false);
                    try {
                        pool.returnObject(this);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("我被Interrupted了，所以此线程将被关闭");
            return;
        }
    }
}
