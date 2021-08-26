drop table member;
/*멤버 테이블 설정 */
select * from member;

create table member(
	member_id varchar(50) primary key,
	member_name varchar(50) not null,
	password varchar(50) not null,
	tel varchar(50) not null
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
	member_id varchar(50) not null CONSTRAINT fk_member_id  REFERENCES member(id),
	place_id int not null CONSTRAINT fk_place_id  REFERENCES place(f_id),
	reserve_date varchar(50) not null,
	reserve_time varchar(50) not null	
);
create sequence reserve_num increment by 1 start with 1;
/*예약확정 테이블 */
select * from confirm_reservation;
create table confirm_reservation(
	reserve_num int primary key,
	member_id varchar(50) not null CONSTRAINT fk_confirm_member_id  REFERENCES member(id),
	place_id int not null CONSTRAINT fk_confirm_place_id  REFERENCES place(f_id),
	reserve_date varchar(50) not null,
	reserve_time varchar(50) not null	
);
create sequence confirm_num increment by 1 start with 1;
insert into place values(place_num.NEXTVAL,'북현풋살구장','대구 북구 복현동 302-16','053-383-2630');
select * from place;
insert into place values(place_num.NEXTVAL,'lfc풋살파크','대구 달서구 달구벌대로 1820','650-81-00575');

alter table reservation add state varchar(25) default 'N'; /*예약 테이블에 추가하기 */
alter table reservation add reg_time time default sysdate; /*예약 테이블에 추가하기 */

/* 풋살장 시간 테이블 */
--create table operation(
--operation_num int primary key,
--place_id int not null CONSTRAINT fk_operation_id  REFERENCES place(f_id),
--operation_time varchar(50) not null);


create table operation(
id int PRIMARY key,
place_id int not null CONSTRAINT operation_place_id  REFERENCES place(f_id),
operation_time varchar(50));

create sequence operation_num increment by 1 start with 1; /*시퀀스 */

insert into operation values(operation_num.NEXTVAL,1,'09:00~12:00');
insert into operation values(operation_num.NEXTVAL,1,'13:00~16:00');
insert into operation values(operation_num.NEXTVAL,1,'18:00~21:00');
insert into operation values(operation_num.NEXTVAL,2,'10:00~13:00');
insert into operation values(operation_num.NEXTVAL,2,'14:00~17:00');
insert into operation values(operation_num.NEXTVAL,2,'19:00~22:00');
