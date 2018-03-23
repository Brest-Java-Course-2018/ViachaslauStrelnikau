drop table if exists department;
create table department (
department_id int not null auto_increment,
department_name varchar(255) not null,
description varchar(255) null,
primary key(department_id)
);

drop table if exists employee;
create table employee (
employee_id int not null auto_increment,
employee_name varchar(255) not null,
employeeEmail varchar(255) null,
employeeSalary int not null,
department_id  int NOT NULL,
primary key(employee_id),
  CONSTRAINT FK_employeeid_departmentid
  FOREIGN KEY (department_id) REFERENCES department(department_id)
);
