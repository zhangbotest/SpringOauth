package com.bobo.cn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/test")
@RestController
public class TestController {
   @RequestMapping("/test")
    public String test(){
        return "222";
    }
}
