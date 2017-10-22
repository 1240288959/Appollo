-------------------------------------------------------
-- Export file for user SCOTT@LANQIAO                --
-- Created by fanhuayeluojin on 2017/10/22, 14:46:22 --
-------------------------------------------------------

set define off
spool sql.log

prompt
prompt Creating table ACCOUNT
prompt ======================
prompt
create table SCOTT.ACCOUNT
(
  id      CHAR(36) not null,
  card_id VARCHAR2(20),
  name    VARCHAR2(8),
  balance NUMBER(5,2) default 0
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.ACCOUNT
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table BONUS
prompt ====================
prompt
create table SCOTT.BONUS
(
  ename VARCHAR2(10),
  job   VARCHAR2(9),
  sal   NUMBER,
  comm  NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table BOOKTYPE
prompt =======================
prompt
create table SCOTT.BOOKTYPE
(
  id        CHAR(36) not null,
  name      VARCHAR2(50),
  parent_id CHAR(36)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table SCOTT.BOOKTYPE
  is '这是书类型表';
alter table SCOTT.BOOKTYPE
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.BOOKTYPE
  add foreign key (PARENT_ID)
  references SCOTT.BOOKTYPE (ID);

prompt
prompt Creating table PRESS
prompt ====================
prompt
create table SCOTT.PRESS
(
  id   CHAR(36) not null,
  name VARCHAR2(50) not null,
  url  VARCHAR2(150)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table SCOTT.PRESS
  is '这是出版社表';
alter table SCOTT.PRESS
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table BOOK
prompt ===================
prompt
create table SCOTT.BOOK
(
  id                 CHAR(36) not null,
  name               VARCHAR2(50) not null,
  author             VARCHAR2(50) not null,
  price              NUMBER(8,2) default 0,
  total              NUMBER(5) default 0,
  big_book_type_id   CHAR(36),
  small_book_type_id CHAR(36),
  publisher_id       CHAR(36),
  sumarry            CLOB
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table SCOTT.BOOK
  is '这是书表';
alter table SCOTT.BOOK
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.BOOK
  add constraint BOOK_BIG_FK foreign key (BIG_BOOK_TYPE_ID)
  references SCOTT.BOOKTYPE (ID);
alter table SCOTT.BOOK
  add constraint BOOK_PUB_FK foreign key (PUBLISHER_ID)
  references SCOTT.PRESS (ID);
alter table SCOTT.BOOK
  add constraint BOOK_SMALL_FK foreign key (SMALL_BOOK_TYPE_ID)
  references SCOTT.BOOKTYPE (ID);
alter table SCOTT.BOOK
  add constraint PRICECHECK
  check (price>0);
alter table SCOTT.BOOK
  add constraint TOTALCHECK
  check (total>0);

prompt
prompt Creating table USER_INFO
prompt ========================
prompt
create table SCOTT.USER_INFO
(
  id               CHAR(36) not null,
  user_name        VARCHAR2(30),
  password         VARCHAR2(20),
  real_name        VARCHAR2(60),
  certification_id VARCHAR2(40),
  mobile           CHAR(11),
  address          VARCHAR2(60),
  timer            NUMBER(1) default 0
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table SCOTT.USER_INFO
  is '这是用户信息表';
alter table SCOTT.USER_INFO
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table BOOKCOMMENT
prompt ==========================
prompt
create table SCOTT.BOOKCOMMENT
(
  id        CHAR(36) not null,
  user_id   CHAR(36) not null,
  book_id   CHAR(36) not null,
  parent_id CHAR(36),
  content   CLOB
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table SCOTT.BOOKCOMMENT
  is '这是评论表';
alter table SCOTT.BOOKCOMMENT
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.BOOKCOMMENT
  add foreign key (USER_ID)
  references SCOTT.USER_INFO (ID);
alter table SCOTT.BOOKCOMMENT
  add foreign key (BOOK_ID)
  references SCOTT.BOOK (ID);
alter table SCOTT.BOOKCOMMENT
  add foreign key (PARENT_ID)
  references SCOTT.BOOKCOMMENT (ID);

prompt
prompt Creating table CART
prompt ===================
prompt
create table SCOTT.CART
(
  id      CHAR(36) not null,
  user_id CHAR(36) not null,
  book_id CHAR(36) not null,
  num     NUMBER(6)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table SCOTT.CART
  is '这是购物车标';
alter table SCOTT.CART
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.CART
  add foreign key (USER_ID)
  references SCOTT.USER_INFO (ID);
alter table SCOTT.CART
  add foreign key (BOOK_ID)
  references SCOTT.BOOK (ID);

prompt
prompt Creating table DEPT
prompt ===================
prompt
create table SCOTT.DEPT
(
  deptno NUMBER(2) not null,
  dname  VARCHAR2(14),
  loc    VARCHAR2(13)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.DEPT
  add constraint PK_DEPT primary key (DEPTNO)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table EMP
prompt ==================
prompt
create table SCOTT.EMP
(
  empno    NUMBER(4) not null,
  ename    VARCHAR2(10),
  job      VARCHAR2(9),
  mgr      NUMBER(4),
  hiredate DATE,
  sal      NUMBER(7,2),
  comm     NUMBER(7,2),
  deptno   NUMBER(2)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.EMP
  add constraint PK_EMP primary key (EMPNO)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.EMP
  add constraint FK_DEPTNO foreign key (DEPTNO)
  references SCOTT.DEPT (DEPTNO);

prompt
prompt Creating table EMPLOYEE
prompt =======================
prompt
create table SCOTT.EMPLOYEE
(
  id               CHAR(36) not null,
  user_name        VARCHAR2(8),
  password         VARCHAR2(10),
  real_name        VARCHAR2(8),
  certification_id VARCHAR2(36)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table SCOTT.EMPLOYEE
  is '员工表';
comment on column SCOTT.EMPLOYEE.id
  is 'ID';
comment on column SCOTT.EMPLOYEE.user_name
  is '用户名';
comment on column SCOTT.EMPLOYEE.password
  is '密码';
comment on column SCOTT.EMPLOYEE.real_name
  is '真实姓名';
comment on column SCOTT.EMPLOYEE.certification_id
  is '身份证号';
alter table SCOTT.EMPLOYEE
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table NEW_PEOPLE
prompt =========================
prompt
create table SCOTT.NEW_PEOPLE
(
  id   NUMBER(5) not null,
  name VARCHAR2(8) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.NEW_PEOPLE
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PEOPLE
prompt =====================
prompt
create table SCOTT.PEOPLE
(
  id   CHAR(36) not null,
  name VARCHAR2(8)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table SCOTT.PEOPLE
  is 'people';
comment on column SCOTT.PEOPLE.id
  is 'id';
alter table SCOTT.PEOPLE
  add constraint PEOPLE_ID_PK primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table SALGRADE
prompt =======================
prompt
create table SCOTT.SALGRADE
(
  grade NUMBER,
  losal NUMBER,
  hisal NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table SBOOK
prompt ====================
prompt
create table SCOTT.SBOOK
(
  id        CHAR(12),
  name      VARCHAR2(20),
  author    VARCHAR2(8),
  summary   CLOB,
  publisher VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table SHOPORDER
prompt ========================
prompt
create table SCOTT.SHOPORDER
(
  id      CHAR(36) not null,
  user_id CHAR(36) not null,
  book_id CHAR(36) not null,
  sum     NUMBER(6),
  address VARCHAR2(60)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table SCOTT.SHOPORDER
  is '这是订单表';
alter table SCOTT.SHOPORDER
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SCOTT.SHOPORDER
  add foreign key (USER_ID)
  references SCOTT.USER_INFO (ID);
alter table SCOTT.SHOPORDER
  add foreign key (BOOK_ID)
  references SCOTT.BOOK (ID);

prompt
prompt Creating function AGE
prompt =====================
prompt
create or replace function scott.age(id varchar2)
return number
is
newyear varchar2(5):=to_char(sysdate,'yyyy');
birthyear varchar2(5):=substr(id,7,4);
begin
  return to_number(newyear)-to_number(birthyear);
end;
/

prompt
prompt Creating function CALCULAGE
prompt ===========================
prompt
create or replace function scott.calculage(birthday varchar2)
return number
as
       nowyear varchar2(5):=to_char(sysdate,'yyyy');
       birthyear varchar2(5):=substr(birthday,1,4);
begin
       return nowyear-birthyear;
end calculage;
/

prompt
prompt Creating function CHECKGENDER
prompt =============================
prompt
create or replace function scott.checkgender(id varchar2)
return varchar2
is
numgender varchar2(2):=substr(id,17,1);
begin
  if(mod(to_number(numgender),2)=1) then
          return '男';
  else
          return '女';
  end if;
end;
/

prompt
prompt Creating function GETGENDER
prompt ===========================
prompt
create or replace function scott.getgender(cid varchar2)
return varchar2
as
       genderchar number(1):=to_number(substr(cid,16,1));
begin
       case mod(genderchar,2)
            when 1 then
              return '男';
            when 0 then
              return '女';
       end case;
end;
/

prompt
prompt Creating function ISBIRTHDAY
prompt ============================
prompt
create or replace function scott.isbirthday(cid varchar2)
return varchar2
as
       nowyear varchar2(10):=to_char(sysdate,'yyyy');
       nowmonth varchar2(10):=to_char(sysdate,'mm');
       nowday varchar2(10):=to_char(sysdate,'dd');
       birthyear varchar2(10):=substr(cid,7,10);
       birthmonth varchar2(10):=substr(cid,11,2);
       birthday varchar2(10):=substr(cid,13,2);

begin
       if nowmonth = birthmonth and nowday= birthday then
           return '生日快乐';
       else
           return '普通的一天';
       end if;
end isbirthday;
/

prompt
prompt Creating function RE_BIRTH
prompt ==========================
prompt
create or replace function scott.re_birth(c_id varchar2)
return varchar2
is
birthday varchar2(36);
year varchar2(8);
month varchar2(8);
day varchar2(8);
begin
year :=substr(c_id,7,4);
month :=substr(c_id,11,2);
day :=substr(c_id,13,2);
birthday:=year||'-'||month||'-'||day;
return birthday;
end;
/

prompt
prompt Creating procedure RETURN_TIMER_ZERO_GE
prompt =======================================
prompt
create or replace procedure scott.return_timer_zero_ge(uname varchar2)
is
begin
  update user_info set timer=0 where user_name = uname;
  commit;
  end;
/

prompt
prompt Creating procedure TIMER_JOB_START
prompt ==================================
prompt
create or replace procedure scott.timer_job_start
is
job_id number;
begin
  dbms_job.submit(job_id,'timer_return_zero;',sysdate,'trunc(sysdate+1)');
  dbms_job.run(job_id);
  end;
/

prompt
prompt Creating procedure TIMER_RETURN_ZERO
prompt ====================================
prompt
create or replace procedure scott.timer_return_zero
is
begin
  update user_info set timer=0;
  commit;
  end;
/

prompt
prompt Creating procedure TIMMER_ADD
prompt =============================
prompt
create or replace procedure scott.timmer_add(uid varchar2)
is
begin
  update user_info set timer=timer+1 where user_name=uid;
  commit;
  end;
/


spool off
