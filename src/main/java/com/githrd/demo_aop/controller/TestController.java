package com.githrd.demo_aop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.githrd.demo_aop.service.TestService;

@RestController     // view 파일 없으므로 이거 사용..? json, string 다 반환해준다 설명하심
public class TestController {

    // TestService 연결
    @Autowired
    TestService testService;

    @GetMapping("/hello")
    public String hello(){
        return testService.hello();
    }

    @GetMapping("/hi")
    public String hi(){
        return testService.hi();
    }
}
