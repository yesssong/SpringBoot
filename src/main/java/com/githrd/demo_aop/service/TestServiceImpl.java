package com.githrd.demo_aop.service;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService{

    @Override
    public String hello() {

        try {
            Thread.sleep(1234);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Hello Everyone!!!";
    }

    @Override
    public String hi() {
        for(int i=0;i<1000021;i++);
        return "Hi Thankyou :)";
    }

}
