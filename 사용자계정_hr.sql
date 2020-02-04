select * from tab;
select * from employees;
select * from all_users;
create user java IDENTIFIED by bit;
conn c##java/bit;
show user;

select * from employees;

select * from tab; --테이블 목록 확인
select * from user_sequences; --시퀀스 목록 확인
desc employees;
select employee_id, last_name, salary from employees;
select employee_id as 사원번호, last_name as "이  름", salary as "급  여" from employees;
select employee_id as 사원번호, last_name as "이  름", salary*12 as "연  봉" from employees;
select employee_id as 사원번호, first_name||' '||last_name as "이  름", (salary*12)||'달러' as "연  봉" from employees;
select * from employees;
select last_name||' is a '||job_id as "Employee Detail" from employees;
select distinct department_id from employees;
select first_name||' '||last_name as 사원명, '$'||salary as "월 급", department_id as 부서코드 from employees where department_id=90 and (salary<=2500 or salary>=3000);

select last_name as 이름,
       job_id as 업무ID,
       salary||'원' as "급 여"
from employees 
where (job_id='SA_REP' or job_id='AD_PRES') and salary>10000;
select distinct job_id from employees;
desc employees;
select employee_id as 사원번호, first_name||' '||last_name as "이  름", hire_date as 입사일 from employees where hire_date like '05%';
select employee_id, first_name, salary from employees where salary<=20000 and salary>=10000;

create table guestbook(
    seq number primary key,
    name varchar2(30),
    email varchar2(30),
    homepage varchar2(35),
    subject varchar2(500) not null,
    content varchar2(4000) not null,
    logtime date);
select * from guestbook;
create sequence seq_guestbook nocycle nocache;
insert into guestbook values(seq_guestbook.nextval,'1','1','1','1','1',sysdate);
commit;

select last_name as "이 름", salary*12 as "연 봉" from employees order by 2 desc;--day2 문제2
select last_name, department_id, hire_date
from employees
order by 2 desc, 3 asc;
select employee_id, last_name, department_id
from employees
where lower(last_name)='higgins';

select mod(10,3) from dual;
select round(35765.357,2) from dual;
select round(35765.357,0) from dual;
select round(35765.357,-3) from dual;

select trunc(35765.357,2)from dual;
select trunc(35765.357,0)from dual;
select trunc(35765.357,-3)from dual;

select concat('Hello',' World') from dual;

select length('King') from employees;
select instr('HelloWorld', 'o',-1) from dual; 

select * from employees;
--사원의 레코드를 검색하시오(concat, length)id name length
--      조건1) 이름과 성을 연결하시오(concat) 
--      조건2) 구해진 이름의 길이를 구하시오(length)
--      조건3) 성이 n으로 끝나는 사원(substr)
select employee_id, 
        concat(first_name, ' '||last_name) as name ,
        length(concat(first_name, ' '||last_name)) as lenght
from employees where (substr(last_name, length(last_name)))='n';
--substr(last_name, -1, 1) = 'n'; 끝에서 한개만 가져오겠다.

select last_day(sysdate) from dual;

select
to_char(last_day(sysdate), 'DD')-
to_char(sysdate, 'DD')
from dual; --날짜끼리의 연산은 -만 가능 ( +안됨 )

select last_day(sysdate)-sysdate from dual;
select sysdate+3 from dual;

select * from
(select rownum rn, tt.* from 
(select * from guestbook order by seq desc)tt --지금 걸러온 항목을 나는 tt라 하겠습니다.
)where rn>=1 and rn<=3;

select (select count(*) from guestbook);

select count(*) cn from guestbook

select * from
(select rownum rn, tt.* from
(select seq, name, email, homepage, subject, content, to_char(logtime, 'YYYY.MM.DD')as logtime from guestbook orderby seq desc
)where rn>=? and rn<=?;

select rownum,tt.* from (select * from guestbook order by seq desc)tt where rownum>=1 and rownum<=3;

select last_name, to_char(salary, 'L99,999.99')
from employees
where last_name='King';

select to_char(to_date('97-9-30', 'YY-MM-DD'), 'YYYY-MM-DD') from dual;
select to_char(to_date('97-9-30', 'RR-MM-DD'), 'YYYY-MM-DD') from dual;
--ex15) months_between(date1,date2) : 두 날짜 사이의 달 수
select round(months_between('95-10-21', '94-10-20'), 0) from dual; -- ← 자동 형변환 앞달이 더 커야함.
select rank(3000) within group(order by salary desc) "rank" from employees;
select employee_id, salary, rank() over(order by salary desc)"rank" from employees;

