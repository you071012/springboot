package com.ukar;

import com.mo9.auto_config.pojo.DictPojo;
import com.mo9.auto_config.pojo.GroupPojo;
import com.mo9.auto_config.service.DictService;
import com.ukar.entity.User;
import com.ukar.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * Created by jyou on 2017/10/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest("com.ukar.*,com.mo9.*")
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Resource
    private DictService dictService;

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

    @Test
    public void testR(){
        GroupPojo groupPojo = new GroupPojo();
        groupPojo.setGroup("kyc");
        dictService.group(groupPojo);

        GroupPojo groupPojo2 = new GroupPojo();
        groupPojo2.setGroup("glutton");
        dictService.group(groupPojo2);

        DictPojo dictPojo = new DictPojo();
        dictPojo.setKey("risk.url");
        dictPojo.setValue("http://risk.mo9.com");
        dictPojo.setGroup("kyc");
        dictService.addDict(dictPojo);

        dictPojo.setKey("kyc.url");
        dictPojo.setValue("http://kyc.mo9.com");
        dictPojo.setGroup("kyc");
        dictService.addDict(dictPojo);

        DictPojo dictPojo2 = new DictPojo();
        dictPojo2.setKey("glutton.url");
        dictPojo2.setValue("http://glutton.mo9.com");
        dictPojo2.setGroup("glutton");
        dictService.addDict(dictPojo2);
    }
}
