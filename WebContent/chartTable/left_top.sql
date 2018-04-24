-- Create table
create table LEFT_TOP_HEAD
(
  TITLE             VARCHAR2(100 CHAR) not null,
  CATEGORYAXISLABEL             VARCHAR2(100 CHAR),
  VALUEAXISLABEL             VARCHAR2(100 CHAR) not null,
  created_time     TIMESTAMP(6) default systimestamp
)
tablespace XXX
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
-- Create table 
  create table LEFT_TOP_Y
(
  CATEGORIES             VARCHAR2(100 CHAR) not null,
  X             VARCHAR2(100 CHAR) not null,
  QUANTITY             FLOAT,
  created_time     TIMESTAMP(6) default systimestamp
)
tablespace XXX
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );