package com.shop.dubbo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MainController {

    @RequestMapping("/lac")
    public String a(){
        return "load";
    }
}

