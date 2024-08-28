package com.example.demo_visit.vo;

import org.apache.ibatis.type.Alias;

import lombok.Data;

// <typeAliases>
// 	   <typeAlias type="com.example.demo_visit.vo.VisitVo"  alias="visit"/>	 
// </typeAliases>

@Data
@Alias("visit")
public class VisitVo {

	//VO Property(속성)
	int    idx;
	String name;
	String content;
	String pwd;
	String ip;
	String regdate;
	
	public VisitVo() {}
	
	// insert 포장시
	public VisitVo(String name, String content, String pwd, String ip) {
		super();
		this.name = name;
		this.content = content;
		this.pwd = pwd;
		this.ip = ip;
	}
	
	// update 포장시
	public VisitVo(int idx, String name, String content, String pwd, String ip) {
		super();
		this.idx = idx;
		this.name = name;
		this.content = content;
		this.pwd = pwd;
		this.ip = ip;
	}

}
