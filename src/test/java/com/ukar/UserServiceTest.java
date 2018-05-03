package com.ukar;

import com.github.pagehelper.PageInfo;
import com.ukar.entity.User;
import com.ukar.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jyou on 2017/10/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest("com.ukar.*")
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void test(){
        User user = userService.selectOne(21L);
        System.out.println(user.toString());
//        PageInfo<User> page = userService.selectList();
//        List<User> list = page.getList();
//        long total = page.getTotal();
//        System.out.println(list.size());
//        System.out.println(total);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setPassword("123456");
        user.setName("张三");
        user.setCreateTime(LocalDateTime.now());
        userService.insert(user);

    }
}
