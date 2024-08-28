package com.example.demo_visit.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo_visit.vo.ProductVo;

import util.MySearchUtil;

@Controller
public class ProductController {

    @RequestMapping("/product/list.do")
    public String list(String p_name,
                       @RequestParam(name="start",  defaultValue = "1") int start,
                       @RequestParam(name="display",defaultValue = "10") int display,Model model){

		
		//상품목록 가져오기
		List<ProductVo> list = MySearchUtil.search_shop(p_name, start, display);
		
        model.addAttribute("list", list);

        return "product/product_list";
    }

}
