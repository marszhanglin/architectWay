drop database if exists users; 
create table users(
	id int primary key auto_increment,  
	name varchar(40),
	password varchar(40),
	email varchar(60),
	birthday date
)character set utf8 collate utf8_general_ci;/** 编码格式：character set utf8 ;排序规则：collate utf8_general_ci   */

insert into users(name,password,email,birthday) values('name_no_001','123456','name_no_001@qq.com','1990-12-01');
insert into users(name,password,email,birthday) values('name_no_002','123456','name_no_002@qq.com','1990-12-02');
insert into users(name,password,email,birthday) values('name_no_003','123456','name_no_003@qq.com','1990-12-03');