--[문제4]2005년 이전에 고용된 사원을 찾으시오
select last_name
, to_char(to_date(hire_date, 'DD-MM-RR')
, 'DD-MM"월"-YYYY')hire_date 
from employees where hire_date<to_date('2005', 'YYYY'); --day2 문제4번

select last_name
, to_char(hire_date, 'dd-mon-yyyy')
from employees where hire_date<'2005-01-01'; --day2 문제4번(쌤 답)
--[문제5]
select * from employees where commission_pct is null;
select COUNT(*) FROM employees where commission_pct is null;
select count(nvl(commission_pct,0)) from employees where commission_pct is null; --쌤 답

--[문제6] 급여가 10000미만이면 초급, 20000미만이면 중급 그 외면 고급을 출력하시오
-- (case 사용)
--      조건1) 컬럼명은  '구분'으로 하시오
--      조건2) 제목은 사원번호, 사원명, 구  분으로 표시하시오
--      조건3) 구분(오름차순)으로 정렬하고, 같으면 사원명(오름차순)으로 정렬하시오.

select employee_id 사원번호,last_name 사원명, case 
                    when salary<10000 then '초급' 
                    when salary<20000 then '중급' 
                    else '고급' 
              end "구  분"
from employees
order by 3,2;
--[문제7] 사원테이블에서 사원번호, 이름, 급여, 커미션, 연봉을 출력하시오
--        조건1) 연봉은 $ 표시와 세자리마다 콤마를 사용하시오
--        조건2) 연봉 = 급여 * 12 + (급여 * 12 * 커미션)
--        조건3) 커미션을 받지 않는 사원도 포함해서 출력하시오
--
select employee_id 사원번호
    , last_name 이름, salary 급여
    , nvl(commission_pct, 0) 커미션
    , to_char(salary*12+(salary*12*nvl(commission_pct, 0)), '$9,999,999.99')연봉
from employees
order by 5 desc;
--[문제8] 매니저가 없는 사원의 매니저id를, 1000번으로 표시
--        조건1) 제목은 사원번호, 이름, 매니저ID
--        조건2) 모든 사원을 표시하시오
select employee_id 사원번호
    , last_name 이름
    ,nvl(manager_id, 1000)매니저ID 
from employees;

--day3_ex1
select to_char(trunc(avg(salary),0), '99,999') as 사원급여평균 from employees;
desc employees;

select department_id as 부서코드, 
       to_char(round(avg(salary),0),'L99,999,999') as 평균급여
from employees
group by department_id  --group by 묶은 해당 컬럼(부서번호)만 조회가능.
having avg(salary)>=5000
order by department_id asc; 

select department_id, avg(salary) 
from employees
group by department_id;

select job_id, sum(salary) 급여합계
from employees
group by job_id
order by 급여합계 desc;--day3 문제1

select department_id, max(salary) as max_salary
from employees
group by department_id
having department_id in(10,20)
order by department_id;

select department_id, max(salary) as max_salary
from employees
where department_id in(10,20)
group by department_id
order by department_id;

--ex05
select employee_id, employees.department_id, department_name
from employees, departments
where employees.department_id = departments.department_id;

select e.employee_id, e.department_id, d.department_name
from employees e, departments d
where e.department_id= d.department_id;

select employee_id,department_id, department_name
from employees
join departments 
   USING (department_id);--inner join인가?
--문제2
select * from locations;
select * from departments;
select department_id, city
from departments d
join locations l using(location_id)
order by department_id;

select last_name, department_id,department_name
from employees
left join departments using(department_id)order by department_id;

--ex7
select last_name, department_id, department_name
from employees
right join departments using(department_id)order by department_id;

--inner join : 두 개의 컬럼이 일치 하는 경우
--     부서ID와 매니저ID가 같은 사원을 연결 하시오
--     (관련 테이블 : departments, employees)  : 32 레코드
--        last_name     department_id   manager_id
select e.last_name, d.department_id, d.manager_id
from employees e, departments d
where e.department_id=d.department_id and e.manager_id=d.manager_id; 

select last_name, department_id, manager_id
from employees
inner join departments using(department_id, manager_id);



select last_name, department_id, department_name
from employees
full join departments using(department_id)order by department_id;





select * from location2;
create table location2 as select * from locations;
alter table location2 rename column location_id to loc_id;

select department_id, city
from departments
join location2 on(location_id=loc_id);

