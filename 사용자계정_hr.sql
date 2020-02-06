SELECT
    *
FROM
    tab;

SELECT
    *
FROM
    employees;

SELECT
    *
FROM
    all_users;

CREATE USER java IDENTIFIED BY bit;

CONN c##java
/

bit;

SHOW USER;

SELECT
    *
FROM
    employees;

SELECT
    *
FROM
    tab; --테이블 목록 확인

SELECT
    *
FROM
    user_sequences; --시퀀스 목록 확인

DESC employees;

SELECT
    employee_id,
    last_name,
    salary
FROM
    employees;

SELECT
    employee_id   AS 사원번호,
    last_name     AS "이  름",
    salary        AS "급  여"
FROM
    employees;

SELECT
    employee_id   AS 사원번호,
    last_name     AS "이  름",
    salary * 12 AS "연  봉"
FROM
    employees;

SELECT
    employee_id AS 사원번호,
    first_name
    || ' '
    || last_name AS "이  름",
    ( salary * 12 )
    || '달러' AS "연  봉"
FROM
    employees;

SELECT
    *
FROM
    employees;

SELECT
    last_name
    || ' is a '
    || job_id AS "Employee Detail"
FROM
    employees;

SELECT DISTINCT
    department_id
FROM
    employees;

SELECT
    first_name
    || ' '
    || last_name AS 사원명,
    '$' || salary AS "월 급",
    department_id AS 부서코드
FROM
    employees
WHERE
    department_id = 90
    AND ( salary <= 2500
          OR salary >= 3000 );

SELECT
    last_name   AS 이름,
    job_id      AS 업무id,
    salary || '원' AS "급 여"
FROM
    employees
WHERE
    ( job_id = 'SA_REP'
      OR job_id = 'AD_PRES' )
    AND salary > 10000;

SELECT DISTINCT
    job_id
FROM
    employees;

DESC employees;

SELECT
    employee_id   AS 사원번호,
    first_name
    || ' '
    || last_name AS "이  름",
    hire_date     AS 입사일
FROM
    employees
WHERE
    hire_date LIKE '05%';

SELECT
    employee_id,
    first_name,
    salary
FROM
    employees
WHERE
    salary <= 20000
    AND salary >= 10000;

CREATE TABLE guestbook (
    seq        NUMBER PRIMARY KEY,
    name       VARCHAR2(30),
    email      VARCHAR2(30),
    homepage   VARCHAR2(35),
    subject    VARCHAR2(500) NOT NULL,
    content    VARCHAR2(4000) NOT NULL,
    logtime    DATE
);

SELECT
    *
FROM
    guestbook;

CREATE SEQUENCE seq_guestbook NOCYCLE NOCACHE;

INSERT INTO guestbook VALUES (
    seq_guestbook.NEXTVAL,
    '1',
    '1',
    '1',
    '1',
    '1',
    sysdate
);

COMMIT;

SELECT
    last_name AS "이 름",
    salary * 12 AS "연 봉"
FROM
    employees
ORDER BY
    2 DESC;--day2 문제2

SELECT
    last_name,
    department_id,
    hire_date
FROM
    employees
ORDER BY
    2 DESC,
    3 ASC;

SELECT
    employee_id,
    last_name,
    department_id
FROM
    employees
WHERE
    lower(last_name) = 'higgins';

SELECT
    mod(10, 3)
FROM
    dual;

SELECT
    round(35765.357, 2)
FROM
    dual;

SELECT
    round(35765.357, 0)
FROM
    dual;

SELECT
    round(35765.357, - 3)
FROM
    dual;

SELECT
    trunc(35765.357, 2)
FROM
    dual;

SELECT
    trunc(35765.357, 0)
FROM
    dual;

SELECT
    trunc(35765.357, - 3)
FROM
    dual;

SELECT
    concat('Hello', ' World')
FROM
    dual;

SELECT
    length('King')
