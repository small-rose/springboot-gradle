-- TABLE AMS_ERROR_LOG
CREATE TABLE AMS_ERROR_LOG (
	"OWNER" VARCHAR2(30 BYTE) NOT NULL,
	"INFO" VARCHAR2(4000 BYTE),
	"SQLCODE" NUMBER(20,0),
	"SQLERRM" VARCHAR2(4000 BYTE),
	"TRACE" VARCHAR2(4000 BYTE),
	"LOGDATE" DATE NOT NULL,
	"PROCNAME" VARCHAR2(100 BYTE) NOT NULL,
	"KEYWORD1" VARCHAR2(100 BYTE),
	"KEYWORD2" VARCHAR2(100 BYTE),
	"KEYWORD3" VARCHAR2(100 BYTE),
	"KEYWORD4" VARCHAR2(100 BYTE),
	"LASTOPDATE" DATE DEFAULT SYSDATE NOT NULL,
	"TIMESTAMP" DATE DEFAULT SYSDATE NOT NULL,
	"HIBERNATEVERSION" NUMBER(5,0) DEFAULT 1 NOT NULL
);

CREATE INDEX IDX_AMS_ERRORLOG_01  ON AMS_ERROR_LOG("KEYWORD1" ASC);


---- table AMS_TASK_PROCEDURE_TD
create table AMS_TASK_PROCEDURE_TD
(
    TASK_ID          NUMBER         not null
        constraint SYS_C00461396
            primary key,
    PROCEDURE_VALUE  VARCHAR2(1000) not null,
    PROCEDURE_LEVEL  NUMBER,
    PROCEDURE_ORDER  NUMBER         not null,
    PROCEDURE_STATUS VARCHAR2(100),
    IFVALID          NUMBER,
    PROCEDURE_DESC   VARCHAR2(1000),
    EXECUTION_DATE   VARCHAR2(1000),
    IFPARALLEL       NUMBER,
    CREATE_TIME      DATE default sysdate,
    CREATER          NUMBER
)
/

comment on column AMS_TASK_PROCEDURE_TD.TASK_ID is '任务id'
/

comment on column AMS_TASK_PROCEDURE_TD.PROCEDURE_VALUE is '存储过程'
/

comment on column AMS_TASK_PROCEDURE_TD.PROCEDURE_LEVEL is '存储过程执行级别'
/

comment on column AMS_TASK_PROCEDURE_TD.PROCEDURE_ORDER is '存储过程执行顺序'
/

comment on column AMS_TASK_PROCEDURE_TD.PROCEDURE_STATUS is '存储过程执行状态00初始，01待执行，02正在执行，03执行完成'
/

comment on column AMS_TASK_PROCEDURE_TD.IFVALID is '存储过程是否启用 1启用 0不启用'
/

comment on column AMS_TASK_PROCEDURE_TD.PROCEDURE_DESC is '参数描述'
/

comment on column AMS_TASK_PROCEDURE_TD.EXECUTION_DATE is '执行的日期'
/

comment on column AMS_TASK_PROCEDURE_TD.IFPARALLEL is '0串行1并行'
/

comment on column AMS_TASK_PROCEDURE_TD.CREATE_TIME is '创建时间'
/

comment on column AMS_TASK_PROCEDURE_TD.CREATER is '创建人'
/

-- table AMS_TASK_PROCEDURE_DETAIL_TD
create table AMS_TASK_PROCEDURE_DETAIL_TD
(
    PROCEDURE_ID     NUMBER         not null
        constraint SYS_C00461401
            primary key,
    TASK_ID          NUMBER         not null,
    PROCEDURE_VALUE  VARCHAR2(1000) not null,
    PROCEDURE_LEVEL  NUMBER,
    PROCEDURE_ORDER  NUMBER         not null,
    PROCEDURE_STATUS VARCHAR2(100),
    IFVALID          NUMBER,
    PROCEDURE_DESC   VARCHAR2(1000),
    PARALLEL         NUMBER,
    JOB_ID           NUMBER,
    EXECUTION_DATE   VARCHAR2(1000),
    CREATE_TIME      DATE           default sysdate,
    CREATER          NUMBER,
    PROCEDURE_FALG   VARCHAR2(1000) default 'OK',
    TASKSTARTDATE    DATE,
    TASKENDDATE      DATE,
    EXECUTSTART_DATE DATE,
    EXECUTEND_DATE   DATE,
    EXECUTNEXT_DATE  VARCHAR2(1000),
    TASK_DATE        DATE
)
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.PROCEDURE_ID is '存储过程主键'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.TASK_ID is '任务编码'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.PROCEDURE_VALUE is '存储过程'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.PROCEDURE_LEVEL is '存储过程执行级别'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.PROCEDURE_ORDER is '存储过程执行顺序'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.PROCEDURE_STATUS is '存储过程执行状态'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.IFVALID is '存储过程是否启用 1启用 0不启用'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.PROCEDURE_DESC is '参数描述'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.PARALLEL is '允许并发数　Flag_Parallel'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.JOB_ID is '任务id'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.EXECUTION_DATE is '执行的日期'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.CREATE_TIME is '创建时间'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.CREATER is '执行次数'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.PROCEDURE_FALG is '存储过程执行状态,OK不走任何校验仅执行一次'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.TASKSTARTDATE is '任务开始时间'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.TASKENDDATE is '任务结束时间'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.EXECUTSTART_DATE is '允许执行的日期区间'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.EXECUTEND_DATE is '允许执行的日期区间'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.EXECUTNEXT_DATE is '允许执行的下一次日期'
/

