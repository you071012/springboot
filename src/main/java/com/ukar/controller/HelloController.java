package com.ukar.controller;

import com.ukar.Test.Consume;
import com.ukar.Test.Product;
import com.ukar.Test.QueueDemo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jyou on 2017/9/5.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {



    @RequestMapping("/index")
    public String index(){
        System.out.println("hello开始执行");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程睡眠一秒");
        Product p = new Product();
        Consume c = new Consume();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(p);
        service.execute(c);
        service.shutdown();
        return "hello";
    }

    @RequestMapping("/model_index")
    public ModelAndView modelIndex(){
        return new ModelAndView("hello");
    }
}
