package com.ukar.Test;

import com.alibaba.fastjson.JSON;
import com.ukar.httpclient.HttpClientApi;
import com.ukar.util.PostRequest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jyou on 2018/1/18.
 */
public class VoiceTest {


    public static void main(String[] args) throws Exception {
//        System.out.println(Integer.toBinaryString(-5));
        String[] strings = readFile("C:\\Users\\jyou\\Desktop\\springboot\\aa.txt");
        for(int i = 0; i < strings.length; i++){
            String[] split = strings[i].split(",");
            sendSms(split);
        }

    }

    public static void sendSms(String[] datas) throws IOException {
        Map<String, Object> tempParams = new HashMap<String, Object>();
        String name = datas[0];
        String[] split = name.split("\uFEFF");
        if(split != null && split.length == 2){
            name = split[1];
        }
        tempParams.put("user_name", name);
        tempParams.put("payment_time", datas[3].trim());
        tempParams.put("payment_amount", datas[2].trim());
        // todo 每次都需要修改
//        tempParams.put("today", "2018-08-07");
        Map<String,String> params = new HashMap<String,String>();

        params.put("mobile", datas[1].trim());
        params.put("template_name", "batch_deal_dunning_all");
        params.put("template_data", JSON.toJSONString(tempParams).toString());
        params.put("template_tags", "CN");

        params.put("snc_version", "2.0");
        params.put("biz_sys","JHJJ");
        params.put("product_name", "JHJJ");
        params.put("biz_type", "dunning");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * 固定参数信息
         */
        params.put("client_id", "S_soaDefClient");
        params.put("valid_type", "secret");
        params.put("valid_token", "kdjdi95@d7");
        String response = PostRequest.postRequest("https://www.mo9.com/snc/sms/sendSms", params);
        System.out.println(response);
    }

    public static void voiceSms(String mobile) throws IOException {
        Map<String,String> params = new HashMap<String,String>();
        //发送手机号
        params.put("mobile", mobile);
        //语音短信固定值
        params.put("style", "voiceContent");
        params.put("template_name","weixinVip" );
        //模板tag
        params.put("template_tags", "CN");
        //snc版本 固定值:"2.0";
        params.put("snc_version", "2.0");
        //业务名称 例："JHJJ","FXYL","XWHF","MIS";
        params.put("biz_sys","JHJJ");
        //发送类型 例："1","2","3","4";   对应说明:验证码，营销，催收,系统
        params.put("biz_type", "marketing");

        /**
         * 固定参数信息
         */
        params.put("client_id", "S_soaDefClient");
        params.put("valid_type", "secret");
        params.put("valid_token", "kdjdi95@d7");

        String response = PostRequest.postRequest("https://www.mo9.com/snc/sms/sendSms", params);
        System.out.println(response);
    }

    public static String[] readFile(String fileName) throws Exception{
        File file = new File(fileName);
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        while((line = br.readLine())!=null){
            buffer.append(line);
            buffer.append("&");
        }
        br.close();
        String str = buffer.toString();
        str = str.substring(0 , str.length() - 1);
        String[] split = str.split("&");
        return split;
    }

}
