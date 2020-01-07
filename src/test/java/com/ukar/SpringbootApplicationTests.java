package com.ukar;

import com.ukar.redis.RedisPropertis;
import com.ukar.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest("com.ukar.*")
public class SpringbootApplicationTests {

    @Autowired
    private RedisPropertis redisPropertis;

    @Autowired
    private RedisService redisService;

    @Test
    public void contextLoads() {
        String host = redisPropertis.getHost();
        System.out.println(host);
        redisService.set("key", 123);
    }

}
