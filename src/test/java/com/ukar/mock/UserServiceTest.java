package com.ukar.mock;

import com.ukar.SpringbootApplication;
import com.ukar.entity.User;
import com.ukar.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author jia.you
 * @date 2018/12/19
 */
@SpringBootTest(classes = SpringbootApplication.class, webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void findOne() {
        User user = userService.selectOne(1);
        System.out.println(user);
    }

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        User user = userService.selectOne(1);
//        user.setPassword("456");
//        userService.update(user);
//        User user1 = userService.selectOne(1);
//        user.setPassword("789");
//        userService.update(user1);
//        User user2 = new User();
//        user2.setName("ukar02");
//        user2.setPassword("123");
//        userService.selectOne(1);
//        userService.selectOne(1);
//        userService.selectOne(1);
//        userService.selectOne(1);
//        userService.selectOne(1);
//        userService.selectOne(1);
        long end = System.currentTimeMillis();
        System.out.println("时间：" + (end - start));
    }


}