FROM
    employees;

SELECT
    instr('HelloWorld', 'o', - 1)
FROM
    dual;

SELECT
    *
FROM
    employees;
--사원의 레코드를 검색하시오(concat, length)id name length
--      조건1) 이름과 성을 연결하시오(concat) 
--      조건2) 구해진 이름의 길이를 구하시오(length)
--      조건3) 성이 n으로 끝나는 사원(substr)

SELECT
    employee_id,
    concat(first_name, ' ' || last_name) AS name,
    length(concat(first_name, ' ' || last_name)) AS lenght
FROM
    employees
WHERE
    ( substr(last_name, length(last_name)) ) = 'n';
--substr(last_name, -1, 1) = 'n'; 끝에서 한개만 가져오겠다.

SELECT
    last_day(sysdate)
FROM
    dual;

SELECT
    to_char(last_day(sysdate), 'DD') - to_char(sysdate, 'DD')
FROM
    dual; --날짜끼리의 연산은 -만 가능 ( +안됨 )

SELECT
    last_day(sysdate) - sysdate
FROM
    dual;

SELECT
    sysdate + 3
FROM
    dual;

SELECT
    *
FROM
    (
        SELECT
            ROWNUM rn,
            tt.*
        FROM
            (
                SELECT
                    *
                FROM
                    guestbook
                ORDER BY
                    seq DESC
            ) tt --지금 걸러온 항목을 나는 tt라 하겠습니다.
    )
WHERE
    rn >= 1
    AND rn <= 3;

SELECT
    (
        SELECT
            COUNT(*)
        FROM
            guestbook
    );

select count(*) cn from guestbook

select * from
(select rownum rn, tt.* from
(select seq, name, email, homepage, subject, content, to_char(logtime, 'YYYY.MM.DD')as logtime from guestbook orderby seq DESC )
WHERE
    rn >= ?
    AND rn <=
    ?;

SELECT
    ROWNUM,
    tt.*
FROM
    (
        SELECT
            *
        FROM
            guestbook
        ORDER BY
            seq DESC
    ) tt
WHERE
    ROWNUM >= 1
    AND ROWNUM <= 3;

SELECT
    last_name,
    to_char(salary, 'L99,999.99')
FROM
    employees
WHERE
    last_name = 'King';

SELECT
    to_char(TO_DATE('97-9-30', 'YY-MM-DD'), 'YYYY-MM-DD')
FROM
    dual;

SELECT
    to_char(TO_DATE('97-9-30', 'RR-MM-DD'), 'YYYY-MM-DD')
FROM
    dual;
--ex15) months_between(date1,date2) : 두 날짜 사이의 달 수

SELECT
    round(months_between('95-10-21', '94-10-20'), 0)
FROM
    dual; -- ← 자동 형변환 앞달이 더 커야함.

SELECT
    RANK(3000) WITHIN GROUP(ORDER BY salary DESC) "rank"
FROM
    employees;

SELECT
    employee_id,
    salary,
    RANK() OVER(
        ORDER BY
            salary DESC
    ) "rank"
FROM
    employees;

--[문제4]2005년 이전에 고용된 사원을 찾으시오

SELECT
    last_name,
    to_char(to_date(hire_date, 'DD-MM-RR'), 'DD-MM"월"-YYYY') hire_date
FROM
    employees
WHERE
    hire_date < TO_DATE('2005', 'YYYY'); --day2 문제4번

SELECT
    last_name,
    to_char(hire_date, 'dd-mon-yyyy')
FROM
    employees
WHERE
    hire_date < '2005-01-01'; --day2 문제4번(쌤 답)
--[문제5]

SELECT
    *
FROM
    employees
WHERE
    commission_pct IS NULL;

SELECT
    COUNT(*)
FROM
    employees
WHERE
    commission_pct IS NULL;

SELECT
    COUNT(nvl(commission_pct, 0))
FROM
    employees
