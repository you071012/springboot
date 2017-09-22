package com.ukar;

import com.ukar.Lock.Lock;
import com.ukar.Lock.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jyou on 2017/9/22.
 *
 * redis分布式锁测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest("com.ukar.*")
public class LockTest {

    @Autowired
    private RedisLock redisLock;

    private long timeout = 60000L;

    private long tryInterval = 500L;

    private long lockExpireTime = 120000L;


    @Test
    public void test(){

        Lock lock = new Lock("123456key", "123456value");
        for(int i = 0 ; i < 2 ; i++){
            /**
             * 获取锁
             */
            boolean tryLock = redisLock.tryLock(lock, timeout, tryInterval, lockExpireTime);
            if(tryLock){//获取锁成功
                System.out.println("获取锁true");
                boolean tryRealase = redisLock.tryRealase(lock);
                if(tryRealase){
                    redisLock.realase(lock);
                }
            }else{
                System.out.println("获取锁false");
            }
        }

    }


}
