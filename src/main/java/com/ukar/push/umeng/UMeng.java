package com.ukar.push.umeng;

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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

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
    public String sendAndroidFilecast(String fileToken) throws Exception {
        PushClient client = new PushClient();
        AndroidFilecast filecast = new AndroidFilecast(androidAppkey,androidAppMasterSecret);
        String fileId = client.uploadContents(androidAppkey,androidAppMasterSecret,fileToken);
        filecast.setFileId( fileId);
        filecast.setTicker( "飞鼠贷停更福利不再有？别担心mo9免息给你管够！动动手指下载mo9，放款一样溜！");
        filecast.setTitle(  "飞鼠贷停更福利不再有？别担心mo9免息给你管够！动动手指下载mo9，放款一样溜！");
        filecast.setText(   "查看详情");
        filecast.goAppAfterOpen();
        filecast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        filecast.setProductionMode();
        filecast.setExtraField("mo9_title", "飞鼠贷停更福利不再有？别担心mo9免息给你管够！动动手指下载mo9，放款一样溜!");
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
    public String sendIOSFilecast(String fileToken) throws Exception {
        PushClient client = new PushClient();
        IOSFilecast filecast = new IOSFilecast(iosAppkey,iosAppMasterSecret);
        String fileId = client.uploadContents(iosAppkey,iosAppMasterSecret,fileToken);
        filecast.setFileId(fileId);
        filecast.setAlert("飞鼠贷停更福利不再有？别担心mo9免息给你管够！动动手指下载mo9，放款一样溜！");
        filecast.setProductionMode();
        filecast.setCustomizedField("mo9_title", "飞鼠贷停更福利不再有？别担心mo9免息给你管够！动动手指下载mo9，放款一样溜!");
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
    public void searchStatus(String task_id,String app_key, String appMasterSecret) throws Exception {
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


    public static void main(String[] args) throws Exception {
        UMeng umeng = new UMeng();


//        String tokens = umeng.readFile("C:\\Users\\jyou\\Desktop\\umeng\\128066_ios.txt");
////        添加上自己的token
////        tokens = tokens + "\n" + "ef2d09ef2855e2944605c136576ef6cfc801f421823c99300657c0a8fda5249c";
//        umeng.sendIOSFilecast(tokens);



//        String tokens = umeng.readFile("C:\\Users\\jyou\\Desktop\\umeng\\54945_android.txt");
//        //添加上自己的token
//       // tokens = tokens + "\n" + "AqS_3FPq7Wvrt6en87DtUlA7vVglV6wcemBwwW58rX94";
//        umeng.sendAndroidFilecast(tokens);


//        umeng.searchStatus("uf28162150840597064600", iosAppkey, iosAppMasterSecret);
//        umeng.searchStatus("uf67341150882924032500", androidAppkey, androidAppMasterSecret);
    }


}