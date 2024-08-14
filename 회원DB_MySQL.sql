/*
 
 -- 회원 테이블 만들기
 create table member
 (
 	mem_idx		int primary key auto_increment,		-- 회원번호
 	mem_name	varchar(100)  not null,				-- 회원명
 	mem_id		varchar(100)  not null,				-- 아이디
 	mem_pwd		varchar(100)  not null,				-- 비밀번호
 	mem_zipcode char(5)		  not null,				-- 우편번호(숫자로 처리하지말기-나중에 곤란)
 	mem_addr	varchar(1000) not null,				-- 주소
 	mem_ip		varchar(100)  not null,				-- 아이피
 	mem_regdate datetime	  default now(),		-- 가입일자 (입력 안 할시 시스템 시간)
 	mem_grade	varchar(100)  default '일반' 		-- 회원등급
 )
 
 -- 제약조건
   
 -- 아이디 unique
 alter table member
   add constraint unique_member_id unique(mem_id);
   
 -- 회원등급 check
 alter table member
   add constraint ck_member_grade check(mem_grade in('일반', '관리자'));
 
 -- 샘플 데이터 입력
 insert into member values(null,
 						  '김관리',
 						  'admin',
 						  'admin',
 						  '00000',
 						  '서울시 관악구',
 						  '127.0.0.1',
 						   default,
 						   '관리자');

 insert into member values(null,
 						  '일길동',
 						  'one',
 						  '1234',
 						  '00000',
 						  '서울시 관악구',
 						  '127.0.0.1', 
 						  default, 
 						  default);	
 					
 					
-- JDBC에서 사용 할 insert문
-- 위에거 복붙해서 변수부분 ?표 처리 -> ''(작은따옴표) 빼기, 문장 마지막 ;(세미콜론) 빼기
 insert into member values(seq_member_idx.nextVal,?,?,?,?,?,?,default,default);	
 
 				  	  				  	  
 select * from member
 
 
 */

