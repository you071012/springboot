package com.ukar.util;

import com.taobao.api.internal.tmc.Message;
import com.taobao.api.internal.tmc.MessageHandler;
import com.taobao.api.internal.tmc.MessageStatus;
import com.taobao.api.internal.tmc.TmcClient;
import com.taobao.api.internal.toplink.LinkException;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by jyou on 2018/1/30.
 */
public class DateUtils {
    /**
     * 得到UTC时间，类型为字符串，格式为"yyyy-MM-dd HH:mm"<br />
     * 如果获取失败，返回null
     * @return
     */
    public static String getUTCTimeStr(Date date) {
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance() ;
        cal.setTime(date);
        // 2、取得时间偏移量：
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        Date time = cal.getTime();
        long time1 = time.getTime();
        String str = time1 + "";
        str = str.substring(0, str.length() - 3);
        return str;
    }

    public static void main(String[] args) {
        TmcClient client=new TmcClient("24788867","2ad2ec6d3f54b48a515f1cf53e9e4882","default");
        client.setMessageHandler(new MessageHandler(){
            public void onMessage(Message message, MessageStatus status){
                try{
                    System.out.println(message.getContent());
                    System.out.println(message.getTopic());
                    //默认不抛出异常则认为消息处理成功
                }catch(Exception e){
                    e.printStackTrace();
                    status.fail();//消息处理失败回滚，服务端需要重发
                }
            }
        });
        try {
            client.connect();
        } catch (LinkException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(5000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
