package com.example.demo_visit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo_visit.dao.VisitMapper;
import com.example.demo_visit.vo.VisitVo;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


// 해당 action Controller를 사용하려면 web.xml의 FrontController생성부분에 등록

@Controller
@RequiredArgsConstructor
@RequestMapping("/visit/")
public class VisitController {


	//자동 엮기
	final VisitMapper visit_dao;
	
	
	@Autowired
	HttpServletRequest request; //request요청 발생할때마다 DS가 넣어준다
	
	@Autowired
	HttpSession session;        //request요청 발생할때마다 DS가 넣어준다
	
	@Autowired
	ServletContext application; //request요청 발생할때마다 DS가 넣어준다
	
	
	
	// /visit/list.do
	// /visit/list.do?search=all&search_text=
	// /visit/list.do?search=name&search_text=길동
	
	@RequestMapping("list.do")
	public String list(
			           @RequestParam(name="search", defaultValue="all") String search,
			           @RequestParam(name="search_text",required=false) String search_text,
			           Model model) {
		
			
		
		        
		//검색에 필요한 정보를 Map에 넣는다
        Map<String, String> map = new HashMap<String,String>();
        
        if(search.equals("name_content")) { //이름+내용
        	
        	map.put("name", search_text);
        	map.put("content", search_text);
        	
        }else if(search.equals("name")) { //이름
        	
        	map.put("name", search_text);
        	
        }else if(search.equals("content")) { //내용
        	
        	map.put("content", search_text);
        	
        }else if(search.equals("regdate")) { //날짜
        	
        	map.put("regdate", search_text);
        }
 
		//방명록 목록 가져오기
		List<VisitVo> list = visit_dao.selectList(map);
		
		//model통해서 DS로 데이터전달->DS가 request binding시킨다
		model.addAttribute("list", list);
		
		return "visit/visit_list";
		
	}//end:list
	

	//입력폼 띄우기
	@RequestMapping("insert_form.do")
	public String insert_form() {
		
		return "visit/visit_insert_form";
		
	}//end:inser_form
	
	
	//입력
	// /visit/insert.do?name=홍길동&content=잘들어가나&pwd=1234
	
	@RequestMapping("insert.do")
	public String insert(VisitVo vo) {
		//          VisitVo vo내용 : 넘어온 parameter를 VO로 포장해줘라고 DS에게 요청
        
        //  window서버: \r\n
        //  unix  서버: \n
        //  content =   "동해물과\r\n백두산이\r\n마르고\r\n......"
        //                       \r\n -> <br>변경
        String content	= vo.getContent().replaceAll("\n", "<br>");  
        vo.setContent(content);
              
		//3.IP구하기
		String ip		= request.getRemoteAddr();
        vo.setIp(ip);  
		
		//5.DB insert
		int res = visit_dao.insert(vo);
		if(res==0){}
		// 반환정보->DispatcherServlet에게 전달
		// -> 접두어 redirect이면 그이후 명령으로
		//    response.sendRedirect("list.do") 시킨다
		return "redirect:list.do";
		
	}//end: insert
	
	
	// /visit/check_pwd.do?idx=5&c_pwd=1234
	@RequestMapping("check_pwd.do")
	@ResponseBody
	public Map<String, Boolean> check_pwd(int idx,String c_pwd) {
		
		// idx에 해당되는 게시물정보 얻어온다
		VisitVo vo = visit_dao.selectOne(idx);
		//게시물 비밀번호==입력된 비밀번호 같냐?
		boolean bResult = vo.getPwd().equals(c_pwd);
		
        Map<String, Boolean> map = new HashMap<String,Boolean>();
        map.put("result", bResult);
        
        return map;
			
		
	}//end: check_pwd
	
	
	// /visit/delete.do?idx=10
	@RequestMapping("delete.do")
	public String delete(int idx) {
	
		//DB delete
		int res = visit_dao.delete(idx);
		if(res==0){}		
		return "redirect:list.do";
		
	}//end: delete
	
	
    //  /visit/modify_form.do?idx=6
	//수정폼
	@RequestMapping("modify_form.do")
	public String modify_form(int idx,Model model) {
		
		
		//idx해당되는 게시물 1건 얻어오기
		VisitVo  vo = visit_dao.selectOne(idx);
		
		// content => <br> -> \n
		String content = vo.getContent().replaceAll("<br>", "\n");
		vo.setContent(content);
		
		//request binding
		model.addAttribute("vo", vo);
		
		return "visit/visit_modify_form";
		
	}//end:modify_form
	
	//수정
	// /visit/modify.do?idx=5&name=홍길동&content=잘들어가나&pwd=1234
	@RequestMapping("modify.do")
	public String modify(VisitVo vo) {
		
        //                       \r\n -> <br>변경
        String content	= vo.getContent().replaceAll("\n", "<br>");  
        vo.setContent(content);
     
        //3.IP구하기
		String ip		= request.getRemoteAddr();
		vo.setIp(ip);
		
		//5.DB update
		int res = visit_dao.update(vo);
		if(res==0){}
		// 반환정보->DS에게 전달
		// -> 접두어 redirect이면 그이후 명령(list.do)으로
		//    sendRedirect("list.do") 시킨다
		return "redirect:list.do";
		
	}//end: modify
	
}
