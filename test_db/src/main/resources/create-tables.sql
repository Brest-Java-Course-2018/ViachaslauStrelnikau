drop table if exists department;
create table department (
department_id int not null auto_increment,
department_name varchar(255) not null,
description varchar(255) null,
primary key(department_id)
)