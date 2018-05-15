package com.ukar;

import com.ukar.entity.User;
import com.ukar.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

/**
 * Created by jyou on 2017/9/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest("com.ukar.*")
public class RedisTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void test(){
        User user = new User();
        user.setName("张三");
        user.setPassword("123");
        boolean key3 = redisService.set("key3", user);
        System.out.println(key3);
//        Object key2 = redisService.get("key2");
//        System.out.println(key2);

//        redisService.hSet("hset", "key01", "123");
//        redisService.hSet("hset", "key02", "456");
        Object o = redisService.hGet("hset", "key01");
        System.out.println(o);
        Set<Object> hset = redisService.hGetKeys("hset");
        System.out.println(hset);
    }

}
