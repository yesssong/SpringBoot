package com.githrd.demo_mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.githrd.demo_mvc.dao.TestDao;

@Service("test_service")
public class TestServiceImpl implements TestService{

    // TestDao injection
    @Autowired
    @Qualifier("test_dao")  // 만들어진 bean 중에서 test_dao injection 할 것 = bean id가 test_dao인 객체 정보 injection
    TestDao test_dao;       // -> 똑같은 인터페이스를 구현한 객체가 여러 개일 경우 어떤 것을 injection할지 이름으로 명확하게 알려줘야 함

    // TestDao2 injection
    @Autowired
    @Qualifier("test_dao2")
    TestDao test_dao2; 

    //생성자 자동 생성 : ctor 타이핑 후 tab 2번
    public TestServiceImpl() {
        super();
        System.out.println("--TestServiceImpl()--");
    }

    // code Generator : Ctrl + .
    @Override
    public void test() {
        
    }

    @Override
    public String hello() {

        StringBuffer sb = new StringBuffer();
        sb.append("msg1=>");
        sb.append(test_dao.hello());
        sb.append("<br>");
        sb.append("msg2=>");
        sb.append(test_dao2.hello());


        return sb.toString();
    }

    
}
