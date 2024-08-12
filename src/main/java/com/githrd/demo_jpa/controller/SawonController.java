package com.githrd.demo_jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.githrd.demo_jpa.entity.Sawon;
import com.githrd.demo_jpa.repository.SawonRepository;

@RestController     // @Controller + @ResponseBody -> json으로 반환해줌
public class SawonController {

    @Autowired
    SawonRepository sawonRepository;

    // 전체 조회
    @GetMapping("/sawons")
    public List<Sawon> selectList(){

        return sawonRepository.findAll(); 
    }
    
    // 전체 조회 + query
    @GetMapping("/sawons-dept")
    public List<Sawon> selectListWithDept(){

        return sawonRepository.findAllWithDept(); 
    }

    // 부서 별 조회
    @GetMapping("/sawons/{deptno}")
    public List<Sawon> selectListFromDeptno(@PathVariable int deptno){

        return sawonRepository.findAllByDeptno(deptno); 
    }

    // 정렬
    @GetMapping("/sawons-sortbysapaydesc")
    public List<Sawon> selectListSortBySapayDesc(){

        return sawonRepository.findAll(Sort.by("sapay").descending()); 
    }

    // page 처리         /페이지 갯수/몇개씩 보이게 할건지
    @GetMapping("/sawons/{start}/{count}")
    public List<Sawon> selectListByPaging(@PathVariable int start, @PathVariable int count){

        //page 0부터 시작
        Pageable pageAble = PageRequest.of(start,count,Sort.by("sabun"));

        Page<Sawon> pageSawon = sawonRepository.findAll(pageAble);

        // 페이지를 내가 원하는 리스트로 바꾸려면?
        // Page<Sawon> ->  List<Sawon> 변환 : getContent()

        return pageSawon.getContent(); 
    } 

}
