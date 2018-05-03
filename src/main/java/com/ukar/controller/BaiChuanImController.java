package com.ukar.controller;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.OpenImUser;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.*;
import com.taobao.api.response.*;
import com.ukar.util.DateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jyou on 2018/1/30.
 */
@RestController
@RequestMapping("/baichuan")
public class BaiChuanImController {

    String url = "http://gw.api.taobao.com/router/rest";
    String appkey = "24789120";//
    String secret = "9a33ef3697c52ae2da34a09160967823";

    @RequestMapping("/index")
    public String index() throws ApiException {
        return "欢迎";
    }


    @RequestMapping("/add")
    public String add(@RequestParam("userId") String userId) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersAddRequest req = new OpenimUsersAddRequest();
        req.putHeaderParam("format", "xml");
        List<Userinfos> list2 = new ArrayList<Userinfos>();
        Userinfos obj3 = new Userinfos();
        list2.add(obj3);
        obj3.setNick(userId);
        obj3.setIconUrl("http://xxx.com/xxx");
        obj3.setEmail("uid@taobao.com");
        obj3.setMobile("13213173517");
        obj3.setTaobaoid(userId);
        obj3.setUserid(userId);
        obj3.setPassword("123456");
        obj3.setRemark(userId);
        obj3.setExtra("{}");
        obj3.setCareer(userId);
        obj3.setVip("{}");
        obj3.setAddress(userId);
        obj3.setName(userId);
        obj3.setAge(123L);
        obj3.setGender("M");
        obj3.setWechat(userId);
        obj3.setQq(userId);
        obj3.setWeibo(userId);
        req.setUserinfos(list2);
        OpenimUsersAddResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());

        return rsp.getBody();
    }

    @RequestMapping("/get")
    public String get() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersGetRequest req = new OpenimUsersGetRequest();
        req.setUserids("ukar01");
        OpenimUsersGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
        return rsp.getBody();
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("userId") String userId) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersDeleteRequest req = new OpenimUsersDeleteRequest();
        req.setUserids(userId);
        OpenimUsersDeleteResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
        return rsp.getBody();
    }

    @RequestMapping("/push")
    public String push() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimCustmsgPushRequest req = new OpenimCustmsgPushRequest();
        OpenimCustmsgPushRequest.CustMsg obj1 = new OpenimCustmsgPushRequest.CustMsg();
        obj1.setFromUser("ukar01");
        List<String> list = new ArrayList<>();
        list.add("ukar02");
        list.add("ukar03");
        obj1.setToUsers(list);
        obj1.setSummary("客户端最近消息里面显示的消息摘要");
        obj1.setData("ukar01发送的测试消息");
        obj1.setAps("{\"alert\":\"ios apns push\"}");
        obj1.setApnsParam("apns推送的附带数据");
        obj1.setInvisible(0L);
        obj1.setFromNick("sender_nick");
        obj1.setFromTaobao(0L);
        req.setCustmsg(obj1);
        OpenimCustmsgPushResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());

        return rsp.getBody();
    }


    @RequestMapping("/pushGet")
    public String pushGet() throws Exception {
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", appkey, secret);
        OpenimChatlogsGetRequest req = new OpenimChatlogsGetRequest();
        OpenImUser obj1 = new OpenImUser();
        obj1.setUid("ukar01");
        obj1.setTaobaoAccount(false);
        obj1.setAppKey("demo");
        req.setUser1(obj1);
        OpenImUser obj2 = new OpenImUser();
        obj2.setUid("ukar02");
        obj2.setTaobaoAccount(false);
        obj2.setAppKey("ukar2");
        req.setUser2(obj2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = sdf.parse("2018-01-30 10:00:00");
        Date now = new Date();
        req.setBegin(Long.valueOf(DateUtils.getUTCTimeStr(start)));
        req.setEnd(Long.valueOf(DateUtils.getUTCTimeStr(now)));
        req.setCount(100L);
        OpenimChatlogsGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());

        return rsp.getBody();
    }



}
