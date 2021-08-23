drop table member;

select * from member;

create table member(
	id varchar(50) primary key,
	name varchar(50) not null,
	password varchar(50) not null,
	tel varchar(50) not null
);

drop table article;

create table article(
	article_no int primary key,
	writer_id varchar(50) not null,
	writer_name varchar(50) not null,
	title varchar(225) not null,
	regdate timestamp not null,
	moddate timestamp not null,
	content long not null,
	read_cnt int);
//article에 content가 추가되었음.

select article_no from article;

grant create sequence to hr;

//article_no 글번호 증가 num_seq
create sequence num_seq
increment by 1 start with 1;
