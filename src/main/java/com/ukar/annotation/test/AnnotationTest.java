package com.ukar.annotation.test;

import com.ukar.annotation.AnnotationDemo;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by jyou on 2017/9/18.
 */
@AnnotationDemo(value = "class", name = "class_name")
public class AnnotationTest {

    @AnnotationDemo(value = "method", name = "method_name")
    public String test(){
        System.out.println("注解test执行。。。。。。。。。。。");
        return "执行完毕。。。。。";
    }

    public static void main(String[] args) {

        Class<AnnotationTest> clazz = AnnotationTest.class;
        /**
         * 获取类上所有注解,方法，成员变量等类似
         */
        Annotation[] annotations = clazz.getAnnotations();
        System.out.println(annotations.length);
        /**
         * 获取类上所有注解,但是不包括父类中被Inherited修饰的注解
         */
        Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();

        /**
         * 获取方法上指定注解
         */
        Method[] methods = clazz.getMethods();
        for(Method method : methods){
            if(method.isAnnotationPresent(AnnotationDemo.class)){
                AnnotationDemo annotation = method.getAnnotation(AnnotationDemo.class);
                String value = annotation.value();
                String name = annotation.name();
                System.out.println("value = " + value);
                System.out.println("name = " + name);
            }
        }

        /**
         * 获取类上指定注解
         */
        boolean boo = clazz.isAnnotationPresent(AnnotationDemo.class);
        if(boo){
            AnnotationDemo annotation = clazz.getAnnotation(AnnotationDemo.class);
            System.out.println("value = " + annotation.value());
            System.out.println("name = " + annotation.name());
        }
    }
}
