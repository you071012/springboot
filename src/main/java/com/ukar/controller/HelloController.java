package com.ukar.controller;

import com.alibaba.fastjson.JSONObject;
import com.ukar.annotation.AnnotationDemo;
import com.ukar.httpclient.HttpClientApi;
import org.apache.http.client.HttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.URL;


/**
 * Created by jyou on 2017/9/5.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    @Resource
    private HttpClientApi httpClient;


    @RequestMapping("/index")
    @ResponseBody
    @AnnotationDemo(value = "nice")
    public String index() {
        return "hello";
    }

    @RequestMapping("/model_index")
    public ModelAndView modelIndex() {
        return new ModelAndView("hello");
    }
}
