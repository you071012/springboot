package com.ukar.thread.pool;

import org.apache.commons.pool.PoolableObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by jyou on 2018/4/9.
 */
@Component
public class MyPoolableObjectFactory implements PoolableObjectFactory {
    private Logger logger = LoggerFactory.getLogger(MyPoolableObjectFactory.class);

    private SimpleThread simpleThread;

    public void setSimpleThread(SimpleThread simpleThread) {
        this.simpleThread = simpleThread;
    }

    @Override
    public Object makeObject() throws Exception {
        this.simpleThread.start();
        logger.info("创建线程:" + simpleThread.getName());
        return simpleThread;
    }

    @Override
    public void destroyObject(Object o) throws Exception {
        if (o instanceof SimpleThread) {
            SimpleThread simpleThread = (SimpleThread) o;
            logger.info("销毁线程:" + simpleThread.getName());
            simpleThread.destroy();
        }
    }

    @Override
    public boolean validateObject(Object o) {
        return false;
    }

    @Override
    public void activateObject(Object o) throws Exception {

    }

    @Override
    public void passivateObject(Object o) throws Exception {

    }
}
