package com.ukar;

import com.alibaba.fastjson.JSON;
import com.ukar.httpclient.HttpClientApi;
import com.ukar.httpclient.bean.HttpResult;
import com.ukar.util.AdressUtils;
import com.ukar.util.Md5Encrypt;
import com.ukar.util.Md5Util;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by jyou on 2017/9/13.
 * httpclient测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest("com.ukar.*")
public class HttpClientTest {

    @Autowired
    private HttpClientApi httpClient;

    Map<String, String> headers = new HashMap<>();
    Map<String, String> params = new HashMap<String, String>();

    @Before
    public void before(){
        headers.put("Client-Id", "401");
        headers.put("Language", "en");

        params.put("accountName", "jyou@mo9.com");
        params.put("password", "Ukar123456");
    }

    @Test
    public void test() throws IOException, URISyntaxException {
        Map<String,String> map = new HashMap<>();
        map.put("ip", "180.167.76.244");
        map.put("ak", "F454f8a5efe5e577997931cc01de3974");
        String url = "http://api.map.baidu.com/location/ip";
        String response = httpClient.doGet(url, map);
        System.out.println(AdressUtils.decodeUnicode(response));
    }

    @Test
    public void testPushHandle() throws Exception{
        Map<String,String> params = new HashMap<>();
        params.put("mobile", "15026676160");
        params.put("channel", "UMENG");
        params.put("channelToken", "Ao_BzEdyevrhHpLTVMc8YUwUdmp5oDQ4d0cFxNuv3sMW");
        params.put("platform", "ANDROID");
        params.put("client", "MO9");
        params.put("clientVersion", "1.0.0");
        params.put("deviceId", "123456");
        String s = JSON.toJSONString(params);
        HttpResult httpResult = httpClient.doPostJson("http://192.168.6.201/push/checkIn", s);

        System.out.println(httpResult.getData());
    }

    @Test
    public void tests() throws Exception{
        String key = "werocxofsdjnfksdf892349729lkfnnmgn/x,.zx=9=-MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJGLeWVIS3wo0U2h8lzWjiq5RJJDi14hzsbxxwedhqje123";
        long buyerId = 20148857;
        String operateName = "尤嘉";
        String amount = "0.2";
        String remark = "ukar测试客服退款";
        String sign = key + buyerId + operateName + amount + remark;
        sign = Md5Util.getMD5(sign);
        Map<String, String> params = new HashMap<String, String>();
        params.put("buyerId", String.valueOf(buyerId));
        params.put("operateName", URLEncoder.encode(operateName,"utf-8"));
        params.put("amount", amount);
        params.put("remark", URLEncoder.encode(remark,"utf-8"));
        params.put("sign", sign);
//        HttpResult httpResult = httpClient.doPost("https://www.mo9.com/gateway/flash/refund/clearCash.mhtml", params);
        HttpResult httpResult = httpClient.doPost("https://new.mo9.com/gateway/flash/refund/clearCash.mhtml", params);
        System.out.println(httpResult.getData());
    }


    @Test
    public void test1() throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("aa", "你好啊但是");
        String s = httpClient.doGet("http://localhost/bell/credit/index", params);
    }

    @Test
    public void testAdd() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("channel", "LEANCLOUD");
        params.put("method", "ADD");
        params.put("name", "you jia 创建了房间");
        params.put("clientIds", "you jia,you jia01");

        Map<String, String> map = new HashMap<String, String>();
        String data = JSON.toJSONString(params).toString();
        data = URLEncoder.encode(data, "utf-8");
        map.put("data", data);
        HttpResult httpResult = httpClient.doPost("http://localhost:8082/im/createIm", map);
        System.out.println(httpResult.getData());
    }

    @Test
    public void testSend() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("channel", "LEANCLOUD");
        params.put("method", "SEND");
        params.put("fromPeer", "you jia");
        params.put("convId", "5a7187488d6d810069a7ac21");
        params.put("message", "this is second test message");
        Map<String, String> map = new HashMap<String, String>();
        String data = JSON.toJSONString(params).toString();
        data = URLEncoder.encode(data, "utf-8");
        map.put("data", data);
        HttpResult httpResult = httpClient.doPost("http://localhost:8082/im/sendMessage", map);
        System.out.println(httpResult.getData());
    }

    @Test
    public void testGetHistory() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("channel", "LEANCLOUD");
        params.put("method", "GET");
        params.put("convid", "5a7179678d6d810069a7a446");
        Map<String, String> map = new HashMap<String, String>();
        String data = JSON.toJSONString(params).toString();
        data = URLEncoder.encode(data, "utf-8");
        map.put("data", data);
        HttpResult httpResult = httpClient.doPost("http://localhost:8082/im/getHistoryMessage", map);
        System.out.println(httpResult.getData());
    }

    @Test
    public void testRegister() throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("channel", "LEANCLOUD");
        params.put("method", "REGISTER");
        params.put("username", "you jia02");
        params.put("password", "123456");
        params.put("phone", "13213173517");

        Map<String, String> map = new HashMap<String, String>();
        String data = JSON.toJSONString(params).toString();
        data = URLEncoder.encode(data, "utf-8");
        map.put("data", data);
        HttpResult httpResult = httpClient.doPost("http://localhost:8082/im/register", map);
        System.out.println(httpResult.getData());
    }

    @Test
    public void testLogin() throws Exception {


        for(int i = 0 ; i < 1000; i ++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpResult httpResult = null;
                    try {
                        httpResult = httpClient.doPostJson("https://librakyc.alpha.mo9.com/kycApi/account/login", JSON.toJSONString(params), headers);
                        System.out.println(httpResult.getData());
                        System.out.println(httpResult.getCode());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }



    }

    @Test
    public void testPorxyDeduct() throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("bizSys", "JHJJ");
        params.put("invoice", "1519195039288152032056712774cd558");

        String sign = sign(params);
        params.put("sign", sign);
        HttpResult httpResult = httpClient.doPost("https://www.mo9.com/gateway/proxydeduct/new/query.mhtml", params);
        System.out.println(httpResult.getData());

    }

    public String sign(Map<String, String> params) {
        /** 代收（代扣）内部密钥secret key. */
        String key = "CDSW79FFMVL9Q0A13DFFJCBZVADETRUOKKJFGW5R3";

        if(null == params || params.size() < 1){
            throw new IllegalArgumentException("代扣业务参数不正确!");
        }
        // 保持与网关签名一致
        return Md5Encrypt.sign(params, key);
    }

}
