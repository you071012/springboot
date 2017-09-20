package com.ukar.util;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by jyou on 2017/9/14.
 */
@Configuration //标识相当于一个xml配置
@EnableCaching //启用缓存
//@Import(value = aa.class) 引入其他Configuration配置
//@ImportResource("classpath:aa.xml") 引入外部xml配置文件
//@PropertySource("classpath:application-redis.properties") 引入外部properties配置文件
//@ComponentScan(value = {"com.ukar"}) 定义该配置文件扫描的包
public class ConfigDemoUtils {
}
