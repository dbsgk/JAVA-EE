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
    tab; --���̺� ��� Ȯ��

SELECT
    *
FROM
    user_sequences; --������ ��� Ȯ��

DESC employees;

SELECT
    employee_id,
    last_name,
    salary
FROM
    employees;

SELECT
    employee_id   AS �����ȣ,
    last_name     AS "��  ��",
    salary        AS "��  ��"
FROM
    employees;

SELECT
    employee_id   AS �����ȣ,
    last_name     AS "��  ��",
    salary * 12 AS "��  ��"
FROM
    employees;

SELECT
    employee_id AS �����ȣ,
    first_name
    || ' '
    || last_name AS "��  ��",
    ( salary * 12 )
    || '�޷�' AS "��  ��"
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
    || last_name AS �����,
    '$' || salary AS "�� ��",
    department_id AS �μ��ڵ�
FROM
    employees
WHERE
    department_id = 90
    AND ( salary <= 2500
          OR salary >= 3000 );

SELECT
    last_name   AS �̸�,
    job_id      AS ����id,
    salary || '��' AS "�� ��"
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
    employee_id   AS �����ȣ,
    first_name
    || ' '
    || last_name AS "��  ��",
    hire_date     AS �Ի���
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
    last_name AS "�� ��",
    salary * 12 AS "�� ��"
FROM
    employees
ORDER BY
    2 DESC;--day2 ����2

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
--����� ���ڵ带 �˻��Ͻÿ�(concat, length)id name length
--      ����1) �̸��� ���� �����Ͻÿ�(concat) 
--      ����2) ������ �̸��� ���̸� ���Ͻÿ�(length)
--      ����3) ���� n���� ������ ���(substr)

SELECT
    employee_id,
    concat(first_name, ' ' || last_name) AS name,
    length(concat(first_name, ' ' || last_name)) AS lenght
FROM
    employees
WHERE
    ( substr(last_name, length(last_name)) ) = 'n';
--substr(last_name, -1, 1) = 'n'; ������ �Ѱ��� �������ڴ�.

SELECT
    last_day(sysdate)
FROM
    dual;

SELECT
    to_char(last_day(sysdate), 'DD') - to_char(sysdate, 'DD')
FROM
    dual; --��¥������ ������ -�� ���� ( +�ȵ� )

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
            ) tt --���� �ɷ��� �׸��� ���� tt�� �ϰڽ��ϴ�.
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
--ex15) months_between(date1,date2) : �� ��¥ ������ �� ��

SELECT
    round(months_between('95-10-21', '94-10-20'), 0)
FROM
    dual; -- �� �ڵ� ����ȯ �մ��� �� Ŀ����.

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

--[����4]2005�� ������ ���� ����� ã���ÿ�

SELECT
    last_name,
    to_char(to_date(hire_date, 'DD-MM-RR'), 'DD-MM"��"-YYYY') hire_date
FROM
    employees
WHERE
    hire_date < TO_DATE('2005', 'YYYY'); --day2 ����4��

SELECT
    last_name,
    to_char(hire_date, 'dd-mon-yyyy')
FROM
    employees
WHERE
    hire_date < '2005-01-01'; --day2 ����4��(�� ��)
--[����5]

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
    commission_pct IS NULL; --�� ��

--[����6] �޿��� 10000�̸��̸� �ʱ�, 20000�̸��̸� �߱� �� �ܸ� ����� ����Ͻÿ�
-- (case ���)
--      ����1) �÷�����  '����'���� �Ͻÿ�
--      ����2) ������ �����ȣ, �����, ��  ������ ǥ���Ͻÿ�
--      ����3) ����(��������)���� �����ϰ�, ������ �����(��������)���� �����Ͻÿ�.

SELECT
    employee_id   �����ȣ,
    last_name     �����,
    CASE
        WHEN salary < 10000 THEN
            '�ʱ�'
        WHEN salary < 20000 THEN
            '�߱�'
        ELSE
            '���'
    END "��  ��"
FROM
    employees
ORDER BY
    3,
    2;
--[����7] ������̺��� �����ȣ, �̸�, �޿�, Ŀ�̼�, ������ ����Ͻÿ�
--        ����1) ������ $ ǥ�ÿ� ���ڸ����� �޸��� ����Ͻÿ�
--        ����2) ���� = �޿� * 12 + (�޿� * 12 * Ŀ�̼�)
--        ����3) Ŀ�̼��� ���� �ʴ� ����� �����ؼ� ����Ͻÿ�
--

SELECT
    employee_id   �����ȣ,
    last_name     �̸�,
    salary        �޿�,
    nvl(commission_pct, 0) Ŀ�̼�,
    to_char(salary * 12 +(salary * 12 * nvl(commission_pct, 0)), '$9,999,999.99') ����
FROM
    employees
ORDER BY
    5 DESC;
--[����8] �Ŵ����� ���� ����� �Ŵ���id��, 1000������ ǥ��
--        ����1) ������ �����ȣ, �̸�, �Ŵ���ID
--        ����2) ��� ����� ǥ���Ͻÿ�

SELECT
    employee_id   �����ȣ,
    last_name     �̸�,
    nvl(manager_id, 1000) �Ŵ���id
FROM
    employees;

--day3_ex1

SELECT
    to_char(trunc(AVG(salary), 0), '99,999') AS ����޿����
FROM
    employees;

DESC employees;

SELECT
    department_id AS �μ��ڵ�,
    to_char(round(AVG(salary), 0), 'L99,999,999') AS ��ձ޿�
FROM
    employees
GROUP BY
    department_id  --group by ���� �ش� �÷�(�μ���ȣ)�� ��ȸ����.
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
    SUM(salary) �޿��հ�
FROM
    employees
GROUP BY
    job_id
ORDER BY
    �޿��հ� DESC;--day3 ����1

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
    JOIN departments USING ( department_id );--inner join�ΰ�?
--����2

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

--inner join : �� ���� �÷��� ��ġ �ϴ� ���
--     �μ�ID�� �Ŵ���ID�� ���� ����� ���� �Ͻÿ�
--     (���� ���̺� : departments, employees)  : 32 ���ڵ�
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
    e.employee_id   AS �����ȣ,
    e.last_name     AS ����̸�,
    m.last_name     AS ������
FROM
    employees   e,
    employees   m
WHERE
    m.employee_id = e.manager_id
ORDER BY
    m.employee_id; 

--ex11) self ���� : �ڱ��ڽ��� ���̺�� �����ϴ� ��� ����� �����ڸ� �����Ͻÿ�
--
--�����ȣ   ����̸�      ������
------------------------------------
--   101      Kochhar      King  

SELECT
    e.employee_id   �����ȣ,
    e.last_name     ����̸�,
    m.last_name     ������
FROM
    employees   e,
    employees   m
WHERE
    e.manager_id = m.employee_id
ORDER BY
    e.employee_id;

SELECT
    e.employee_id   AS �����ȣ,
    e.last_name     AS ����̸�,
    m.last_name     AS ������
FROM
    employees   e
    JOIN employees   m ON ( m.employee_id = e.manager_id );  
    

--[����3] ��ġID, �μ�ID�� �����ؼ� ����̸�, ����, �μ��̸��� ����Ͻÿ�
--        (���� ���̺� : EMPLOYEES, LOCATIONS2, DEPARTMENTS)
--����1 : ����̸�, ����, �μ��̸����� ������ ǥ���Ͻÿ�	
--����2 : Seattle �Ǵ� Oxford ���� �ٹ��ϴ� ���
--����3 : ���� ������ �������� �����Ͻÿ� 
--
--����̸�      ��    ��     �μ��̸�
-----------------------------------------------
--Hall            Oxford       Sales

DESC departments;

SELECT
    e.last_name         ����̸�,
    l.city              ����,
    d.department_name   �μ��̸�
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

--[����4] �μ�ID, ��ġID, ����ID�� �����ؼ� ������ ���� �ϼ��Ͻÿ�
--(���� ���̺� : EMPLOYEES, LOCATIONS2, DEPARTMENTS, COUNTRIES)
--
--����1 : �����ȣ, ����̸�, �μ��̸�, ����(city), �����ּ�(country_id), �����̸�(country_name)���� ������ ǥ���Ͻÿ�
--����2 : �����ּҿ� Ch �Ǵ� Sh �Ǵ� Rd�� ���ԵǾ� �ִ� �����͸� ǥ���Ͻÿ�
--����3 : �����̸�, ���ú��� �������� �����Ͻÿ�
--����4 : ��� ����� �����Ѵ�

SELECT
    e.employee_id       �����ȣ,
    e.last_name         ����̸�,
    d.department_name   �μ��̸�,
    l.city              ����,
    l.street_address    �����ּ�,
    c.country_name      �����̸�
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
 �� ���̺� ������ ����(���ڵ�� �Ȱ�����.)
--ex1) union 
--employee_id, last_name�� ���� ��� �ߺ����� �Ͻÿ�  �� 110 ���ڵ�
select employee_id, last_name from employees_role
union all
select employee_id, last_name from employees;

select salary  from employees where department_id=10
union all
select salary  from employees where department_id=30 order by 1;

--[����1] employees�� employees_role���� ���ڵ��� �������� ���Ͻÿ�
--����1) ����̸�, ����ID, �μ�ID�� ǥ���Ͻÿ�
--����2) employees ������ �μ�ID�� 10�λ���� 
--       employees_role������ ����ID�� IT_PROG�� �˻�
--����3) �ߺ��Ǵ� ���ڵ�� ����
select last_name ����̸�,
     job_id �����̸�,
     department_id �μ�ID,
from employees
where department_id=10
union
select last_name ����̸�,
     job_id �����̸�,
     department_id �μ�ID,
from employees_role
where job_id='IT_PROG';

select department_name from departments
where department_id = (select department_id 
from employees 
where first_name='Neena');
desc employees;
select * from employees;
--ex2) Neena����� �μ����� Neena������� �޿��� ���� �޴� ������� ���Ͻÿ� 
--                  (90)          (17000)
--last_name    department_id    salary
-----------------------------------------
select last_name, department_id, salary
from employees
where department_id = (select department_id from employees 
                        where first_name='Neena')
and salary > (select salary from employees where first_name='Neena');

--[����1] �����޿��� �޴� ������� �̸�(first_name)�� �޿�(salary)�� ���Ͻÿ�
select first_name �̸�,
    salary �޿�
from employees
where salary = (select min(salary) from employees) ;

select department_name, �޿��հ�
from departments
left join (select department_id, sum(salary) �޿��հ�
from employees
group by department_id) using(department_id);
--[����2] �μ��� �޿� �հ� �� �ִ�޿��� �޴� �μ��� �μ���(department_id)��
--�޿��հ踦 ���Ͻÿ�(group by)
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
group by department_id;--�ִ�޿��հ谪

select department_name,sum(salary)
from employees
join departments using(department_id)
group by department_name
having sum(salary) =(select max(sum(salary)) from employees group by department_id);

--ex3) Austin�� ���� �μ��̸鼭 ���� �޿��� �޴� �������
--      �̸�, �μ���, �޿��� ���Ͻÿ�(60�μ�, 4800�޷�)
select last_name, department_name, salary
from employees
left join departments using(department_id)
where department_id = (select department_id 
from employees 
where last_name='Austin')
and
salary = (select salary from employees where last_name='Austin');

--ex4) 'ST_MAN' ���޺��� �޿��� ���� 'IT_PROG' ���� �������� ��ȸ�Ͻÿ�
--last_name   job_id   salary
-------------------------------
select last_name, job_id, salary
from employees
where job_id = 'IT_PROG'
and
salary >any(select salary from employees where job_id='ST_MAN');

--[����3] 'IT_PROG' ���� �� ���� ���� �޴� ����� �޿����� �� ���� �޿��� �޴� 'FI_ACCOUNT' �Ǵ� 'SA_REP' ���� �������� ��ȸ�Ͻÿ�
--      ����1) �޿� ������ �������� �����Ͻÿ�
--      ����2) �޿��� ���ڸ� ���� �޸�(,) ��� ȭ����� '������ ���̽ÿ�
--      ����3) Ÿ��Ʋ�� �����, ����ID, �޿��� ǥ���Ͻÿ�
--
--�����    ����ID    �޿�
----------------------------
select last_name �����, job_id ����ID, to_char(salary,'L99,999,999') �޿�
from employees join departments using(department_id)
where job_id in('FI_ACCOUNT','SA_REP')
and salary>(SELECT max(salary) FROM employees
join departments using(department_id)
where job_id='IT_PROG') order by 3 desc;-- �� ��

select last_name �����, job_id ����ID, to_char(salary,'99,999,999')||'��' �޿�
from employees
where job_id in('FI_ACCOUNT','SA_REP') 
and salary >all (select salary from employees where job_id='IT_PROG')-->all (4200-9000)
order by 3 desc;
--ex5) 'IT_PROG'�� ���� �޿��� �޴� ������� �̸�, ����ID, �޿��� ���� ���Ͻÿ�
select last_name, job_id, salary
from employees
where salary in(select salary from employees where job_id='IT_PROG');

--ex6) ��ü������ ���� �����ڿ� ������ �����ϴ� ǥ�ø� �Ͻÿ�
--(in, not in�̿�)
--�����ȣ      �̸�       ����
---------------------------------------
--100          King      ������
--
--�� ���1 (in ������)
select employee_id as �����ȣ, last_name as �̸�,
case
    when employee_id in(select manager_id from employees) 
        then '������'
        else '����'
    end as ����
from employees
order by 3,1;

--�� ���2 (union, in, not in ������)
select employee_id as �����ȣ, last_name as �̸�, '������' as ����
from employees
where employee_id in(select manager_id from employees)
union
select employee_id as �����ȣ, last_name as �̸�, '����' as ����
from employees
where employee_id not in(select manager_id from employees where manager_id is not null)
order by 3,1; 


--�� ���3 (��������̿�)
-- �������� ������ �а� �ش簪�� ������������ �����Ͽ� ������������� �����ϸ� true�� ��ȯ

select employee_id as �����ȣ, last_name as �̸�, '������' as ����
from employees e
where exists(select null from employees where e.employee_id=manager_id)
union
select employee_id as �����ȣ, last_name as �̸�, '����' as ����
from employees e
where not exists(select null from employees where e.employee_id=manager_id)
order by 3,1;
desc jobs;
--[����4] �ڱ� ����id(job_id)�� ��ձ޿��� �޴� �������� ��ȸ�Ͻÿ�
--����1) ��ձ޿��� 100���� ���� �����ϰ� �޿��� ���ڸ����� �޸�, $ǥ��
--����2) ����̸�(last_name), ����id(job_id), ����(job_title), �޿�(salary) �� ǥ���Ͻÿ�
--����3) �޿������� �������� �����Ͻÿ�
select last_name ����̸�, job_id ����ID, job_title ����, to_char(trunc(salary,-3), '$999,999,999') �޿�
from employees
join jobs using(job_id)
where to_char(trunc(salary,-3), '$999,999,999') =
any(select to_char(trunc(avg(salary),-3), '$999,999,999')
from employees
group by job_id);

select last_name ����̸�, job_id ����ID, job_title ����, to_char(trunc(salary,-3), '$999,999,999') �޿�
from employees
join jobs using(job_id)
where (job_id, salary) in
(select job_id, trunc(avg(salary),-3)
from employees
group by job_id);








