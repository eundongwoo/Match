create table article_comment (
	comment_num int not null primary key,
	member_id varchar(50) not null,
	comment_date timestamp not null,
	comment_article int constraint fk_comment references article(article_no) ON DELETE CASCADE,
	comment_content varchar(1000) not null
	);

	
alter table article_comment drop constraint fk_comment;
alter table article_comment add constraint fk_comment foreign key(comment_article) references article(article_no) on delete cascade;

//comment_article은 댓글을 작성한 게시판이다. 게시판의 시퀀스..번호로 해야하나?
//comment_parent는 대댓글을 위한 부모댓글이다.
//comment_num은 article_comment_seq를 위한 자리이다.
//아직 forien key등 설정을 덜 한 상태이다.
select * from article_comment;

drop table article_comment;
//
create sequence article_comment_seq
increment by 1 start with 1;

select * from article_comment;

insert into article_comment values(2, user, systimestamp, 51, 1, 123);











댓글 글번호,게시글 글번호,댓글작성자,댓글작성일,부모글,댓글 내용