comment on column AMS_TASK_PROCEDURE_DETAIL_TD.TASK_DATE is '任务时间'
/



-- table AMS_TASK_PROCEDURE_PARAM_TD

create table AMS_TASK_PROCEDURE_PARAM_TD
(
  param_id     INTEGER PRIMARY KEY not null,
  procedure_id INTEGER not null,
  param_key    VARCHAR2(100) not null,
  param_value  VARCHAR2(1000) not null,
  param_type   VARCHAR2(100),
  param_order  INTEGER,
  param_desc   VARCHAR2(1000),
  create_time  DATE default sysdate,
  creater      INTEGER
);
-- Add comments to the columns
comment on column AMS_TASK_PROCEDURE_PARAM_TD.param_id
  is '参数主键';
comment on column AMS_TASK_PROCEDURE_PARAM_TD.procedure_id
  is '任务编码';
comment on column AMS_TASK_PROCEDURE_PARAM_TD.param_key
  is '参数代码';
comment on column AMS_TASK_PROCEDURE_PARAM_TD.param_value
  is '参数值';
comment on column AMS_TASK_PROCEDURE_PARAM_TD.param_type
  is '参数类型(不填默认为String类型)';
comment on column AMS_TASK_PROCEDURE_PARAM_TD.param_order
  is '参数顺序';
comment on column AMS_TASK_PROCEDURE_PARAM_TD.param_desc
  is '参数描述';
comment on column AMS_TASK_PROCEDURE_PARAM_TD.create_time
  is '创建时间';
comment on column AMS_TASK_PROCEDURE_PARAM_TD.creater
  is '创建人';



-- 新增字段
ALTER TABLE AMS_TASK_PROCEDURE_TD ADD  MAX_PARALLEL NUMBER(22) DEFAULT 0 ;
COMMENT ON COLUMN  AMS_TASK_PROCEDURE_TD.MAX_PARALLEL IS '当前任务下的子任务最大并发数';


ALTER TABLE AMS_TASK_PROCEDURE_DETAIL_TD ADD MAX_ERROR_COUNT NUMBER(22) DEFAULT 5 ;
COMMENT ON COLUMN  AMS_TASK_PROCEDURE_DETAIL_TD.MAX_ERROR_COUNT IS '当前任务执行错误最大次数，超过此限制后续不再调用';

ALTER TABLE AMS_TASK_PROCEDURE_DETAIL_TD ADD ERROR_COUNT NUMBER(22) DEFAULT 0 ;
COMMENT ON COLUMN  AMS_TASK_PROCEDURE_DETAIL_TD.ERROR_COUNT IS '当前任务执行错误次数累计';


-- 01 准备就绪，提交线程任务
-- 02 正在执行中
-- 03 执行成功
-- 04 执行出错（含超时）
-- 05 更新状态出错

/**
-- 执行逻辑
1、每次根据主任务--检索出 03或(04错误次数符合执行）的明细
2、更新状态为 01 之后提交到线程池
3、线程执行--状态变02
3.1 执行成功--状态变03
3.2 执行失败--状态变014
4、状态更新出错--状态变05
*/

-- 日志表


CREATE TABLE AMS_TASK_PROCEDURE_LOG
(
    LOG_ID NUMBER(22),
    TASK_ID NUMBER(22),
    PROCEDURE_ID NUMBER(22),
    PROCEDURE_VAL VARCHAR2(1000),
    THREAD_ID NUMBER(22),
    THREAD_NAME VARCHAR2(200),
    EXE_RESULT VARCHAR2(10),
    ERROR_INFO VARCHAR2(4000),
    TASKSTARTDATE DATE,
    TASKENDDATE  DATE,
    TAKE_TIME  VARCHAR2(200),
    LASTOPDATE DATE
);

COMMENT ON TABLE AMS_TASK_PROCEDURE_LOG IS 'Java存过调用执行日志表';
comment on column AMS_TASK_PROCEDURE_LOG.LOG_ID IS '日志主键';
comment on column AMS_TASK_PROCEDURE_LOG.TASK_ID IS '主任务ID';
comment on column AMS_TASK_PROCEDURE_LOG.PROCEDURE_ID IS '明细存过任务ID';
comment on column AMS_TASK_PROCEDURE_LOG.THREAD_ID IS '线程ID';
comment on column AMS_TASK_PROCEDURE_LOG.THREAD_NAME IS '线程名称';
comment on column AMS_TASK_PROCEDURE_LOG.PROCEDURE_VAL IS '执行内容';
comment on column AMS_TASK_PROCEDURE_LOG.EXE_RESULT IS '执行结果03-执行正常结束;04-执行异常';
comment on column AMS_TASK_PROCEDURE_LOG.ERROR_INFO IS '错误信息';
comment on column AMS_TASK_PROCEDURE_LOG.TASKSTARTDATE IS '任务开始时间';
comment on column AMS_TASK_PROCEDURE_LOG.TASKENDDATE IS '任务结束时间';
comment on column AMS_TASK_PROCEDURE_LOG.TAKE_TIME IS '执行耗时';
comment on column AMS_TASK_PROCEDURE_LOG.LASTOPDATE IS '最后更新时间';


-- 创建一个默认的序列
CREATE SEQUENCE SEQ_DEFAULT MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 CACHE 30 NOORDER NOCYCLE ;
