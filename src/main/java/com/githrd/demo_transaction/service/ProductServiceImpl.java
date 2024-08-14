package com.githrd.demo_transaction.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.githrd.demo_transaction.dao.ProductInMapper;
import com.githrd.demo_transaction.dao.ProductOutMapper;
import com.githrd.demo_transaction.dao.ProductRemainMapper;
import com.githrd.demo_transaction.vo.ProductVo;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductInMapper 	productInMapper;		// 입고

	@Autowired
	ProductOutMapper 	productOutMapper;		// 출고

	@Autowired
	ProductRemainMapper productRemainMapper;	// 재고
	

	@Override
	public Map<String, List<ProductVo>> selectTotalMap() {
		
		List<ProductVo> in_list		= productInMapper.selectList();		// 입고 목록
		List<ProductVo> out_list	= productOutMapper.selectList();		// 출고 목록
		List<ProductVo> remain_list	= productRemainMapper.selectList();	// 재고 목록
		
		Map<String, List<ProductVo>> map = new HashMap<String, List<ProductVo>>();
		map.put("in_list", in_list);
		map.put("out_list", out_list);
		map.put("remain_list", remain_list);
		
		return map;
	}


	// 입고
	@Override
	public int insert_in(ProductVo vo) throws Exception {
		
		int res = 0;
		
		// 1. 입고 등록하기
		res = productInMapper.insert(vo);
		
		// 2. 재고 등록 또는 수정 처리하기
		ProductVo remainVo = productRemainMapper.selectOneFromName(vo.getName());
		
		if(remainVo==null) {		
			// null=등록 상품이 없다 => 상품 등록 추가하기
			res = productRemainMapper.insert(vo);
		} else {
			// null이 아니다 = 상품이 등록 되어 있다 -> 수량 수정 처리하기
			// 재고 수량 = 기존 재고 수량 + 추가 수량
			int cnt = remainVo.getCnt() + vo.getCnt();
			remainVo.setCnt(cnt);
			
			res = productRemainMapper.update(remainVo);
		}
		
		return res;
	}

	// 출고
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insert_out(ProductVo vo) throws Exception {
		
		int res = 0;
		
		// 1. 출고등록
		res = productOutMapper.insert(vo);
		
		// 2. 재고 정보 얻어오기
		ProductVo remainVo = productRemainMapper.selectOneFromName(vo.getName());
		
		if(remainVo==null) {
			// 재고 목록에 상품이 없을 경우
			throw new Exception("remain_not");		// 강제로 예외 발생시켜서 rollback 유도(이미 출고DB에 insert된 것 실행 취소 되게)
		}else {
			// 재고수량 = 원래 재고 수량 - 출고 수량
			int cnt = remainVo.getCnt() - vo.getCnt();
			
			// 0보다 작을 수 없으니 그에 대한 처리해주기
			if(cnt < 0) {
				// 재고수량 부족한 경우
				throw new Exception("remain_lack");
			}
			// 재고수량 수정
			remainVo.setCnt(cnt);
			res = productRemainMapper.update(remainVo);
		}
		
		return res;
	}


	// 입고 취소
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete_in(int idx) throws Exception {
		
		int res = 0;
		
		// 0. 취소 할 입고 상품 정보 알아오기
		ProductVo insertVo = productInMapper.selectOne(idx);
		
		// 1. 입고 상품 삭제
		// 취소할 때 재고, 출고 목록에 있는 수량 생각해서 취소 해야함(음수되면 안됨)
		// 냉장고 입고100, 재고0, 출고100 => 입고 취소 시 출고 취소 + 재고 원상복귀 => 롤백? 아님 걍 수 더하기 빼기 해줌?
		res = productInMapper.delete(idx);
		
		// 2. 재고 상품 수정
		// 2-1. 재고 정보 얻어오기(인덱스 번호가 아닌 이름으로..)
		ProductVo remainVo = productRemainMapper.selectOneFromName(insertVo.getName());
		
		// 2-2. 재고수량 조정
		int cnt = remainVo.getCnt() - insertVo.getCnt();
		
		// 재고 수량이 음수일 경우 예외 처리 걸기(이전 작업 취소하기 위해(롤백))
		if(cnt < 0) {
			throw new Exception("delete_in_lack");
		}
		
		// 2-3. 수정된 재고 내용 적용하기
		remainVo.setCnt(cnt);
		
		res = productRemainMapper.update(remainVo);

		
		return res;
	}


	// 출고 취소
	@Override
	public int delete_out(int idx) throws Exception {
	
		int res = 0;
		
		// 1. 출고 취소할 항목 가져오기
		ProductVo deleteVo = productOutMapper.selectOne(idx);
		
		// 2. 출고 목록에서 해당 인덱스 삭제
		res = productOutMapper.delete(idx);

		// 3. 재고 상품 수정
		// 3-1) 수정 할 재고 정보 얻어오기
		ProductVo remainVo = productRemainMapper.selectOneFromName(deleteVo.getName());
				
		// 3-2) 재고 수량 수정 -> 원 재고 수량 + 출고 취소 수량 
		int cnt = remainVo.getCnt() + deleteVo.getCnt();
		
		// 변경된 수량 재고 목록에 반영
		remainVo.setCnt(cnt);
		
		res = productRemainMapper.update(remainVo); 
		
		return res;
	}

	
	
	
	
	
	
	
}
