package com.githrd.demo_photo.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.githrd.demo_photo.vo.PhotoVo;

@Mapper
public interface PhotoMapper {

    // 전체 조회
    List<PhotoVo> selecList();

    // Page 별 조회
    List<PhotoVo> selectPageList(Map<String, Object> map);

    // 전체 게시물 수 구하기
    int selectRowTotal();

    PhotoVo selectOne(int p_idx);

    // 추가 - insert
    int insert(PhotoVo vo);
    
    // 삭제
    int delete(int p_idx);
    
    // 수정 - 파일 이름 수정
    int updateFilename(PhotoVo vo);

    // 수정 - 이미지 수정
    int update(PhotoVo vo);
}
