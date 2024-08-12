package com.githrd.demo_jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.githrd.demo_jpa.entity.Dept;
import java.util.List;


@Repository
public interface DeptRepository extends JpaRepository<Dept,Integer> {

    // 전체 조회
    // JPQL -> JPA에서 제공하는 sql 작성법 (문법 따로 있음)
    //                d = Alias Type   Dept => Entity명
    // @Query("select d from Dept d order by d.deptno asc")
    // 일반 SQL문 
    @Query(value = "select * from dept order by deptno asc", nativeQuery = true)
    List<Dept> findAll(); //-> 이거 정의하지 않더라도 Jpa가 알아서 만들어줌 대박

    // deptno(부서번호)로 조회
    // Dept             findByDeptno(Integer deptno);   // findById로 해도 됨
    Optional<Dept>      findByDeptno(Integer deptno);   // Optional??? 데이터가 안 들어왔을 상황에 대비해서 적는..??????

    // 저장(insert)
    //Integer save(Dept dept);


    // 기본적으로 지원 해주지 않는 쿼리문에 대해서는 이런 식으로 따로 작성해서 사용하면 됨
    @Query(value = "select * from dept where loc = loc", nativeQuery = true)
    List<Dept> findByLoc(String loc);
    
}
