package com.ukar.push.umeng;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ukar.push.umeng.android.AndroidFilecast;
import com.ukar.push.umeng.bean.AndroidNotification;
import com.ukar.push.umeng.bean.PushClient;
import com.ukar.push.umeng.ios.IOSFilecast;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by jyou on 2017/9/28.
 *
 * 友盟推送实现
 */
public class UMeng{
    private static final Logger logger = LoggerFactory.getLogger(UMeng.class);

    private static final String androidAppkey = "521d8a7256240bcde5062c5e";
    private static final String androidAppMasterSecret = "msuzieyqq8daxthdlt4b2vxd6hdcsc85";

    private static final String iosAppkey = "525f3bdf56240b4bc90d74cf";
    private static final String iosAppMasterSecret = "vah1ozlhqtjfcn1nilmhkissgbyk1ust";

    private static final String IOS_TYPE = "IOS";
    private static final String ANDROID_TYPE = "ANDROID";

    /**
     * 安卓文件推送
     * @param fileToken
     * @throws Exception
     */
    public String sendAndroidFilecast(String fileToken, String title) throws Exception {
        PushClient client = new PushClient();
        AndroidFilecast filecast = new AndroidFilecast(androidAppkey,androidAppMasterSecret);
        String fileId = client.uploadContents(androidAppkey,androidAppMasterSecret,fileToken);
        filecast.setFileId( fileId);
        filecast.setTicker( title);
        filecast.setTitle(  title);
        filecast.setText(   "查看详情");
        filecast.goAppAfterOpen();
        filecast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        filecast.setProductionMode();
        filecast.setExtraField("mo9_title", title);
        filecast.setExtraField("mo9_url", "https://h5.mo9.com");
        filecast.setExtraField("msgType", "URL");
        String send = client.send(filecast);
        logger.info("友盟ANDROID文件播返回结果-->" + send);
        return send;
    }


    /**
     * IOS文件播推送
     * @param fileToken 需要推送的token，多个token已换行符分隔
     * @throws Exception
     */
    public String sendIOSFilecast(String fileToken, String title) throws Exception {
        PushClient client = new PushClient();
        IOSFilecast filecast = new IOSFilecast(iosAppkey,iosAppMasterSecret);
        String fileId = client.uploadContents(iosAppkey,iosAppMasterSecret,fileToken);
        filecast.setFileId(fileId);
        filecast.setAlert(title);
        filecast.setProductionMode();
        filecast.setCustomizedField("mo9_title", title);
        filecast.setCustomizedField("mo9_url", "https://h5.mo9.com");
        filecast.setCustomizedField("msgType", "URL");
        String send = client.send(filecast);
        logger.info("友盟IOS文件播返回结果-->" + send);
        return send;
    }


