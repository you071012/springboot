package com.ukar;

import com.ukar.httpclient.HttpClientApi;
import com.ukar.util.AdressUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jyou on 2017/9/13.
 * httpclient测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest("com.ukar.*")
public class HttpClientTest {

    @Autowired
    private HttpClientApi httpClient;

    @Test
    public void test() throws IOException, URISyntaxException {
        Map<String,String> map = new HashMap<>();
        map.put("ip", "180.167.76.244");
        map.put("ak", "F454f8a5efe5e577997931cc01de3974");
        String url = "http://api.map.baidu.com/location/ip";
        String response = httpClient.doGet(url, map);
        System.out.println(AdressUtils.decodeUnicode(response));
    }

}
