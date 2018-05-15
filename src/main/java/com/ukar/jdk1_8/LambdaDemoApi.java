package com.ukar.jdk1_8;

import com.ukar.jdk1_8.LambdaBean.User;
import org.assertj.core.util.Lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lambda 对于只申明一个函数的接口，它提供了一个简单和简洁的方式让程序员编写匿名函数，同时改善了Java集合框架库（collection），
 * 使得更加容易迭代、过滤一个集合，更加容易从另一个集合中提取数据。并且在多核计算机的情况下，新特性提高了运算性能
 *
 * x,y -> body
 * x,y :参数列表，  -> :表达式  body :方法体
 */
public class LambdaDemoApi {

    public static void testLambda(){
        List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
//        list.forEach(System.out::println);
        list.forEach(integer -> System.out.println("integer=" + integer));
    }

    /**
     * Runnable 接口测试
     */
    public static void testLambdaRunnable(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("r1 run...");
            }
        };
        r1.run();

        Runnable r2 = () -> System.out.println("r2 run...");
        r2.run();
    }

    /**
     * Comparator 接口测试
     */
    public static void testLambdaComparator(){
        User user1 = new User("zhangsan");
        User user2 = new User("lisi");
        User user3 = new User("wangwu");
        List<User> list = Lists.newArrayList(user1, user2, user3);
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getUserName().compareTo(o2.getUserName());
            }
        });
        list.forEach(user -> System.out.println("方法一：" + user.getUserName()));
        System.out.println("============================");

        /**
         * Lambda表达式写法
         */
        Collections.sort(list, (User u1, User u2) -> u1.getUserName().compareTo(u2.getUserName()));
        list.forEach(user -> System.out.println("Lambda方法二：" + user.getUserName()));
    }

    public static void main(String[] args) {
        LambdaDemoApi.testLambda();
        LambdaDemoApi.testLambdaRunnable();
        LambdaDemoApi.testLambdaComparator();
    }
}
