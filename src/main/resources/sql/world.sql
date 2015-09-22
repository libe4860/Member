select count(*) from city;

select * from city where countrycode = 'kor';
select * from country where code = 'kor';

select countrycode,district from city as c where countrycode = 'kor';
select countrycode,district from city;
desc city;

select * from city;
select * from country;
select * from countrylanguage;

' 문제 : KOR 의 City 정보를 출력하시오'
'name indepYear population'
'cityname population'

select a.countrycode 국가,a.name 도시,a.population 도시인구수,b.population 국가인구수 from city a, country b where a.countrycode=b.code and b.code='kor';

--2
drop table dept;   
--3
create table dept (                     
   deptno numeric(2) not null,
   dname varchar(14) ,
   loc varchar(13),
   constraint pk_dept primary key (deptno)
) engine=InnoDB default charset=utf8;

--1
DROP TABLE EMP;   
--4
create table emp (                     
   empno numeric(4),
   ename varchar(10),
   job varchar(9),
   mgr numeric(4),
   hiredate datetime,
   sal numeric(7,2),
   comm numeric(7,2),
   deptno numeric(2),
   constraint pk_emp primary key (empno),
   constraint fk_deptno foreign key (deptno) references dept(deptno)
) engine=InnoDB default charset=utf8;

drop table bonus;
create table bonus (
   ename varchar(10),
   job varchar(9),
   sal numeric,
   comm numeric
) engine=InnoDB default charset=utf8;

drop table salgrade;
create table salgrade ( 
   grade numeric,
   losal numeric,
   hisal numeric 
) engine=InnoDB default charset=utf8;

select * from dept;
select * from emp;
select * from Country;

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

INSERT INTO `Country` VALUES ('AFG','Afghanistan','Asia','Southern and Central Asia',652090.00,1919,22720000,45.9,5976.00,NULL,'Afganistan/Afqanestan','Islamic Emirate','Mohammad Omar',1,'AF');

--0번지부터 10번지까지 조회
--limit start, count ==> start는 0부터 시작
--? start ==> firstItem - 1
--? count ==> lastItem - firstItem + 1
--1 page :  1 - 10 ==>  0 -  9
select * 
   from city
   limit 0,10;
--2 page : 11 - 20 ==> 10 - 19
select * 
   from city
   limit 10,10;

--totalItem
select count(*) from city;   
   
create table filelist(
   fname          varchar(50) primary key,
   flength         numeric(10),
   flast_modified   datetime,
   image         longblob
);

select count(*) from filelist;   
select fname,flength,flast_modified,image
   from filelist
   where fname = 'hangulkeyboard.apk';
   
drop table filelist;   
   
   
   
   
   
   
   
   
--###########
--#* Member #
--###########
-- email xxx@xxx.com (regex)
-- password 영문 + 숫자 + 6자리 이상 + 3자리 이상 반복 불가 
--          암호화(encryption) ==> MD5, SHA-256
select * from member;
drop table member;
create table member(
   id         int not null auto_increment,
   email      varchar(64) not null,
   password   varchar(64) not null,      
   name      varchar(12) not null,
   regdate      timestamp not null,
   constraint member_pk primary key (id, email),
   constraint member_id_uq unique (id),
   constraint member_email_uq unique (email)
);

--시퀀스(자동증가만들기)
auto_increment
alter table member auto_increment=1000;

select * from member2;
drop table member2;
create table member2(
   id         int not null,
   email      varchar(64) not null,
   password   varchar(64) not null,      
   name      varchar(12) not null,
   regdate      timestamp not null,
   constraint member_pk primary key (id, email),
   constraint member_id_uq unique (id),
   constraint member_email_uq unique (email)
);



insert into member(email, password,name,regdate)
values ('xxx123', 'yyyy', 'zzz', '2015/08/11');

--현재 채번확인
select last_insert_id();


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

-- select lock걸기 : for update; 
--select * from id_generator for update; 
-- mysql은 기본적으로 auto commit 
-- select @@autocommit; ==> commit상태보기(1=autocommit상태) 
-- set autocommit=0; ==> autocommit변경

/* Pagination */
select *
	from city
where countrycode = 'KOR'
order by name
limit 0, 3;
-- limit 0부터시작, length
-- limit 10, 10; //2페이지 10개

   




   
   
   