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



}
