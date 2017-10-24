package com.ukar.push.umeng.android;


import com.ukar.push.umeng.bean.AndroidNotification;

/**
 * Created by jyou on 2017/10/10.
 */
public class AndroidAlias extends AndroidNotification {

    public AndroidAlias(String appkey, String appMasterSecret) throws Exception {
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
