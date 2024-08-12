package com.githrd.demo_mvc.dao;

import org.springframework.stereotype.Repository;
// 데이터를 관리하는 객체임을 알려줌
@Repository("test_dao") // 이름 지어줌
public class TestDaoImpl implements TestDao{

    public TestDaoImpl() {
        super();
        System.out.println("--TestDaoImpl()--");
    }

    @Override
    public String hello() {
        
        return "TestDao : 안녕하세요!";
    }

}
