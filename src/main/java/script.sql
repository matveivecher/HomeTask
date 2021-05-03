create table Task1
(
    id int auto_increment,
    name varchar(100) null,
    mail varchar(100) null,
    constraint Task1_pk
        primary key (id)
);
#1
select *from task1 where id%2!=0;
#2
select mail from task1 group by mail having count(mail)>1;-поиск дубля
#3
select distinct mail from task1;-поиск уникальных имен
#4
select avg(salary) from salarys;-среднее значение
#5
select *from salarys where salary>(select  avg(salary) from salarys);-поиск значения выше среднего
#6
create table workers
(
    id int not null
        primary key,
    name varchar(100) null,
    department_id int null,
    constraint workers_departments_id_fk
        foreign key (department_id) references departments (id)
);
create table departments
(
    id int not null
        primary key,
    name varchar(100) null
);

select departments.name from departments right join workers w on (departments.id = w.department_id);-поиск не пустых департаментов
#7
update salarys
set salary=
        CASE
            when salary=600 THEN 1600 else 900 end ;-обновление по условие
#8
SELECT CONCAT(name,salary) as name_and_salary from salarys;-при выводе новый столбец из нескольких строк
#9
ALTER Table salarys rename зарплаты-переименование таблиц