create table notice(
	notice_no int primary key,
	member_id varchar(50) not null constraint fk_notice references member(member_id) ON DELETE CASCADE,
	member_name varchar(50) not null,
	title varchar(225) not null,
	regdate timestamp not null,
	moddate timestamp not null,
	content varchar2(2000) not null,
	read_cnt int);

drop table notice;

select * from notice;

//notice에는 시퀀스 없음.

