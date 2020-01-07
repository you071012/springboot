package com.ukar.util;

import com.ukar.httpclient.HttpClientApi;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据IP地址获取详细的地域信息
 * 淘宝API : http://ip.taobao.com/service/getIpInfo.php?ip=218.192.3.42
 * 新浪API : http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=218.192.3.42
 * 百度API：http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&ip=218.192.3.42
 */
public class AdressUtils {

    private static final String url = "http://api.map.baidu.com/location/ip";
    public static String AK = "F454f8a5efe5e577997931cc01de3974";

    /**
     * @param map        需求参数ip，ak
     * @param httpClient
     * @return
     */
    public static String getAddresses(Map<String, String> map, HttpClientApi httpClient) {
        try {
            String response = httpClient.doGet(url, map);
            if (response != null) {
                response = decodeUnicode(response);
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "FAIL";
    }

    /**
     * unicode 转换成 中文
     *
     * @param theString
     * @return
     * @author fanhui 2007-3-15
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }

    /*
     *********测试时需要将  applicationContext.xml 中扫描包路径做修改，去除controller的扫描***********
     */
    public static void main(String[] args) {
        // 测试ip 219.136.134.157 中国=华南=广东省=广州市=越秀区=电信
        String ip = "219.136.134.157";
        Map<String, String> map = new HashMap<>();
        map.put("ip", ip);
        map.put("ak", AdressUtils.AK);
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext*.xml");
            HttpClientApi httpClient = (HttpClientApi) context.getBean("httpClientApi");
            String address = AdressUtils.getAddresses(map, httpClient);
            System.out.println(address);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}