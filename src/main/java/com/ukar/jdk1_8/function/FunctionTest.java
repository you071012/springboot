package com.ukar.jdk1_8.function;

import com.ukar.entity.User;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by jyou on 2018/5/3.
 */
public class FunctionTest {

    public static void main(String[] args) {
        /**
         *  Function 输入参数为类型T， 输出为类型R， 记作 T -> R
         */
        Function<String, Integer> function = (String s) -> s.length();
        System.out.println(function.apply("123456"));

        /**
         * 输入参数为类型T， 输出为void， 记作 T -> void
         */
        User user = new User();
        Consumer<User> consumer = (User user1) -> user1.setName("ukar");
        consumer.accept(user);
        System.out.println(user.getName());
    }
}
