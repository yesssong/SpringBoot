package com.example.demo_visit.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_visit.dao.VisitMapper;
import com.example.demo_visit.vo.VisitVo;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


// @RestController = @Controller + @ResponseBody


@RestController
@RequiredArgsConstructor
public class VisitSprintBootRestController {
    

    @Autowired
    final VisitMapper visit_dao;

    @Autowired
    HttpServletRequest request;
    
    
    //목록요청시(GET)  :  /rest2/visits
    @GetMapping(value="/rest2/visits") //@RequestMapping(value="/rest/visits", method=RequestMethod.GET)
    public Map<String,Object> visit_list(){

        List<VisitVo> list = visit_dao.selectList(null);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("total_count", list.size());
        map.put("search_date", new Date().toString());
        map.put("list", list);

        return map;
    }

    //게시물1건요청시(GET) : /rest2/visit/{idx}  <= /rest/visit/64
    @GetMapping(value="/rest2/visit/{idx}")
    public VisitVo visit_one(@PathVariable(name="idx") int idx){

        VisitVo vo  = visit_dao.selectOne(idx);

        return vo;
    }

    @GetMapping(value="/rest2/check-pwd/{idx}/{c_pwd}")
    public Map<String,Boolean> check_pwd(@PathVariable("idx") int idx,
                                         @PathVariable("c_pwd") String c_pwd){

        VisitVo vo = visit_dao.selectOne(idx);
        //4.비밀번호 같은지 여부 체크(게시물비번==내가입력한비번)
        boolean bResult = vo.getPwd().equals(c_pwd);

        //결과를 JSON전송
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        map.put("result", bResult);

        return map;
    }


    //게시물 추가(POST) : /rest2/visit
    // @RequestMapping(value="/rest/visit", method=RequestMethod.POST )
    @PostMapping(value="/rest2/visit")
    public Map<String,Boolean> visit_insert(@RequestBody VisitVo vo){

        String content = vo.getContent().replaceAll("\n", "<br>");
        vo.setContent(content);

		//3.ip구하기
		String ip		= request.getRemoteAddr();
		vo.setIp(ip);


        int res = visit_dao.insert(vo);

        //결과 반환 : {"result" : true}
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        map.put("result", (res==1));
        return map;
    }

    //게시물 삭제(DELTE) :  /rest2/visit/{idx}
    @DeleteMapping(value="/rest2/visit/{idx}")
    public Map<String,Boolean> visit_delete(@PathVariable(name="idx") int idx){

        int res = visit_dao.delete(idx);

        //결과 반환 : {"result" : true}
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        map.put("result", (res==1));
        return map;
    }

    //게시물 수정(PUT) : /rest2/visit
    @PutMapping(value="/rest2/visit")
    public Map<String,Boolean> visit_update(@RequestBody VisitVo vo){

        String content = vo.getContent().replaceAll("\n", "<br>");
        vo.setContent(content);

		//3.ip구하기
		String ip		= request.getRemoteAddr();
		vo.setIp(ip);


        int res = visit_dao.update(vo);

        //결과 반환 : {"result" : true}
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        map.put("result", (res==1));
        return map;
    }


}
