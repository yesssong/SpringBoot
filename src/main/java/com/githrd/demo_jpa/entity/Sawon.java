package com.githrd.demo_jpa.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
//@Table(name="sawon") -> table명=Entity명 일 경우 생략 가능 (대소문자 상관X)
public class Sawon {

    @Id
    int     sabun;

    String  saname;
    String  sasex;

    
    String  sajob;
    String  sahire;
    
    @Column(nullable = true)    // null 허용
    Integer samgr;
    
    int     sapay;
    
    // 참조만 하게 설정 -> why? FK로 설정돼있으니 추가, 수정 못하게 설정해야함
    @Column(insertable=false, updatable=false)
    int     deptno;     // 원래 주인은 Dept테이블

    // Join
    @OneToOne   // 1:1 관계                         // Dept테이블의 deptno
    @JoinColumn(name="deptno", referencedColumnName = "deptno")
    Dept    dept;       // 조인한 것의 결과값을 여기에 넣겠다


    
    @OneToMany  // 1:n
    @JoinColumn(name="godam", referencedColumnName = "sabun")  //godam이 sabun을 참조
    List<Gogek> goList;

}
