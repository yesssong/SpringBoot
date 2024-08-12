package com.githrd.demo_transaction.service;

import java.util.List;
import java.util.Map;

import com.githrd.demo_transaction.vo.ProductVo;

public interface ProductService {

	// 전체 조회
	// ArrayList를 Map 안에 담을 것
	Map<String, List<ProductVo>> selectTotalMap();
	
	// 입고 처리
	int insert_in(ProductVo vo) throws Exception;
	
	//	입고 취소
	int delete_in(int idx) throws Exception;
	
	// 출고 처리
	int insert_out(ProductVo vo) throws Exception;
	
	//	출고 취소
	int delete_out(int idx) throws Exception;

}
