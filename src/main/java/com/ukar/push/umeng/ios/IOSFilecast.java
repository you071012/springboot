package com.ukar.push.umeng.ios;


import com.ukar.push.umeng.bean.IOSNotification;

/**
 * Created by jyou on 2017/10/19.
 */
public class IOSFilecast  extends IOSNotification {
    public IOSFilecast(String appkey,String appMasterSecret) throws Exception {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue("appkey", appkey);
        this.setPredefinedKeyValue("type", "filecast");
    }

    public void setFileId(String fileId) throws Exception {
        setPredefinedKeyValue("file_id", fileId);
    }
}
