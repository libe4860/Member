mvn install:install-file -Dfile=ojdbc6.jar -DartifactId=ojdbc6 -DgroupId=com.oracle -Dversion=11.2.0 -Dpackaging=jar

select * from dept;

select * from emp;

select * from BONUS;

select * from SALGRADE;

--
-- Inner Join 1
--
select dept.deptno 부서번호,dept.dname 부서명,emp.hiredate 입사일 from dept, emp
where dept.deptno=emp.deptno
order by 부서번호 asc;
-- or desc
--
-- Inner Join 2
--
select d.deptno,d.dname,e.ename,e.hiredate from dept d,EMP e
where d.deptno = e.deptno
order by d.deptno desc;
--
-- Left Outer Join
--
select d.deptno,d.dname,e.ename,e.hiredate
from dept d right outer join emp e
on d.deptno=e.deptno;
--
-- 1.emp테이블에 직원을 추가하시오
-- empno = 8000
-- ename = 홍길동
-- job = developer
-- deptno = 80
-- dname = 총무부
-- dloc = 서울
select * from emp;
select * from dept;

insert into dept(deptno,dname,LOC) values (80,'총무부','SEOUL');
insert into emp(empno,ename,job,deptno) values (8000,'홍길동','developer',80);
-- 2.dept정보를 수정하시오
-- deptno = 20인 사람을
-- dname = 개발부로 수정
-- loc = 부산으로 수정
update dept set dname = '개발부' , loc = 'BUSAN' where deptno = 20;

-- 3.직원을 퇴사시키시오(삭제)
-- empno > 7300 and empno <= 7800
delete from emp where empno>=7300 and empno<=7600;

-- 4.부서별 직원정보를 출력하시오
-- 단,모든 부서를 출력하시오
select * from dept d left outer join emp e on d.deptno=e.deptno;

-- 5.부서직원 평균을 구하시오
--select d.deptno 부서번호,d.dname 부서명, e.ename 성명, e.sal 급여,
--avg(e.sal),sum(e.sal),max(e.sal),min(e.sal)
--from dept d, emp e where d.deptno=e.deptno
--group by d.dname;

select e.deptno 부서번호, d.dname 부서명,avg(e.sal),sum(e.sal),max(e.sal),min(e.sal)
from emp e right outer join DEPT d
on e.deptno=d.deptno
group by e.deptno,d.dname;

select * from emp order by empno;

desc emp;

rollback

commit;

insert into emp
(
   empno,
   ename,
   job,
   mgr,
   hiredate,
   sal,
   deptno
) values
(
   9400,
   '장익선',
   'deve''xxx''',
   7000,
   '2015/09/10',
   30000.2,
   10
);

insert into emp
(
   empno,
   ename,
   job
) values
(
   1000,
   '장',
   'test'
);

--#########
--#* City #
--#########
select * from City;
--1
DROP TABLE City;
--4
CREATE TABLE City (
       ID                numeric(11)       NOT NULL ,
        Name             char(35)          DEFAULT '',
        CountryCode       char(3)          DEFAULT '',
        District          char(20)          DEFAULT '',
        Population          numeric(11)       DEFAULT 0,
        constraint pk_city PRIMARY KEY (ID),
        constraint fk_country foreign key (countrycode) references country(code)
) ;

--############
--#* Country #
--############
select * from country;
--2
DROP TABLE IF EXISTS Country;
--3
create table country (
        code             char(3)          default '',
           name             char(52)          default '',
        continent       char(20)          default 'Asia',
          region         char(26)          default '',
          surfacearea       numeric(10,2)       default '0.00',
          indepyear       numeric(6)          default null,
         population       numeric(11)       default 0,
         lifeexpectancy    numeric(3,1)       default null,
          gnp             numeric(10,2)      default null,
          gnpold          numeric(10,2)       default null,
         localname       char(45)         default '',
        governmentform    char(45)          default '',
         headofstate       char(60)          default null,
         capital          numeric(11)       default null,
         code2          char(2)          default '',
         constraint ck_continent check (continent in ('Asia','Europe','North America','Africa','Oceania','Antarctica','South America')),
          constraint pk_country primary key (code)
) ;
        --Continent enum('Asia','Europe','North America','Africa','Oceania','Antarctica','South America') DEFAULT 'Asia',


