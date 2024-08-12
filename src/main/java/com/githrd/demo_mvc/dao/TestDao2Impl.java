package com.githrd.demo_mvc.dao;

import org.springframework.stereotype.Repository;
// 데이터를 관리하는 객체임을 알려줌
@Repository("test_dao2") // 이름 지어줌
public class TestDao2Impl implements TestDao{

    public TestDao2Impl() {
        super();
        System.out.println("--TestDao2Impl()--");
    }

    @Override
    public String hello() {
        
        return "TestDao2 : 안녕하세요!";
    }

}
