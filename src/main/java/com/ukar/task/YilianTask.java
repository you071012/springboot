package com.ukar.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ukar.httpclient.HttpClientApi;
import com.ukar.util.JavaMailUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jyou on 2018/5/15.
 */
@Component
public class YilianTask {

    @Resource
    private JavaMailUtil javaMailUtil;

    @Resource
    private HttpClientApi httpClientApi;

    @Scheduled(cron="0 0 0 * * ?")
    public void task() throws IOException {
        System.out.println("------------------------查询易联余额定时任务开始执行--------------------------");
        String s = httpClientApi.doGet("https://www.mo9.com/gateway/proxypay/yilianpay/queryBalance.mhtml");
        JSONObject parse = (JSONObject) JSON.parse(s);
        BigDecimal amount = parse.getBigDecimal("amount");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM:dd HH:mm:ss");
        String format = sdf.format(date);
        javaMailUtil.sendEmail("易联余额", "当前查询时间为：" + format + "，易联余额为：" + String.valueOf(amount));
    }

    @Scheduled(cron="0 0 12 * * ?")
    public void task2() throws IOException {
        javaMailUtil.sendEmail("测试邮件", "这是一个测试邮件");
    }

}