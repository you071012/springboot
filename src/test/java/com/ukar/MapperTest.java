package com.ukar;

import com.ukar.entity.User;
import com.ukar.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jyou on 2017/9/13.
 * httpclient测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest("com.ukar.*")
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        User user = userMapper.selectByName("ukar");
        System.out.println(user.getIdCard());
    }
}
