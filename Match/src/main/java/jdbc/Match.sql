drop table member;
/*멤버 테이블 설정 */
select * from member;

create table member(
	member_id varchar(50) primary key,
	member_name varchar(50) not null,
	member_password varchar(50) not null,
	member_tel varchar(50) not null
);

/*풋살장 테이블 설정 */
select * from place;
select * from tabs;
select * from reservation;
create table place(
	place_id int primary key,
	place_name varchar(50) not null,
	place_addr varchar(50) not null,
	place_tel varchar(50) not null
);

select * from place;
create sequence place_num increment by 1 start with 1;
drop sequence place_num;

/*예약 테이블 설정 */
select * from reservation;
create table reservation(
	reserve_num int primary key,
	member_id varchar(50) not null CONSTRAINT fk_member_id  REFERENCES member(member_id) ON DELETE CASCADE,
	place_id int not null CONSTRAINT fk_place_id  REFERENCES place(place_id),
	reserve_date varchar(50) not null,
	reserve_time varchar(50) not null	
);
create sequence reserve_num increment by 1 start with 1;

insert into place values(place_num.NEXTVAL,'북현풋살구장','대구 북구 복현동 302-16','053-383-2630');
select * from place;
insert into place values(place_num.NEXTVAL,'lfc풋살파크','대구 달서구 달구벌대로 1820','650-81-00575');

alter table reservation add state varchar(25) default '예약대기'; /*예약 테이블에 추가하기 */
alter table reservation add reg_time date default sysdate; /*예약 테이블에 추가하기 */

create table article(
	article_no int primary key,
	member_id varchar(50) not null constraint fk_article references member(member_id) ON DELETE CASCADE,
	member_name varchar(50) not null,
	title varchar(225) not null,
	regdate timestamp not null,
	moddate timestamp not null,
	content long not null,
	read_cnt int);
//article에 content가 추가되었음.

select * from article;

grant create sequence to hr;

//article_no 글번호 증가 num_seq
create sequence num_seq
increment by 1 start with 1;

