select * from tab;
create table ����(�̸� CHAR, ���� INT, ���� INT, ���� INT, ���� 
create table ���̺��(�ʵ�� �ʵ�Ÿ��, �ʵ�� �ʵ�Ÿ��,...)

create table ����(
x int,
y number,
z number(10,3));
select * from tab;
insert into ����(x,y,z) values(25,36,12.34567);
select * from ����;
insert into ����(x,y,z) values(25.34567,36.34567,12.34567);
insert into ����(x,y)  values(25.666,36.88888);
insert into ����(z,y,x)values(1,2,3);
insert into ���� values(25,36,1234567.3456);
insert into ���� values(25,36,12345678.3456); --���� ��ȿ���� ũ�� �Ѿ
select * from ����;


commit;


create table dbtest(
name varchar2(25),
age number,
height number(10,2),
logtime date);
select * from dbtest;
insert into dbtest(name,age,height,logtime) values('ȫ�浿',25,185.567,sysdate);
insert into dbtest(name,age,height,logtime) values('Hong',30,175.56,sysdate);
insert into dbtest(name,age) values('����',3);
insert into dbtest(name,height) values('ȫ�繫',168.89);
insert into dbtest values('��ȫ��',5,123.5,sysdate);
insert into dbtest(name) values ('����ȫ');
select name,age, height, logtime from dbtest;
select * from dbtest;
select * from dbtest where name = 'ȫ�浿';
select name,logtime from dbtest where name = 'ȫ�浿';
select * from dbtest where name ='Hong';
select * from dbtest where name like 'ȫ%';
select * from dbtest where name like '%ȫ%';
select * from dbtest where name like '%ȫ';
select * from dbtest where name like '_ȫ%';
select * from dbtest where name like '__ȫ%';

select * from dbtest where name like '%ȫ%' and age <= 20;
select * from dbtest where age is null;
select * from dbtest where age is not null;

select * from dbtest order by name asc; --��������(default)
select * from dbtest order by name desc; -- ��������

select * from dbtest where name like'%ȫ%' order by age asc;

update dbtest set age=0 where name = 'ȫ�繫'; 
update dbtest set age=0,height=0 where name='����ȫ'; -- and�� �ƴ϶� ,(��ǥ)���
update dbtest set logtime = sysdate where name ='����ȫ';
update dbtest set age = age+1 where name ='����ȫ';

delete dbtest where name like '%ȫ%';

rollback;
select * from dbtest;
select name,age,height,to_char(logtime,'YYYY.MM.DD') from dbtest;

create table student(
name  varchar2(15)  not  null, -- �̸�
value   varchar2(15), -- �й� or ���� or �μ�
code   number   -- 1�̸� �л�, 2�̸� ����, 3�̸� ������
);
select *from student;
delete student where name = '����';
select * from student where name like '%��%';

create sequence test_seq;
select TEST_SEQ.nextval from dual; --dual: �ƹ��͵� ���� ���� ���̺�
--�ѹ� ���� ��ȣ �ٽ� �ȳ��� (�ߺ� ���� ����)
create sequence num increment by 2 start with 1 maxvalue 9 cycle nocache;
select num.nextval from dual;
drop sequence num;
drop sequence test_seq;

create  table  friend(
seq number primary key, -- �⺻Ű(not null, unique)
name  varchar2(15)  not  null,
tel1  varchar2(5),
tel2  varchar2(5),
tel3  varchar2(5),
gender  number,   -- ���ڴ� 0, ���ڴ� 1
read  number default  0, -- �������� 0, ���� 1
movie  number default  0,
music  number default  0,
game  number default  0,
shopping  number default  0);
create sequence seq_friend nocycle nocache;
alter table friend rename column shopping to shop;

insert into friend(
    seq,name,tel1,tel2,tel3,gender,read,movie,music,game,shop) 
    values(seq_friend.nextval ,'ȫ�浿',010,123,1234,1,1,0,0,1,0);
    
select * from friend order by seq asc;
select * from friend order by seq desc;
update friend set name='�������',tel1='010',tel2='1',tel3='1',gender=1,read=1,movie=1,music=1,game=1,shop=1 where seq=9;
delete friend where seq=10;

create table member(
name varchar2(30) not null,
id varchar2(30) primary key, --�⺻Ű, unique, not null, ���Ἲ ���� ����
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

insert into member(name,id,pwd) values('ȫ�浿','hong','111');
select * from member;
select id from member where id='hong' and pwd='111';
select name from member where id='hong';
delete member where id='dbsgk';
commit;

desc member;
select * from member2;
create table member2(
name varchar2(30) not null,
id varchar2(30) primary key, --�⺻Ű, unique, not null, ���Ἲ ���� ����
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

select * from newzipcode where yubmyundong like '%����%';
delete newzipcode;
commit;

select count(zipcode) from newzipcode;

select * from newzipcode where sido like '% %' and sigungu like yubmyundong like '%����%';

select * from newzipcode where sido='����' and sigungu like '%����%' and roadname like '%��%';
select * from member order by logtime;
delete member where name='�Ƹ���';
desc member;
commit;

CREATE TABLE board(
     seq NUMBER NOT NULL,               -- �۹�ȣ
     id VARCHAR2(20) NOT NULL,           -- ���̵�
     name VARCHAR2(40) NOT NULL,       -- �̸�
     email VARCHAR2(40),                  -- �̸���
     subject VARCHAR2(255) NOT NULL,    -- ����
     content VARCHAR2(4000) NOT NULL,   -- ���� 

     ref NUMBER NOT NULL,                 -- �׷��ȣ
     lev NUMBER DEFAULT 0 NOT NULL,     -- �ܰ�
     step NUMBER DEFAULT 0 NOT NULL,    -- �ۼ���
     pseq NUMBER DEFAULT 0 NOT NULL,    -- ���۹�ȣ
     reply NUMBER DEFAULT 0 NOT NULL,   -- �亯��

     hit NUMBER DEFAULT 0,              -- ��ȸ��
     logtime DATE DEFAULT SYSDATE
 );
 
 CREATE SEQUENCE seq_board  NOCACHE NOCYCLE;
 

