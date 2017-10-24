package com.ukar.push.umeng.ios;


import com.ukar.push.umeng.bean.IOSNotification;

/**
 * Created by jyou on 2017/10/10.
 */
public class IOSAlias extends IOSNotification {

    public IOSAlias(String appkey, String appMasterSecret) throws Exception {
        setAppMasterSecret(appMasterSecret);
        setPredefinedKeyValue("appkey", appkey);
        this.setPredefinedKeyValue("type", "customizedcast");
    }


    public void setAliasType(String aliasType) throws Exception {
        setPredefinedKeyValue("alias_type", aliasType);
    }

    public void setAlias(String alias) throws Exception {
        setPredefinedKeyValue("alias", alias);
    }
}