WHERE
    commission_pct IS NULL; --쌤 답

--[문제6] 급여가 10000미만이면 초급, 20000미만이면 중급 그 외면 고급을 출력하시오
-- (case 사용)
--      조건1) 컬럼명은  '구분'으로 하시오
--      조건2) 제목은 사원번호, 사원명, 구  분으로 표시하시오
--      조건3) 구분(오름차순)으로 정렬하고, 같으면 사원명(오름차순)으로 정렬하시오.

SELECT
    employee_id   사원번호,
    last_name     사원명,
    CASE
        WHEN salary < 10000 THEN
            '초급'
        WHEN salary < 20000 THEN
            '중급'
        ELSE
            '고급'
    END "구  분"
FROM
    employees
ORDER BY
    3,
    2;
--[문제7] 사원테이블에서 사원번호, 이름, 급여, 커미션, 연봉을 출력하시오
--        조건1) 연봉은 $ 표시와 세자리마다 콤마를 사용하시오
--        조건2) 연봉 = 급여 * 12 + (급여 * 12 * 커미션)
--        조건3) 커미션을 받지 않는 사원도 포함해서 출력하시오
--

SELECT
    employee_id   사원번호,
    last_name     이름,
    salary        급여,
    nvl(commission_pct, 0) 커미션,
    to_char(salary * 12 +(salary * 12 * nvl(commission_pct, 0)), '$9,999,999.99') 연봉
FROM
    employees
ORDER BY
    5 DESC;
--[문제8] 매니저가 없는 사원의 매니저id를, 1000번으로 표시
--        조건1) 제목은 사원번호, 이름, 매니저ID
--        조건2) 모든 사원을 표시하시오

SELECT
    employee_id   사원번호,
    last_name     이름,
    nvl(manager_id, 1000) 매니저id
FROM
    employees;

--day3_ex1

SELECT
    to_char(trunc(AVG(salary), 0), '99,999') AS 사원급여평균
FROM
    employees;

DESC employees;

SELECT
    department_id AS 부서코드,
    to_char(round(AVG(salary), 0), 'L99,999,999') AS 평균급여
FROM
    employees
GROUP BY
    department_id  --group by 묶은 해당 컬럼(부서번호)만 조회가능.
HAVING
    AVG(salary) >= 5000
ORDER BY
    department_id ASC;

SELECT
    department_id,
    AVG(salary)
FROM
    employees
GROUP BY
    department_id;

SELECT
    job_id,
    SUM(salary) 급여합계
FROM
    employees
GROUP BY
    job_id
ORDER BY
    급여합계 DESC;--day3 문제1

SELECT
    department_id,
    MAX(salary) AS max_salary
FROM
    employees
GROUP BY
    department_id
HAVING
    department_id IN (
        10,
        20
    )
ORDER BY
    department_id;

SELECT
    department_id,
    MAX(salary) AS max_salary
FROM
    employees
WHERE
    department_id IN (
        10,
        20
    )
GROUP BY
    department_id
ORDER BY
    department_id;

--ex05

SELECT
    employee_id,
    employees.department_id,
    department_name
FROM
    employees,
    departments
WHERE
    employees.department_id = departments.department_id;

SELECT
    e.employee_id,
    e.department_id,
    d.department_name
FROM
    employees     e,
    departments   d
WHERE
    e.department_id = d.department_id;

SELECT
    employee_id,
    department_id,
    department_name
FROM
    employees
    JOIN departments USING ( department_id );--inner join인가?
--문제2

SELECT
    *
FROM
    locations;

SELECT
    *
FROM
    departments;

SELECT
    department_id,
    city
FROM
    departments   d
    JOIN locations     l USING ( location_id )
ORDER BY
    department_id;

SELECT
    last_name,
    department_id,
    department_name
FROM
    employees
    LEFT JOIN departments USING ( department_id )
ORDER BY
    department_id;

--ex7

