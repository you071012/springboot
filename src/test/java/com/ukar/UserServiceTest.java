package com.ukar;

import com.ukar.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author jia.you
 * @date 2019/12/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest("com.ukar.*")
public class UserServiceTest {
    @Resource
    private UserService userService;


    @Test
    public void testLoad(){
        userService.load(null, null);
    }
}
