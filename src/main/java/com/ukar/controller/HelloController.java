package com.ukar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jyou on 2017/9/5.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/index")
    public String index(){
        return "hello";
    }

    @RequestMapping("/model_index")
    public ModelAndView modelIndex(){
        return new ModelAndView("hello");
    }
}
