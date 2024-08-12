package com.githrd.demo_mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.githrd.demo_mvc.service.TestService;


@Controller
public class HomeController {

    public HomeController(){
        System.out.println("--HomeController()--");
    }

    // TestService 받기
    @Autowired
    TestService test_service;

    @RequestMapping("/hello.do")
    public String hello(Model model) {

        String msg = test_service.hello();

        model.addAttribute("msg", msg);

        return "hello";
    }
    
}
