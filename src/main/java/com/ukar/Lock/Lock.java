package com.ukar.Lock;


import com.ukar.util.RandomValue;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * 全局锁，包括锁的名称
 * Created by sun on 2017/8/18.
 */
public class Lock {

  private String name;
  private String value;

  public Lock(String name)
  {
    this.name = name;

    Date now = new Date();
    int i = RandomValue.getNum(23,10000);
    String timestamp = DateFormatUtils.format(now,"yyyyMMddHHmmssSSS");
    this.value = i+timestamp;

  }

  public Lock(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return value;
  }

}