SELECT
    last_name,
    department_id,
    department_name
FROM
    employees right
    JOIN departments USING ( department_id )
ORDER BY
    department_id;

--inner join : 두 개의 컬럼이 일치 하는 경우
--     부서ID와 매니저ID가 같은 사원을 연결 하시오
--     (관련 테이블 : departments, employees)  : 32 레코드
--        last_name     department_id   manager_id

SELECT
    e.last_name,
    d.department_id,
    d.manager_id
FROM
    employees     e,
    departments   d
WHERE
    e.department_id = d.department_id
    AND e.manager_id = d.manager_id;

SELECT
    last_name,
    department_id,
    manager_id
FROM
    employees
    INNER JOIN departments USING ( department_id,
                                   manager_id );

SELECT
    last_name,
    department_id,
    department_name
FROM
    employees full
    JOIN departments USING ( department_id )
ORDER BY
    department_id;

SELECT
    *
FROM
    location2;

CREATE TABLE location2
    AS
        SELECT
            *
        FROM
            locations;

ALTER TABLE location2 RENAME COLUMN location_id TO loc_id;

SELECT
    department_id,
    city
FROM
    departments
    JOIN location2 ON ( location_id = loc_id );

SELECT
    e.employee_id   AS 사원번호,
    e.last_name     AS 사원이름,
    m.last_name     AS 관리자
FROM
    employees   e,
    employees   m
WHERE
    m.employee_id = e.manager_id
ORDER BY
    m.employee_id; 

--ex11) self 조인 : 자기자신의 테이블과 조인하는 경우 사원과 관리자를 연결하시오
--
--사원번호   사원이름      관리자
------------------------------------
--   101      Kochhar      King  

SELECT
    e.employee_id   사원번호,
    e.last_name     사원이름,
    m.last_name     관리자
FROM
    employees   e,
    employees   m
WHERE
    e.manager_id = m.employee_id
ORDER BY
    e.employee_id;

SELECT
    e.employee_id   AS 사원번호,
    e.last_name     AS 사원이름,
    m.last_name     AS 관리자
FROM
    employees   e
    JOIN employees   m ON ( m.employee_id = e.manager_id );  
    

--[문제3] 위치ID, 부서ID을 연결해서 사원이름, 도시, 부서이름을 출력하시오
--        (관련 테이블 : EMPLOYEES, LOCATIONS2, DEPARTMENTS)
--조건1 : 사원이름, 도시, 부서이름으로 제목을 표시하시오	
--조건2 : Seattle 또는 Oxford 에서 근무하는 사원
--조건3 : 도시 순으로 오름차순 정렬하시오 
--
--사원이름      도    시     부서이름
-----------------------------------------------
--Hall            Oxford       Sales

DESC departments;

SELECT
    e.last_name         사원이름,
    l.city              도시,
    d.department_name   부서이름
FROM
    employees     e
    JOIN departments   d USING ( department_id )
    JOIN location2     l ON ( loc_id = location_id )
WHERE
    city IN (
        'Seattle',
        'Oxford'
    )
ORDER BY
    l.city;

--[문제4] 부서ID, 위치ID, 국가ID를 연결해서 다음과 같이 완성하시오
--(관련 테이블 : EMPLOYEES, LOCATIONS2, DEPARTMENTS, COUNTRIES)
--
--조건1 : 사원번호, 사원이름, 부서이름, 도시(city), 도시주소(country_id), 나라이름(country_name)으로 제목을 표시하시오
--조건2 : 도시주소에 Ch 또는 Sh 또는 Rd가 포함되어 있는 데이터만 표시하시오
--조건3 : 나라이름, 도시별로 오름차순 정렬하시오
--조건4 : 모든 사원을 포함한다

SELECT
    e.employee_id       사원번호,
    e.last_name         사원이름,
    d.department_name   부서이름,
    l.city              도시,
    l.street_address    도로주소,
    c.country_name      나라이름
