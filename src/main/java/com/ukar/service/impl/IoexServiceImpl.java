package com.ukar.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ukar.entity.*;
import com.ukar.httpclient.HttpClientApi;
import com.ukar.mapper.UserMapper;
import com.ukar.service.IoexService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jyou on 2018/5/5.
 */
@Service
public class IoexServiceImpl implements IoexService{

    @Resource
    private HttpClientApi httpClientApi;

    @Resource
    private UserMapper userMapper;



    @Override
    public List<EthListData> getEthList() throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("access-token", "8b67c595c3094a42aa32336cf791429d");
        headers.put("account-code", "424307002903101440");
        headers.put("client-id", "503");
        headers.put("country", "CN");
        headers.put("language", "zh");
        headers.put("timestamp", System.currentTimeMillis() + "");
        String response = httpClientApi.doGetByHeader("https://www.ioex.com/dandelionApi/api/dandelion/v1/ad/get_list?type=SELL&digitalCurrencyId=2&currentPage=1&pageSize=10", headers);
        List<EthListData> list = new ArrayList<>();
        if(response != null){
            JSONObject jsonObject = (JSONObject) JSON.parse(response);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray arr = data.getJSONArray("entities");
            for(int i = 0 ; i < arr.size() ; i++){
                JSONObject json = (JSONObject) arr.get(i);
                JSONArray paymentModeArr = (JSONArray) json.get("paymentMode");
                JSONObject paymentMode = new JSONObject();
                for(int j = 0 ; j < paymentModeArr.size() ; j++){
                    JSONObject jsonObject1 = (JSONObject) paymentModeArr.get(j);
                    Integer paymentModeId = jsonObject1.getInteger("paymentModeId");
                    if(paymentModeId == 3){
                        paymentMode = jsonObject1;
                    }
                }
                JSONObject userInfo = (JSONObject) json.get("userInfo");
                JSONObject advertisement = (JSONObject) json.get("advertisement");
                PaymentMode paymentMode1 = JSON.toJavaObject(paymentMode, PaymentMode.class);
                UserInfo userInfo1 = JSON.toJavaObject(userInfo, UserInfo.class);
                Advertisement advertisement1 = JSON.toJavaObject(advertisement, Advertisement.class);

                EthListData ethData = new EthListData();
                ethData.setAdvertisement(advertisement1);
                ethData.setPaymentMode(paymentMode1);
                ethData.setUserInfo(userInfo1);
                list.add(ethData);
            }
            return list;

        }
        return null;
    }

    @Override
    public void add(List<EthListData> list) throws IOException {
        if(list == null || list.size() == 0){
            return;
        }
        for(int i = 0 ; i < list.size(); i++){
            String ticket = httpClientApi.doGet("https://www.ioex.com/dandelionApi/api/dandelion/v1/order/ticket");

        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateUser() {
        User user = new User();
        user.setName("test000000004");
        user.setPassword("456789");
        userMapper.insertSelective(user);
        throw new RuntimeException("出现异常");
    }
}
