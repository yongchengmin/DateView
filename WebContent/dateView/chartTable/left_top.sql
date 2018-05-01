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
comment on table LEFT_TOP_HEAD is '左上监控数据头表';
comment on column LEFT_TOP_HEAD.TITLE is '左上监控头部标题';
comment on column LEFT_TOP_HEAD.CATEGORYAXISLABEL is '左上监控头部副标题';
comment on column LEFT_TOP_HEAD.VALUEAXISLABEL is '左上监控Y左侧备注';

create table LEFT_TOP_HEAD_TEMP nologging as select * from LEFT_TOP_HEAD where 1=2 ;
alter table LEFT_TOP_HEAD_TEMP modify created_time default systimestamp;
-- Create table 
  create table LEFT_TOP_Y
(
  CATEGORIES             VARCHAR2(100 CHAR) not null,
  X             VARCHAR2(100 CHAR) not null,
  QUANTITY             FLOAT,
  line         FLOAT not null,
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
comment on table LEFT_TOP_Y is '左上监控数据明细表';
comment on column LEFT_TOP_Y.CATEGORIES is '左上监控柱状分类';
comment on column LEFT_TOP_Y.X is '左上监控X轴备注';
comment on column LEFT_TOP_Y.QUANTITY is '左上监控柱状值';
comment on column LEFT_TOP_Y.line is '左上监控柱状显示顺序';

create table LEFT_TOP_Y_TEMP nologging as select * from LEFT_TOP_Y where 1=2 ;
alter table LEFT_TOP_Y_TEMP modify QUANTITY default 0;
alter table LEFT_TOP_Y_TEMP modify created_time default systimestamp;