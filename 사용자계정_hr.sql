select * from tab;
select * from employees;
select * from all_users;
create user java IDENTIFIED by bit;
conn c##java/bit;
show user;

select * from employees;

select * from tab; --���̺� ��� Ȯ��
select * from user_sequences; --������ ��� Ȯ��
desc employees;
select employee_id, last_name, salary from employees;
select employee_id as �����ȣ, last_name as "��  ��", salary as "��  ��" from employees;
select employee_id as �����ȣ, last_name as "��  ��", salary*12 as "��  ��" from employees;
select employee_id as �����ȣ, first_name||' '||last_name as "��  ��", (salary*12)||'�޷�' as "��  ��" from employees;
select * from employees;
select last_name||' is a '||job_id as "Employee Detail" from employees;
select distinct department_id from employees;
select first_name||' '||last_name as �����, '$'||salary as "�� ��", department_id as �μ��ڵ� from employees where department_id=90 and (salary<=2500 or salary>=3000);

select last_name as �̸�,
       job_id as ����ID,
       salary||'��' as "�� ��"
from employees 
where (job_id='SA_REP' or job_id='AD_PRES') and salary>10000;
select distinct job_id from employees;
desc employees;
select employee_id as �����ȣ, first_name||' '||last_name as "��  ��", hire_date as �Ի��� from employees where hire_date like '05%';
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

select last_name as "�� ��", salary*12 as "�� ��" from employees order by 2 desc;--day2 ����2
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
--����� ���ڵ带 �˻��Ͻÿ�(concat, length)id name length
--      ����1) �̸��� ���� �����Ͻÿ�(concat) 
--      ����2) ������ �̸��� ���̸� ���Ͻÿ�(length)
--      ����3) ���� n���� ������ ���(substr)
select employee_id, 
        concat(first_name, ' '||last_name) as name ,
        length(concat(first_name, ' '||last_name)) as lenght
from employees where (substr(last_name, length(last_name)))='n';
--substr(last_name, -1, 1) = 'n'; ������ �Ѱ��� �������ڴ�.

select last_day(sysdate) from dual;

select
to_char(last_day(sysdate), 'DD')-
to_char(sysdate, 'DD')
from dual; --��¥������ ������ -�� ���� ( +�ȵ� )

select last_day(sysdate)-sysdate from dual;
select sysdate+3 from dual;

select * from
(select rownum rn, tt.* from 
(select * from guestbook order by seq desc)tt --���� �ɷ��� �׸��� ���� tt�� �ϰڽ��ϴ�.
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
--ex15) months_between(date1,date2) : �� ��¥ ������ �� ��
select round(months_between('95-10-21', '94-10-20'), 0) from dual; -- �� �ڵ� ����ȯ �մ��� �� Ŀ����.
select rank(3000) within group(order by salary desc) "rank" from employees;
select employee_id, salary, rank() over(order by salary desc)"rank" from employees;

--[����4]2005�� ������ ���� ����� ã���ÿ�
select last_name
, to_char(to_date(hire_date, 'DD-MM-RR')
, 'DD-MM"��"-YYYY')hire_date 
from employees where hire_date<to_date('2005', 'YYYY'); --day2 ����4��

select last_name
, to_char(hire_date, 'dd-mon-yyyy')
from employees where hire_date<'2005-01-01'; --day2 ����4��(�� ��)
--[����5]
select * from employees where commission_pct is null;
select COUNT(*) FROM employees where commission_pct is null;
select count(nvl(commission_pct,0)) from employees where commission_pct is null; --�� ��

--[����6] �޿��� 10000�̸��̸� �ʱ�, 20000�̸��̸� �߱� �� �ܸ� ����� ����Ͻÿ�
-- (case ���)
--      ����1) �÷�����  '����'���� �Ͻÿ�
--      ����2) ������ �����ȣ, �����, ��  ������ ǥ���Ͻÿ�
--      ����3) ����(��������)���� �����ϰ�, ������ �����(��������)���� �����Ͻÿ�.

select employee_id �����ȣ,last_name �����, case 
                    when salary<10000 then '�ʱ�' 
                    when salary<20000 then '�߱�' 
                    else '���' 
              end "��  ��"
from employees
order by 3,2;
--[����7] ������̺��� �����ȣ, �̸�, �޿�, Ŀ�̼�, ������ ����Ͻÿ�
--        ����1) ������ $ ǥ�ÿ� ���ڸ����� �޸��� ����Ͻÿ�
--        ����2) ���� = �޿� * 12 + (�޿� * 12 * Ŀ�̼�)
--        ����3) Ŀ�̼��� ���� �ʴ� ����� �����ؼ� ����Ͻÿ�
--
select employee_id �����ȣ
    , last_name �̸�, salary �޿�
    , nvl(commission_pct, 0) Ŀ�̼�
    , to_char(salary*12+(salary*12*nvl(commission_pct, 0)), '$9,999,999.99')����
from employees
order by 5 desc;
--[����8] �Ŵ����� ���� ����� �Ŵ���id��, 1000������ ǥ��
--        ����1) ������ �����ȣ, �̸�, �Ŵ���ID
--        ����2) ��� ����� ǥ���Ͻÿ�
select employee_id �����ȣ
    , last_name �̸�
    ,nvl(manager_id, 1000)�Ŵ���ID 
from employees;

--day3_ex1
select to_char(trunc(avg(salary),0), '99,999') as ����޿���� from employees;
desc employees;

select department_id as �μ��ڵ�, 
       to_char(round(avg(salary),0),'L99,999,999') as ��ձ޿�
from employees
group by department_id  --group by ���� �ش� �÷�(�μ���ȣ)�� ��ȸ����.
having avg(salary)>=5000
order by department_id asc; 

select department_id, avg(salary) 
from employees
group by department_id;

select job_id, sum(salary) �޿��հ�
from employees
group by job_id
order by �޿��հ� desc;--day3 ����1

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
   USING (department_id);--inner join�ΰ�?
--����2
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

--inner join : �� ���� �÷��� ��ġ �ϴ� ���
--     �μ�ID�� �Ŵ���ID�� ���� ����� ���� �Ͻÿ�
--     (���� ���̺� : departments, employees)  : 32 ���ڵ�
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