FROM
    employees     e
    left JOIN departments   d USING ( department_id )
    JOIN location2     l ON ( loc_id = location_id )
    JOIN countries     c USING ( country_id )
WHERE
    l.street_address LIKE '%Ch%'
    OR l.street_address LIKE '%Sh%'
    OR l.street_address LIKE '%Rd%'
ORDER BY
    c.country_name,
    l.city; --23
create TABLE sellings (car_id NUMBER, employee_id NUMBER, created_at date, price number);
select * from sellings;
insert into sellings values(352, 2455, '2016/08/16', 3700);
insert into sellings values(352, 2499, '2016/11/26', 6200);
insert into sellings values(352, 3010, '2016/11/06', 10800);
insert into sellings values(306, 2955, '2016/12/25', 1000);
select sum(price)
from sellings
where created_at like '%16/11%';

create table employees_role as select * from employees where 1=0;
select * from employees_role;
commit;
 ← 테이블 구조만 복사(레코드는 안가져옴.)
--ex1) union 
--employee_id, last_name이 같을 경우 중복제거 하시오  → 110 레코드
select employee_id, last_name from employees_role
union all
select employee_id, last_name from employees;

select salary  from employees where department_id=10
union all
select salary  from employees where department_id=30 order by 1;

--[문제1] employees와 employees_role에서 레코드의 사원명단을 구하시오
--조건1) 사원이름, 업무ID, 부서ID을 표시하시오
--조건2) employees 에서는 부서ID가 10인사원만 
--       employees_role에서는 업무ID가 IT_PROG만 검색
--조건3) 중복되는 레코드는 제거
select last_name 사원이름,
     job_id 업무이름,
     department_id 부서ID,
from employees
where department_id=10
union
select last_name 사원이름,
     job_id 업무이름,
     department_id 부서ID,
from employees_role
where job_id='IT_PROG';

select department_name from departments
where department_id = (select department_id 
from employees 
where first_name='Neena');
desc employees;
select * from employees;
--ex2) Neena사원의 부서에서 Neena사원보다 급여를 많이 받는 사원들을 구하시오 
--                  (90)          (17000)
--last_name    department_id    salary
-----------------------------------------
select last_name, department_id, salary
from employees
where department_id = (select department_id from employees 
                        where first_name='Neena')
and salary > (select salary from employees where first_name='Neena');

--[문제1] 최저급여를 받는 사원들의 이름(first_name)과 급여(salary)를 구하시오
select first_name 이름,
    salary 급여
from employees
where salary = (select min(salary) from employees) ;

select department_name, 급여합계
from departments
left join (select department_id, sum(salary) 급여합계
from employees
group by department_id) using(department_id);
--[문제2] 부서별 급여 합계 중 최대급여를 받는 부서의 부서명(department_id)과
--급여합계를 구하시오(group by)
--DEPARTMENT_NAME    SUM(SALARY)
------------------------   ----------------
--Sales                     304500
--select department_name, sum(salary)
--from departments
--where sum(salary)= (select sum(salary) from departments);
select * from departments;
select * from employees where departments;
select sum(salary),department_id from departments;
select sum(salary)
from(select department_name, salary from departments left join employees using(department_id));

select max()
from employees
group by department_id;--최대급여합계값

select department_name,sum(salary)
from employees
join departments using(department_id)
group by department_name
having sum(salary) =(select max(sum(salary)) from employees group by department_id);

--ex3) Austin과 같은 부서이면서 같은 급여를 받는 사원들의
--      이름, 부서명, 급여를 구하시오(60부서, 4800달러)
select last_name, department_name, salary
from employees
left join departments using(department_id)
where department_id = (select department_id 
from employees 
where last_name='Austin')
and
salary = (select salary from employees where last_name='Austin');

