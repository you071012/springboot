package com.ukar.Lock;

/**
 * Created by jyou on 2017/9/22.
 *
 * 分布式锁
 */
public class Lock {
    /**
     * 锁的key
     */
    private String name;

    /**
     * 锁的value
     */
    private String value;


    public Lock() {
    }

    public Lock(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
