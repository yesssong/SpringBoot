package com.example.demo_visit.controller;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {


    @GetMapping("/xml_to_json.do")
    public String xml_to_json(){

        String xml = "<person><name>홍길동</name><age>20</age></person>";

        JSONObject json = XML.toJSONObject(xml);

        return json.getJSONObject("person").toString();
    }



    
}
