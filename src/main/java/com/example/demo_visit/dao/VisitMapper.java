package com.example.demo_visit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo_visit.vo.VisitVo;

@Mapper
public interface VisitMapper {
	
	//목록가져오기
	//@Select("select * from visit")
	public List<VisitVo> selectListAll();

	public List<VisitVo> selectList(Map<String, String> map);
	public List<VisitVo> selectListCount(Map<String, Object> map);
	public VisitVo selectOne(int idx);
	
	public int insert(VisitVo vo);
	public int delete(int idx);
	public int update(VisitVo vo);

    public int selectRowTotal(Map<String, Object> map);
	
}
