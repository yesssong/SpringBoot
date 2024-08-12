package com.githrd.demo_jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data   // @Getter + @Setter + @toString-> Lombok
@Entity // Entity임을 알려줌
@Table(name = "dept")   // Entity 이름과 Table명 같을 때 생략 가능한 부분
public class Dept {

    @Id     //primary key
    //@GeneratedValue(strategy = GenerationType.IDENTITY)     // 기본키 생성을 데이터베이스에 위임
    Integer deptno;

    // DB 컬럼명, 제약조건            -> notnull
    @Column(name="dname", nullable = false)   // DB 컬럼명과 내가 쓸 속성명이 같다면 생략 가능
    String  dname;                            // 지금 이름이 같으므로 안 써도 되지만 설명 위해 작성함
    
    @Column
    String  loc;

}
