package com.ukar.jdk1_8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jyou on 2018/5/4.
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> collect = list.stream().filter(str -> !str.isEmpty()).collect(Collectors.toList());
        collect.forEach(str -> System.out.println(str));
    }
}
