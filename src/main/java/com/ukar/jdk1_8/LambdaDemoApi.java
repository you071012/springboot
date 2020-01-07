package com.ukar.jdk1_8;

import com.ukar.jdk1_8.LambdaBean.User;
import org.assertj.core.util.Lists;

import java.io.File;
import java.io.FileFilter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Lambda 对于只申明一个函数的接口，它提供了一个简单和简洁的方式让程序员编写匿名函数，同时改善了Java集合框架库（collection），
 * 使得更加容易迭代、过滤一个集合，更加容易从另一个集合中提取数据。并且在多核计算机的情况下，新特性提高了运算性能
 * <p>
 * x,y -> body
 * x,y :参数列表，  -> :表达式  body :方法体
 */
public class LambdaDemoApi {

    private String name;

    private Integer age;

    public LambdaDemoApi() {
    }

    public LambdaDemoApi(String name) {
        this.name = name;
    }

    public LambdaDemoApi(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public static void testLambda() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        list.forEach(System.out::println);
        list.forEach(integer -> System.out.println("integer=" + integer));
    }

    /**
     * Runnable 接口测试
     */
    public static void testLambdaRunnable() {
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
    public static void testLambdaComparator() {
        User user1 = new User("2");
        User user2 = new User("3");
        User user3 = new User("1");
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

    public static void testMh() {
        //旧写法
        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isHidden();
            }
        });

        //::新写法
        File[] hiddenFiles2 = new File(".").listFiles(File::isHidden);

        //Lambda表达式写法
        File[] hiddenFiles3 = new File(".").listFiles((File file) -> file.isHidden());
    }

    /**
     * lambda表达式生成构造方法
     */
    public void testLambadCreateConstructor() {
        /**
         * 生成不带参数的构造方法,使用 Supplier 接口
         */
        Supplier<LambdaDemoApi> s = LambdaDemoApi::new; //等价于 Supplier<LambdaDemoApi> s = () -> new LambdaDemoApi();

        LambdaDemoApi lambdaDemoApi1 = s.get();
        System.out.println(lambdaDemoApi1);

        /**
         * 生成带一个参数的构造方法，使用 Function 接口
         */
        Function<String, LambdaDemoApi> f = LambdaDemoApi::new; //等价于 Function<String, LambdaDemoApi> f2 = (name) -> new LambdaDemoApi(name);
        LambdaDemoApi lambdaDemoApi2 = f.apply("zhangsan");
        System.out.println(lambdaDemoApi2.name);

        /**
         * 生成带2个参数的构造方法，使用 BiFunction 接口
         */
        BiFunction<String, Integer, LambdaDemoApi> b = LambdaDemoApi::new;
        //等价于 BiFunction<String, Integer, LambdaDemoApi> b2 = (name, age) -> new LambdaDemoApi(name, age);
        LambdaDemoApi lambdaDemoApi3 = b.apply("lisi", 18);
        System.out.println(lambdaDemoApi3.age);

        /**
         * 当使用更多参数时，可以自己新建函数式接口来实现，例如
         *  public interface TriFunction<T, U, V, R>{
         *      R apply(T t, U u, V v);
         *  }
         */

    }


    public static void main(String[] args) {
        LambdaDemoApi.testLambda();
        LambdaDemoApi.testLambdaRunnable();
        LambdaDemoApi.testLambdaComparator();
    }
}
