package com.githrd.demo_jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.githrd.demo_jpa.entity.Sawon;    // repository 연결

public interface SawonRepository extends JpaRepository<Sawon, Integer> {

    // 부서 별 조회 : JPQL, 일반 SQL문 변수 처리 => :변수명 or :1(위치값)   (띄어쓰기 주의)
    // SQL
    // @Query(value="select * from sawon where deptno = :deptno", nativeQuery = true)
    // JPQL :  nativeQuery = false or 생략
    //       1.Table or 속성명은 Entity정보와 일치해야 한다
    //       2.Table Alias명을 사용해야된다
    @Query(value="select s from Sawon s where deptno = :deptno", nativeQuery = false)
    List<Sawon> findAllByDeptno(int deptno);
    
    // Sawon테이블, Dept테이블 innser join Oracle(x) MySQL(o)
    // @Query(value = "select * from Sawon s inner join Dept d on s.deptno=d.deptno", nativeQuery=true)
    @Query(value = "select * from Sawon s join Dept d using(deptno)", nativeQuery=true)
    List<Sawon> findAllWithDept();
}
