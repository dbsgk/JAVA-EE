select * from tab;
create table 성적(이름 CHAR, 국어 INT, 영어 INT, 수학 INT, 총점 
create table 테이블명(필드명 필드타입, 필드명 필드타입,...)

create table 연산(
x int,
y number,
z number(10,3));
select * from tab;
insert into 연산(x,y,z) values(25,36,12.34567);
select * from 연산;
insert into 연산(x,y,z) values(25.34567,36.34567,12.34567);
insert into 연산(x,y)  values(25.666,36.88888);
insert into 연산(z,y,x)values(1,2,3);
insert into 연산 values(25,36,1234567.3456);
insert into 연산 values(25,36,12345678.3456); --에러 유효숫자 크기 넘어감
select * from 연산;


commit;


create table dbtest(
name varchar2(25),
age number,
height number(10,2),
logtime date);
select * from dbtest;
insert into dbtest(name,age,height,logtime) values('홍길동',25,185.567,sysdate);
insert into dbtest(name,age,height,logtime) values('Hong',30,175.56,sysdate);
insert into dbtest(name,age) values('희동이',3);
insert into dbtest(name,height) values('홍당무',168.89);
insert into dbtest values('분홍신',5,123.5,sysdate);
insert into dbtest(name) values ('진분홍');
select name,age, height, logtime from dbtest;
select * from dbtest;
select * from dbtest where name = '홍길동';
select name,logtime from dbtest where name = '홍길동';
select * from dbtest where name ='Hong';
select * from dbtest where name like '홍%';
select * from dbtest where name like '%홍%';
select * from dbtest where name like '%홍';
select * from dbtest where name like '_홍%';
select * from dbtest where name like '__홍%';

select * from dbtest where name like '%홍%' and age <= 20;
select * from dbtest where age is null;
select * from dbtest where age is not null;

select * from dbtest order by name asc; --오름차순(default)
select * from dbtest order by name desc; -- 내림차순

select * from dbtest where name like'%홍%' order by age asc;

update dbtest set age=0 where name = '홍당무'; 
update dbtest set age=0,height=0 where name='진분홍'; -- and가 아니라 ,(쉼표)사용
update dbtest set logtime = sysdate where name ='진분홍';
update dbtest set age = age+1 where name ='진분홍';

delete dbtest where name like '%홍%';

rollback;
select * from dbtest;
select name,age,height,to_char(logtime,'YYYY.MM.DD') from dbtest;

create table student(
name  varchar2(15)  not  null, -- 이름
value   varchar2(15), -- 학번 or 과목 or 부서
code   number   -- 1이면 학생, 2이면 교수, 3이면 관리자
);
select *from student;
delete student where name = '희동이';
select * from student where name like '%송%';

create sequence test_seq;
select TEST_SEQ.nextval from dual; --dual: 아무것도 없는 가상 테이블
--한번 나온 번호 다시 안나옴 (중복 방지 가능)
create sequence num increment by 2 start with 1 maxvalue 9 cycle nocache;
select num.nextval from dual;
drop sequence num;
drop sequence test_seq;

create  table  friend(
seq number primary key, -- 기본키(not null, unique)
name  varchar2(15)  not  null,
tel1  varchar2(5),
tel2  varchar2(5),
tel3  varchar2(5),
gender  number,   -- 남자는 0, 여자는 1
read  number default  0, -- 선택해제 0, 선택 1
movie  number default  0,
music  number default  0,
game  number default  0,
shopping  number default  0);
create sequence seq_friend nocycle nocache;
alter table friend rename column shopping to shop;

insert into friend(
    seq,name,tel1,tel2,tel3,gender,read,movie,music,game,shop) 
    values(seq_friend.nextval ,'홍길동',010,123,1234,1,1,0,0,1,0);
    
select * from friend order by seq asc;
select * from friend order by seq desc;
update friend set name='모디파이',tel1='010',tel2='1',tel3='1',gender=1,read=1,movie=1,music=1,game=1,shop=1 where seq=9;
delete friend where seq=10;

create table member(
name varchar2(30) not null,
id varchar2(30) primary key, --기본키, unique, not null, 무결성 제약 조건
pwd varchar2(30) not null,
gender varchar2(3),
email1 varchar2(20),
email2 varchar2(20),
tel1 varchar2(10),
tel2 varchar2(10),
tel3 varchar2(10),
zipcode varchar2(10),
addr1 varchar2(100),
addr2 varchar2(100),
logtime date);

insert into member(name,id,pwd) values('홍길동','hong','111');
select * from member;
select id from member where id='hong' and pwd='111';
select name from member where id='hong';
delete member where id='dbsgk';
commit;

desc member;
select * from member2;
create table member2(
name varchar2(30) not null,
id varchar2(30) primary key, --기본키, unique, not null, 무결성 제약 조건
pwd varchar2(30) not null,
gender varchar2(10),
email1 varchar2(20),
email2 varchar2(20),
tel1 varchar2(10),
tel2 varchar2(10),
tel3 varchar2(10),
zipcode varchar2(10),
addr1 varchar2(100),
addr2 varchar2(100),
logtime date);
commit;

select * from member2;
desc member;

create table newzipcode (
zipcode   varchar2(7),
sido   varchar2(20),
sigungu   varchar2(30),
yubmyundong   varchar2(20),
ri   varchar2(20),
roadname   varchar2(100),
buildingname   varchar2(100));

--load data
--infile 'D:/lib/newzipcode.csv'
--insert into table newzipcode
--fields terminated by ","
--trailing nullcols
--(zipcode,
--sigungu,
--yubmyundong,
--ri,
--roadname,
--buildingname)

select * from newzipcode where yubmyundong like '%구의%';
delete newzipcode;
commit;

select count(zipcode) from newzipcode;

select * from newzipcode where sido like '% %' and sigungu like yubmyundong like '%구의%';

select * from newzipcode where sido='서울' and sigungu like '%광진%' and roadname like '%길%';
select * from member order by logtime;
delete member where name='아몰랑';
desc member;
commit;

CREATE TABLE board(
     seq NUMBER NOT NULL,               -- 글번호
     id VARCHAR2(20) NOT NULL,           -- 아이디
     name VARCHAR2(40) NOT NULL,       -- 이름
     email VARCHAR2(40),                  -- 이메일
     subject VARCHAR2(255) NOT NULL,    -- 제목
     content VARCHAR2(4000) NOT NULL,   -- 내용 

     ref NUMBER NOT NULL,                 -- 그룹번호
     lev NUMBER DEFAULT 0 NOT NULL,     -- 단계
     step NUMBER DEFAULT 0 NOT NULL,    -- 글순서
     pseq NUMBER DEFAULT 0 NOT NULL,    -- 원글번호
     reply NUMBER DEFAULT 0 NOT NULL,   -- 답변수

     hit NUMBER DEFAULT 0,              -- 조회수
     logtime DATE DEFAULT SYSDATE
 );
 
 CREATE SEQUENCE seq_board  NOCACHE NOCYCLE;
 

