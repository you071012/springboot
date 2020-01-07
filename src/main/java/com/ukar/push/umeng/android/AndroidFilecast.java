package com.ukar.push.umeng.android;


import com.ukar.push.umeng.bean.AndroidNotification;

/**
 * Created by jyou on 2017/10/19.
 */
public class AndroidFilecast extends AndroidNotification {
    public AndroidFilecast(String appkey, String appMasterSecret) throws Exception {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue("appkey", appkey);
        this.setPredefinedKeyValue("type", "filecast");
    }

    public void setFileId(String fileId) throws Exception {
        setPredefinedKeyValue("file_id", fileId);
    }
}
