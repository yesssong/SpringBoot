package com.example.demo_visit;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
@MapperScan(basePackages = "com.example.demo_visit", annotationClass = Mapper.class)
public class DemoVisitApplication{

	public static void main(String[] args) {
		SpringApplication.run(DemoVisitApplication.class, args);
	}


}
