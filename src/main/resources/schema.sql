create table USERS(
  ID int not null,
  NAME varchar(100) not null,
  EMAIL varchar(100),
  PASSWORD varchar(100) not null,
  PRIMARY KEY ( ID )
);
create table POSTS(
  ID int not null,
  TITLE varchar(100),
  BODY varchar(255),
  AUTHOR varchar(100),
  PRIMARY KEY ( ID )
);

CREATE SEQUENCE USERS_SEQUENCE_ID START WITH (select max(ID) + 1 from USERS);
CREATE SEQUENCE POSTS_SEQUENCE_ID START WITH (select max(ID) + 1 from POSTS);