package com.githrd.demo_jpa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.githrd.demo_jpa.entity.Dept;
import com.githrd.demo_jpa.repository.DeptRepository;



// @Controller + @ResponseBody
@RestController
public class DeptController {

    @Autowired
    DeptRepository deptRepository;

    // 조회 : GetMapping
    @GetMapping("/depts")
    public List<Dept> list(){

        List<Dept> list = deptRepository.findAll();

        return list;
    }

    // 부서별 조회
    @GetMapping("/dept/{deptno}")
    public Dept selectOne(@PathVariable int deptno){

        Optional<Dept> dept_op = deptRepository.findByDeptno(deptno);

        // Optional로 했는데 데이터 잘 가져왔냐?
        if(dept_op.isPresent()){
            Dept dept = dept_op.get();
            return dept;
        }
        return null;
    } 

    // 추가 기능 - 부서 등록
    @PostMapping("/dept")
    public Map<String,Boolean> insert(@RequestBody Dept dept){



        // jpa가 내부적으로 insert 시켜줌
        Dept resDept = deptRepository.save(dept);

        Map<String,Boolean> map = new HashMap<String,Boolean>();
        map.put("result", resDept!=null);
        
        return map;
    }

    // 삭제 기능 - 부서 삭제
  //= @RequestMapping(value="/dept/{deptno}", method=RequestMethod.DELETE) <- 이거 써도 됨~
    @DeleteMapping("/dept/{deptno}")
    public Map<String,Boolean> delete(@PathVariable int deptno){

        deptRepository.deleteById(deptno);      // 이렇게 mapping만 해주면 알아서 삭제 해줌

        Map<String,Boolean> map = new HashMap<String,Boolean>();
        map.put("result", true);
        
        return map;
    }

    // 수정 기능 - 등록 되어 있는 부서 정보 수정
    @PutMapping("/dept")
    public Map<String,Boolean> update(@RequestBody Dept dept){

        // 수정 할 원본 data 읽어오기                     findById로 써도 됨 
        Optional<Dept> oriDept_Optional = deptRepository.findByDeptno(dept.getDeptno());     // Optional로 받음
        Dept oriDept = null;
        if(oriDept_Optional.isPresent()){         // 값이 존재하는지 물어보는 것
            oriDept = oriDept_Optional.get();
            
            // 수정된 데이터로 저장
            oriDept = deptRepository.save(dept);
        } 

        Map<String,Boolean> map = new HashMap<String,Boolean>();
        map.put("result", oriDept!=null);
        
        return map;
    }


    
}
