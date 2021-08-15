drop table member;

select * from member;

create table member(
	id varchar(50) primary key,
	name varchar(50) not null,
	password varchar(50) not null,
	tel varchar(50) not null
);
