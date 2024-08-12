package com.githrd.demo_transaction.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.githrd.demo_transaction.vo.ProductVo;

@Mapper
public interface ProductOutMapper {
	
	// 전체 조회
	List<ProductVo> selectList();
	
	ProductVo		selectOne(int idx);
	
	int				insert(ProductVo vo);
	int				update(ProductVo vo);
	int				delete(int idx);
}