    /**
     * 查询推送结果
     * @param task_id 任务id
     * @param app_key
     * @param appMasterSecret
     * @throws Exception
     */
    public String searchStatus(String task_id,String app_key, String appMasterSecret) throws Exception {
        String timestamp = Integer.toString((int)(System.currentTimeMillis() / 1000));
        org.json.JSONObject json = new org.json.JSONObject();
        json.put("task_id", task_id);
        json.put("appkey", app_key);
        json.put("timestamp", timestamp);
        String url = "http://msg.umeng.com/api/status";
        String sign = DigestUtils.md5Hex(("POST" + url + json.toString() + appMasterSecret).getBytes("utf8"));
        url = url + "?sign=" + sign;
        HttpPost post = new HttpPost(url);
        post.setHeader("User-Agent", "Mozilla/5.0");
        StringEntity se = new StringEntity(json.toString(), "UTF-8");
        post.setEntity(se);
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(post);
        int status = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + status);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());
        return result.toString();
    }


    public String readFile(String fileName) throws Exception{
        File file = new File(fileName);
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        while((line = br.readLine())!=null){
            buffer.append(line);
            buffer.append("\n");
        }
        br.close();
        String str = buffer.toString();
        str = str.substring(0 , str.length() - 1);
        return str;
    }

    public String search(String fileName) throws Exception{
        File file = new File(fileName);
        UMeng umeng = new UMeng();
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader(file));

        String name = "C:\\Users\\jyou\\Desktop\\umeng\\result.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(name),true));

        String line = null;
        while((line = br.readLine())!=null){
            buffer.append(line);
            String s = buffer.toString();
            String result = s.split("--")[1];
            JSONObject json = (JSONObject) JSON.parse(result);
            JSONObject data = (JSONObject) json.get("data");
            String task_id = data.getString("task_id");
            String str = "";
            if(s.contains("_ios")){
                str = umeng.searchStatus(task_id, iosAppkey, iosAppMasterSecret);
                str = "ios:  " + str;
            }else if(s.contains("_android")){
                str = umeng.searchStatus(task_id, androidAppkey, androidAppMasterSecret);
                str = "android:  " + str;
            }
            str = str + "\r\n\r\n";
//            CreatePushTxt.write(str, "C:\\Users\\jyou\\Desktop\\umeng\\result.txt");
            writer.write(str);
            buffer = new StringBuffer();
            Thread.sleep(200);
        }
        br.close();
        writer.close();
        return null;
    }

    public static void main(String[] args) throws Exception {
        UMeng umeng = new UMeng();

//        TODO title可能需要修改
        String isoTitle = "【mo9】您的订单今天已到期，请记得及时还款哦";
        String androidTitle = "【mo9】订单到期提醒";
        String androidText = "您的订单今天已到期，请记得及时还款哦";
////        //TODO 每次都需要更换
        String baseName = "C:\\Users\\jyou\\Desktop\\daihou\\12-13\\";
        String androidFileName = baseName + "ANDROID.txt";
        String iosFileName =baseName + "IOS.txt";
        String androidTokens = umeng.readFile(androidFileName);
        String iosTokens = umeng.readFile(iosFileName);

//        umeng.sendDaiHouAndroidFilecast(androidTokens, androidTitle, androidText);
//        umeng.sendDaiHouIOSFilecast(iosTokens, isoTitle);


        /**
         * 查询
         */
//        umeng.searchStatus("uf20202151304371133100", iosAppkey, iosAppMasterSecret);
//        umeng.searchStatus("uf39295151304365294500", androidAppkey, androidAppMasterSecret);
    }


    /**
     * 贷后安卓文件推送
     * @param fileToken
     * @throws Exception
     */
    public String sendDaiHouAndroidFilecast(String fileToken, String title, String text) throws Exception {
        PushClient client = new PushClient();
        AndroidFilecast filecast = new AndroidFilecast(androidAppkey,androidAppMasterSecret);
        String fileId = client.uploadContents(androidAppkey,androidAppMasterSecret,fileToken);
        filecast.setFileId( fileId);
        filecast.setTicker( title);
        filecast.setTitle(  title);
        filecast.setText(text);
        filecast.goAppAfterOpen();
        filecast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        filecast.setProductionMode();
        String send = client.send(filecast);
        logger.info("友盟ANDROID文件播返回结果-->" + send);
        return send;
    }

    /**
     * IOS文件播推送
     * @param fileToken 需要推送的token，多个token已换行符分隔
     * @throws Exception
     */
    public String sendDaiHouIOSFilecast(String fileToken, String title) throws Exception {
        PushClient client = new PushClient();
        IOSFilecast filecast = new IOSFilecast(iosAppkey,iosAppMasterSecret);
        String fileId = client.uploadContents(iosAppkey,iosAppMasterSecret,fileToken);
        filecast.setFileId(fileId);
        filecast.setAlert(title);
        filecast.setProductionMode();
        String send = client.send(filecast);
        logger.info("友盟IOS文件播返回结果-->" + send);
        return send;
    }

}