//package com.ukar.jdk1_8.stream;
//
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Supplier;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
///**
// * Created by jyou on 2018/5/4.
// */
//public class StreamDemo {
//
//    public static void main(String[] args) {
//        Supplier<StreamDemo> s = StreamDemo :: new;
//        StreamDemo streamDemo = s.get();
////        streamDemo.testStream();
//        streamDemo.threeHighCaloricDishNames();
////        streamDemo.testDistinct();
////        streamDemo.testLimit();
////        streamDemo.testSkip();
////        streamDemo.testAnyMatch();
////        streamDemo.testAllMatch();
////        streamDemo.testNoneMatch();
////        streamDemo.testFindAny();
////        streamDemo.testFindFirst();
////        streamDemo.testReduce();
//    }
//
//    public void testStream(){
//        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
//        //顺序执行
//        List<String> collect = list.stream().filter(str -> !str.isEmpty()).collect(Collectors.toList());
//        //并行执行
////        List<String> collect = list.parallelStream().filter(str -> !str.isEmpty()).collect(Collectors.toList());
//
//        collect.forEach(str -> System.out.println(str));
//    }
//
//    /**
//     * 获取固定菜单列表
//     * @return
//     */
//    private List<Dish> getMenu() {
//        List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT), new Dish("beef", false, 700, Dish.Type.MEAT),
//                new Dish("chicken", false, 400, Dish.Type.MEAT), new Dish("french fries", true, 530, Dish.Type.OTHER),
//                new Dish("rice", true, 350, Dish.Type.OTHER), new Dish("season fruit", true, 120, Dish.Type.OTHER),
//                new Dish("pizza", true, 550, Dish.Type.OTHER), new Dish("prawns", false, 300, Dish.Type.FISH),
//                new Dish("salmon", false, 450, Dish.Type.FISH));
//
//        return menu;
//    }
//
//    /**
//     * 筛选高热量菜品名称,calories > 300
//     */
//    public void threeHighCaloricDishNames(){
//        List<Dish> menu = getMenu();
//        List<String> collect = menu.stream().filter(dish -> dish.getCalories() > 300).map(Dish :: getName).collect(Collectors.toList());
////        等价于List<String> collect = menu.stream().filter(dish -> dish.getCalories() > 300).map(dish -> dish.getName()).collect(Collectors.toList());
//        collect.forEach((name) -> System.out.println(name));
//        Comparator<Dish> comparing = Comparator.comparing(t -> t.getName());
//        System.out.println("=========================");
//        Optional<Dish> collect1 = menu.stream().collect(Collectors.maxBy(comparing));
//        System.out.println(collect1.orElse(null));
//    }
//
//    /**
//     * distinct方法 它会返回一个元素各异（根据流所生成元素的 hashCode和equals方法实现）的流
//     */
//    public void testDistinct(){
//        List<Integer> list = Arrays.asList(1,2,1,2,3,5,8,4,5,5,4);
//        List<Integer> collect = list.stream().filter(num -> num % 2 == 0).distinct().collect(Collectors.toList());
//        collect.forEach(num -> System.out.println(num));
//    }
//
//    /**
//     * limit(n)方法，截断流，获取流前n个元素
//     */
//    public void testLimit(){
//        List<Dish> menu = getMenu();
//        List<Dish> collect = menu.stream().filter(dish -> dish.getCalories() > 300).limit(3).collect(Collectors.toList());
//        collect.forEach(dish -> System.out.println(dish.toString()));
//    }
//
//    /**
//     * skip(n)方法，，返回一个扔掉了前n个元素的流
//     */
//    public void testSkip(){
//        List<Dish> menu = getMenu();
//        List<Dish> collect = menu.stream().filter(dish -> dish.getCalories() > 300).skip(1).collect(Collectors.toList());
//        collect.forEach(dish -> System.out.println(dish.toString()));
//    }
//
//    /**
//     * anyMatch方法可以回答“流中是否有一个元素能匹配给定的谓词”。
//     */
//    public void testAnyMatch(){
//        List<Dish> menu = getMenu();
//        boolean b = menu.stream().anyMatch(dish -> dish.isVegetarian());
//        System.out.println(b);
//    }
//
//    /**
//     * allMatch方法可以回答“流中是否有所以元素都能匹配给定的谓词”。
//     */
//    public void testAllMatch(){
//        List<Dish> menu = getMenu();
//        boolean b = menu.stream().allMatch(dish -> dish.isVegetarian());
//        System.out.println(b);
//    }
//
//    /**
//     * noneMatch方法可以回答“流中是否有所以元素都不能匹配给定的谓词”。
//     */
//    public void testNoneMatch(){
//        List<Dish> menu = getMenu();
//        boolean b = menu.stream().noneMatch(dish -> dish.getName().equals("chicken001"));
//        System.out.println(b);
//    }
//
//    /**
//     * findAny方法将返回当前流中的任意元素
//     */
//    public void testFindAny(){
//        List<Dish> menu = getMenu();
//        menu.stream().filter(Dish :: isVegetarian).findAny().ifPresent(dish -> System.out.println(dish.getName()));
//    }
//
//    /**
//     * findFirst方法将返回当前流中查找到的第一个元素
//     */
//    public void testFindFirst(){
//        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);
//        list.stream().map(num -> num * num).filter(num -> num % 2 == 0).findFirst().ifPresent(num -> System.out.println(num));
//    }
//
//    /**
//     * 归约方法 reduce(),接收2个参数，一个是初始值，一个是计算之后产生的新值。也可以不接收初始值，用第二个参数的第一个值做初始值
//     */
//    public void testReduce(){
//        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);
//        Integer sum = list.stream().filter(a -> a != 3).reduce(0, (a, b) -> a + b);
//        Integer m = list.stream().reduce((a, b) -> a * b).get();
//        Integer max = list.stream().reduce(Integer :: max).get();
//        System.out.println(max);
//        System.out.println(sum);
//        System.out.println(m);
//    }
//}
