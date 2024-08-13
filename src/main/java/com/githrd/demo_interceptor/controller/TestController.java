package com.githrd.demo_interceptor.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class TestController {

    // login 정보 저장할 session 얻어오기?
    @Autowired
    HttpSession session;


    @GetMapping("/home")
    @ResponseBody
    public String home(){
        return "My Home";
    }

    @GetMapping("/hi")
    @ResponseBody
    public String hi(){
        return "Hi";
    }


    //login?name==홍길동&age=20&grade=normal(일반) or admin(관리자)
    @GetMapping("/login")
    @ResponseBody       // Map으로 세 가지 정보 받아오기
    public String login(@RequestParam Map<String, Object> user){   // 이렇게 쓰면 알아서 만들어줌 map을 따로 만들 필요가 없다?

        // 잘 받아졌는지 터미널에서 확인
        System.out.println(user);

        // 로그인 유저 정보를 session에 담기
        session.setAttribute("user", user);

        return "Login 완료";
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout(){

        // session에 들어간 user(유저 정보) 삭제
        session.removeAttribute("user");

        return "Logout 완료";
    }

    // 회원정보 보기 - 관리자만
    @GetMapping("/admin/members")
    public String members(){
        return "admin/member_list";
    }

    // 사진 보기 - 성인만
    @GetMapping("/adult/photo")
    public String photo(){
        return "adult/photo";
    }

    // error 메세지
    // /error?reason=session_timeout
    @GetMapping("/myerror")
    @ResponseBody
    public String myerror(String reason){

        String message="no_message";

        switch (reason) {
            case "session_timeout" : message="로그아웃 되었습니다."; break;
            case "not_admin"       : message="관리자만 접근 가능한 페이지입니다."; break;
            case "not_adult"       : message="성인만 접근 가능한 페이지입니다."; break;
        }
        return message;
    }

}
