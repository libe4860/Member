--##############
--# MEMBER #
--##############

/* 전체조회 */
select * from member;

/* 테이블 삭제 */
drop table member;

/* 테이블 생성 */
create table member (
	user_no				int					not null,	-- 회원번호(자동부여) 				
	user_pw				varchar2(12)		not null,	-- 비밀번호
	company_name		varchar2(30)		not null,	-- 상호명/법인명
	company_number		varchar2(10)		not null,	-- 사업자등록번호
	company_tel			varchar2(11)		not null,	-- 대표 전화번호
	company_type		varchar2(10),					-- 업종
	user_name			varchar2(20)		not null,	-- 이름
	user_tel			varchar2(11)		not null,	-- 전화번호
	email				varchar2(40)		not null,	-- 이메일
	department			varchar2(50),					-- 소속부서
	email_check			char(1)				not null,	-- 이메일 수신동의
	sms_check			char(1)				not null,	-- SMS 수신동의
	member_reg_date		date				not null,	-- 가입일자
	
constraint member_pk primary key (user_no),
constraint member_company_number_uq unique (company_number),
constraint member_email_uq unique (email)
);

/* 시퀀스(자동증가 만들기) 삭제 */
drop sequence member_user_no_seq;
/* 시퀀스(자동증가 만들기) 생성 */
create sequence member_user_no_seq
	start with 1000;
	
/* 데이터 삽입 */
insert into member ( user_no,			
					 user_pw, 			
				   	 company_name, 		
				     company_number,	
				     company_tel,		
				     company_type,		
				     user_name,			
				     user_tel,			
				     email,				
				     department,		
				     email_check,		
				     sms_check,			
				     member_reg_date	
				   )
	values ( member_user_no_seq.nextval,		
			 'abcd1234',					
			 '(주)좋은회사',					
			 1234567890,					
			 0212345678,					
			 'IT',							
			 '이경선',						
			 01012345678,					
			 'seelibe@naver.com',			
			 '개발부',						
			 'y',							
			 'n',							
			 sysdate						
		   );  					   

/* 채번 증가 테이블 user_no generator 조회 */
select * from user_no_generator;

/* 채번 증가 테이블 user_no generator 삭제 */
drop table user_no_generator;

/* 채번 증가 테이블 user_no generator 생성 */
create table user_no_generator(
	name      varchar(20)   not null primary key,
	nextval   varchar2(10)   not null,
	incval    varchar2(5)    not null
);

/* 채번 증가 테이블 user_no generator 삽입 */
insert into user_no_generator
(name, nextval, incval)
values
('memberUserNo', 1000, 1);


--#########
--# BOARD #
--#########

/* 전체조회 */
select * from board;

/* 테이블 삭제 */
drop table board;

/* 테이블 생성 */
create table board (
	user_no			int				not null,
	board_head		varchar2(120)	not null,
	board_contents	CLOB			not null,
	board_reg_date	date			not null,
	item_no			varchar2(30)	not null,
constraint board_pk primary key (user_no),
constraint member_fk foreign key (user_no)
references member (user_no)
);

/* 데이터 삽입 */
insert into board ( user_no,
					board_head,
					board_contents,
					board_reg_date,
					item_no
				  )
values ( 1000,
		 '게시판 테스트 입니다.',
		 '게시판 테스트 입니다.게시판 테스트 입니다.게시판 테스트 입니다.',
		 sysdate,
		 'item_no00'
 	   );

/* Join(게시판 기준) */
select a.user_no 회원번호, b.board_head 게시판제목, b.board_contents 게시판내용,
	   b.board_reg_date 작성일자, b.item_no 제품분류
	from member a, board b
	where a.user_no=b.user_no
	order by a.user_no;
 	   
/* Join(회원번호 전체 조인) */
select a.user_no 회원번호, a.user_pw 비밀번호, a.company_name 법인명상호명,
	   a.company_number 사업자번호, a.company_tel 대표전화번호, a.company_type 업종,
	   a.user_name 담당자이름, a.user_tel 핸드폰번호, a.email 이메일, 
	   a.email_check 이메일수신동의, a.sms_check sms수신동의, a.member_reg_date 가입일,
	   b.user_no 회원번호, b.board_head 게시판제목, b.board_contents 게시판내용,
	   b.board_reg_date 작성일자, b.item_no
	from member a, board b
	where a.user_no=b.user_no
	order by b.user_no;
	

	
select * from member;
select a.user_no,b.* from member a, board b where a.user_no=b.user_no and a.user_no=1001;

select b.user_no 회원번호, b.board_head 게시판제목, b.board_contents 게시판내용,
	   b.board_reg_date 작성일자, b.item_no
	from member a, board b
	where a.user_no=b.user_no
	and a.user_no=1000;







	
/* Join(게시판 제품분류별 조인) */

 	   
a테이블 member : 회원번호(pk) user_no
b테이블 goods_items : 제품분류(pk) item_no
                  : 회원번호(fk) user_no
c테이블 board : 회원번호(pk) user_no



