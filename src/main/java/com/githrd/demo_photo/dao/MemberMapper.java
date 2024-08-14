package com.githrd.demo_photo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.githrd.demo_photo.vo.MemberVo;

@Mapper
public interface MemberMapper {

    // 전체 조회
    List<MemberVo> selectList();

    // selectOne - mem_idx
    MemberVo selectOneFromIdx(int mem_idx);

    // selectOne - mem_id
    MemberVo selectOneFromId(String mem_id);

    // 추가 - insert
    int insert(MemberVo vo);

    // 수정
    int update(MemberVo vo);
    
    // 삭제
    int delete(int mem_idx);



}