--ex4) 'ST_MAN' 직급보다 급여가 많은 'IT_PROG' 직급 직원들을 조회하시오
--last_name   job_id   salary
-------------------------------
select last_name, job_id, salary
from employees
where job_id = 'IT_PROG'
and
salary >any(select salary from employees where job_id='ST_MAN');

--[문제3] 'IT_PROG' 직급 중 가장 많이 받는 사원의 급여보다 더 많은 급여를 받는 'FI_ACCOUNT' 또는 'SA_REP' 직급 직원들을 조회하시오
--      조건1) 급여 순으로 내림차순 정렬하시오
--      조건2) 급여는 세자리 마다 콤마(,) 찍고 화폐단위 '원’을 붙이시오
--      조건3) 타이틀은 사원명, 업무ID, 급여로 표시하시오
--
--사원명    업무ID    급여
----------------------------
select last_name 사원명, job_id 업무ID, to_char(salary,'L99,999,999') 급여
from employees join departments using(department_id)
where job_id in('FI_ACCOUNT','SA_REP')
and salary>(SELECT max(salary) FROM employees
join departments using(department_id)
where job_id='IT_PROG') order by 3 desc;-- 내 답

select last_name 사원명, job_id 업무ID, to_char(salary,'99,999,999')||'원' 급여
from employees
where job_id in('FI_ACCOUNT','SA_REP') 
and salary >all (select salary from employees where job_id='IT_PROG')-->all (4200-9000)
order by 3 desc;
--ex5) 'IT_PROG'와 같은 급여를 받는 사원들의 이름, 업무ID, 급여를 전부 구하시오
select last_name, job_id, salary
from employees
where salary in(select salary from employees where job_id='IT_PROG');

--ex6) 전체직원에 대한 관리자와 직원을 구분하는 표시를 하시오
--(in, not in이용)
--사원번호      이름       구분
---------------------------------------
--100          King      관리자
--
--★ 방법1 (in 연산자)
select employee_id as 사원번호, last_name as 이름,
case
    when employee_id in(select manager_id from employees) 
        then '관리자'
        else '직원'
    end as 구분
from employees
order by 3,1;

--★ 방법2 (union, in, not in 연산자)
select employee_id as 사원번호, last_name as 이름, '관리자' as 구분
from employees
where employee_id in(select manager_id from employees)
union
select employee_id as 사원번호, last_name as 이름, '직원' as 구분
from employees
where employee_id not in(select manager_id from employees where manager_id is not null)
order by 3,1; 


--★ 방법3 (상관쿼리이용)
-- 메인쿼리 한행을 읽고 해당값을 서브쿼리에서 참조하여 서브쿼리결과에 존재하면 true를 반환

select employee_id as 사원번호, last_name as 이름, '관리자' as 구분
from employees e
where exists(select null from employees where e.employee_id=manager_id)
union
select employee_id as 사원번호, last_name as 이름, '직원' as 구분
from employees e
where not exists(select null from employees where e.employee_id=manager_id)
order by 3,1;
desc jobs;
--[문제4] 자기 업무id(job_id)의 평균급여를 받는 직원들을 조회하시오
--조건1) 평균급여는 100단위 이하 절삭하고 급여는 세자리마다 콤마, $표시
--조건2) 사원이름(last_name), 업무id(job_id), 직무(job_title), 급여(salary) 로 표시하시오
--조건3) 급여순으로 오름차순 정렬하시오
select last_name 사원이름, job_id 업무ID, job_title 직무, to_char(trunc(salary,-3), '$999,999,999') 급여
from employees
join jobs using(job_id)
where to_char(trunc(salary,-3), '$999,999,999') =
any(select to_char(trunc(avg(salary),-3), '$999,999,999')
from employees
group by job_id);

select last_name 사원이름, job_id 업무ID, job_title 직무, to_char(trunc(salary,-3), '$999,999,999') 급여
from employees
join jobs using(job_id)
where (job_id, salary) in
(select job_id, trunc(avg(salary),-3)
from employees
group by job_id);