--####################
--#* CountryLanguage #
--####################
DROP TABLE IF EXISTS CountryLanguage;
create table countrylanguage (
        countrycode       char(3)          default '',
        language          char(30)          default '',
        isofficial       char(1)            default 'f',
        percentage       numeric(4,1)       default '0.0',
        constraint ck_isofficial check(isofficial in ('t','f')),
        constraint pk_countrylanguage primary key (countrycode,language)
) ;

select * from countrylanguage;
select * from country;

INSERT INTO Country (
   code,
   name,
   continent,
   region,
   surfaceArea,
   indepYear,
   population,
   lifeExpectancy,
   gnp,
   gnpOld,
   localName,
   governmentForm,
   headOfState,
   capital,
   code2
) values
(
   'BEL',
   'Belgium',
   'Europe',
   'Western Europe',
   30518.00,
   1830,
   10239000,
   77,
   249704.00,
   243948.00,
   'Belgi/Belgique',
   'Constitutional Monarchy',
   'Albert II',
   179,
   'BE'
);

create table filelist(
   fname          varchar(50) primary key,
   flength         numeric(10),
   flast_modified   date,
   image         blob
);

select count(*) from filelist;

delete from dept where deptno = 70;
select * from dept order by deptno;

update dept 
      set dname = 'xxxx', 
              loc     = 'yyyy',
     where  deptno = 19;

update dept set dname = '개발부' , loc = 'BUSAN' where deptno = 20;














--###########
--#* Member #
--###########
select * from member;
drop table member;
create table member(
   id         int not null,
   email      varchar(64) not null,
   password   varchar(64) not null,      
   name      varchar(12) not null,
   regdate      timestamp not null,
   constraint member_pk primary key (id, email),
   constraint member_id_uq unique (id),
   constraint member_email_uq unique (email)
);

select * from member2;
drop table member2;
create table member2(
   id         int not null,
   email      varchar(64) not null,
   password   varchar(64) not null,      
   name      varchar(12) not null,
   regdate      timestamp not null,
   constraint member2_pk primary key (id, email),
   constraint member2_id_uq unique (id),
   constraint member2_email_uq unique (email)
);

--시퀀스(자동증가만들기)
drop sequence member_id_seq;
create sequence member_id_seq
   start with 1000               
;

select member_id_seq.nextval from dual;
select member_id_seq.currval from dual;   --현재val값


insert into member(id, email, password, name, regdate)
values ( member_id_seq.nextval, 'xxx1234', 'yyyy4', 'zz4''z4', sysdate);

/* 채번 증가 테이블 id generator */
select * from id_generator;

drop table id_generator;

create table id_generator(
	name      varchar(20)   not null primary key,
	nextval   numeric(10)   not null,
	incval    numeric(5)    not null
);

insert into id_generator
(name, nextval, incval)
values
('memberId', 10000, 100);

/* update 고려사항 */
select * from member;

update member
  set email 	= null,
	  password 	= 'qwer1234',
	  name	    = null,
	  regdate 	= null
where id		= 14600;

update member
  set password 	= 'qwer1234'
where id		= 14600;

update member
  set name 	= '홍순이'
where id	= 1001;

/* Pagination */
-- ( ) 임시테이블
-- rownum 가상의 컬럼, rownum유지되면서 sort됨
select outer.rn,
	   outer.id,
       outer.email,
       outer.password,
       outer.name,
       outer.regdate
 from (select rownum rn, inner.*
	from(select m.* 
			from member m
		  order by m.id
	    ) inner
	  )outer
 where outer.rn >= 11
  and outer.rn <= 20

-- sort시 rownum 유지안됨
select rownum, e.*
	from emp e
order by e.ename







 

















