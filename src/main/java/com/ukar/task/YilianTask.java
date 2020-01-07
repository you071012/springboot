package com.ukar.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ukar.httpclient.HttpClientApi;
import com.ukar.util.JavaMailUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    @Scheduled(cron = "0 0 0 * * ?")
    public void task() throws IOException {
        System.out.println("------------------------查询易联余额定时任务开始执行--------------------------");
        String s = httpClientApi.doGet("https://www.mo9.com/gateway/proxypay/yilianpay/queryBalance.mhtml");
        JSONObject parse = (JSONObject) JSON.parse(s);
        BigDecimal amount = parse.getBigDecimal("amount");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);

        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int hour = instance.get(Calendar.HOUR);//获取时
        int minute = instance.get(Calendar.MINUTE);//获取分
        if (hour != 0 && hour != 23) {
            System.out.println("------------------------当前时间不是0点，不发送邮件------------------------");
            return;
        }
        if ((hour == 0 && minute > 3) || (hour == 23 && minute < 57)) {
            System.out.println("------------------------当前时间超过指定时间5min，不发送邮件------------------------");
            return;
        }
        javaMailUtil.sendEmail("易联余额", "当前查询时间为：" + format + "，易联余额为：" + String.valueOf(amount));
        System.out.println("------------------------定时任务执行成功--------------------------");
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void listenTask() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println("------------------------系统正常执行中,time=" + sdf.format(date) + "------------------------");
    }

}
