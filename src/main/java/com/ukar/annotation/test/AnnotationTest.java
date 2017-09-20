package com.ukar.annotation.test;

import com.ukar.annotation.AnnotationDemo;
import org.springframework.stereotype.Component;

/**
 * Created by jyou on 2017/9/18.
 */
@Component
public class AnnotationTest {

    @AnnotationDemo(name = "ukar")
    public String test(){
        System.out.println("注解test执行。。。。。。。。。。。");
        return "执行完毕。。。。。";
    }
}
