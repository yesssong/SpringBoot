package com.githrd.demo_interceptor.interceptor;

import java.util.Map;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

    @SuppressWarnings("unchecked")
    @Override      // interceptor 전에 실행됨
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //log.info("==================== BEGIN ====================");
        //log.info("Request URI ===> " + request.getRequestURI());
        System.out.println("==================== BEGIN ====================");
        System.out.println("Request URI ===> " + request.getRequestURI());

        //로그인 정보 얻어오기
        Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
        
        // 로그인 안됐을 경우 -> 못 들어오게 막기
        if(user==null){

            response.sendRedirect("../myerror?reason=session_timeout");

            return false;
        }

        String uri = request.getRequestURI();
        // uri = /admin/members
        // uri = /adult/...

        if(uri.startsWith("/admin/")){      // = uri가 admin으로 시작?
            // 관리자냐
            String grade = (String) user.get("grade");
            if(!grade.equals("admin")){         // 등급이 admin이 아니면

                response.sendRedirect("../myerror?reason=not_admin");

                return false;
            }
        }else if(uri.startsWith("/adult/")){
            // 성인이냐
            int age = Integer.parseInt((String)user.get("age"));
            if(age<20){
                response.sendRedirect("../myerror?reason=not_adult");
                return false;
            }
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override   // interceptor 후에 실행됨
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //log.info("==================== END ======================");
        System.out.println("==================== END ======================");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

}
