package com.ukar;

import com.ukar.Lock.RedisLock;
import com.ukar.entity.EthListData;
import com.ukar.service.IoexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by jyou on 2017/9/22.
 *
 * redis分布式锁测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest("com.ukar.*")
public class IoexTest {

    @Resource
    private IoexService ioexService;

    @Test
    public void testGetEthList() throws IOException {
        List<EthListData> ethList = ioexService.getEthList();
        System.out.println(ethList.size());
    }


}
