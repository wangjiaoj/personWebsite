
create table DTUser(
	userId int primary key,
	userName varchar(32) not null,
	pwd varchar(32) not null,
	email varchar(32),
	introduction varchar(250),
	photoURL varchar(50)
);

create table DTCategory
(
     categoryId int primary key,
	 categoryName varchar(32) not null,
);

create table BEArticle
(
    articleId int primary key,
    articleTitle varchar(32) not null,
    content text not null,
    writenTime   Date  not null,
    commentsNum int default 0 ,
    hits int default 0 ,
    categoryId int,
    constraint BEArticleFK foreign key(categoryId) references DTCategory(categoryId)
);
 
create table BEComment(
    commentId int primary key,
    content text not null,
    flag   char(1) 	default '0' ,
    mail   varchar(32) not null,
    reviewerName varchar(32) not null,
    replayName varchar(32) not null,
    publishedTime Date not null,
    articleId int ,
    check (flag in ('0','1')),    
    constraint BECommentFK foreign key(articleId) references BEArticle(articleId)
);

create table BEPicture(
	  picId int primary key,
      picUrl varchar(50)  not null
);
create table  BETag(
	  tagId int primary key,
      tagName varchar(20)  not null,
      tagNum int default 1 not null
);
 
create table  BETagRelationship(
     id int primary key,
     tagId Integer not null,
     articleId Integer not null,
     constraint  BETagRelationshipFK1 foreign key(tagId) references BETag(tagId),
     constraint  BETagRelationshipFK2 foreign key(articleId) references BEArticle(articleId)
);


SELECT  * from bearticle a ,betag b,betagrelationship c
 where  a.articleId=c.articleId  and c.tagId=b.tagId and a.categoryId=1 and b.tagName='"土豆"' 
ORDER BY a.writenTime; 

SELECT  * from bearticle a ,betag b,betagrelationship c
 where  a.articleId=c.articleId  and c.tagId=b.tagId and a.categoryId=1 and b.tagName='"土豆"' 
and  a.writenTime BETWEEN '2016-05-09' AND '2016-05-19' 
ORDER BY a.writenTime; 

/*触发器 删除tagRelationShip中的记录时更新betag.tagNum*/
create TRIGGER tagRelationDelete_T
AFTER delete  ON betagrelationship
FOR EACH ROW
BEGIN
  update betag set tagNum=tagNum-1 where tagId = old.tagId;
END;

/*触发器 添加tagRelationShip中的记录时更新betag.tagNum*/
create TRIGGER tagRelationAdd_T
AFTER insert  ON betagrelationship
FOR EACH ROW
BEGIN
  update betag set tagNum=tagNum+1 where tagId = new.tagId;
END;

drop trigger tagRelationDelete_T;