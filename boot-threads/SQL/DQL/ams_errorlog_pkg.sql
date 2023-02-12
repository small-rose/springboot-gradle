
CREATE OR REPLACE package ams_errorlog_pkg is

  -- Author  : guqin
  -- Created : 2007-10-24 14:02:23
  -- Purpose : 定义错误号,日志过程等.

  /*
  TODO: owner="guqin" category="Finish" priority="2 - Medium" created="2007-10-28" closed="2007-11-13"
  text="自定义全局异常号"
  */
  e_null_value constant binary_integer := -20001; /*空值*/
  null_value exception;
  pragma exception_init(null_value, -20001);
  e_invalid_value constant binary_integer := -20002; /*无效值*/
  invalid_value exception;
  pragma exception_init(invalid_value, -20002);
  e_invalid_rowcnt constant binary_integer := -20003; /*错误行数*/
  invalid_rowcnt exception;
  pragma exception_init(invalid_rowcnt, -20003);
  e_invalid_rule constant binary_integer := -20004; /*校验规则失败*/
  invalid_rule exception;
  pragma exception_init(invalid_rule, -20004);
  e_split_error constant binary_integer := -20005; /*拆分错误*/
  split_error exception;
  pragma exception_init(split_error, -20005);
  e_update_error constant binary_integer := -20006; /*更新失败*/
  update_error exception;
  pragma exception_init(update_error, -20006);
  e_system_error constant binary_integer := -20007; /*无法处理的错误*/
  system_error exception;
  pragma exception_init(system_error, -20007);
  e_no_data_found constant binary_integer := -20100; /*获取不到值,*/
  no_data_found exception;
  pragma exception_init(no_data_found, -20100);

  bulk_errors exception;
  pragma exception_init(bulk_errors, -24381); /*传输数据块错误*/
  /*
  TODO: owner="guqin" category="Finish" priority="2 - Medium" created="2007-10-24" closed="2007-12-12"
  text="错误日志,dbms_utility.format_error_backtrace适用于oracle10g"
  */
  procedure log_error(procname_in in ams_error_log.procname%type,
                      keyword1_in in ams_error_log.keyword1%type default null,
                      keyword2_in in ams_error_log.keyword2%type default null,
                      keyword3_in in ams_error_log.keyword3%type default null,
                      keyword4_in in ams_error_log.keyword4%type default null,
                      info_in     in ams_error_log.info%type default null,
                      sqlcode_in  in ams_error_log.sqlcode%type default sqlcode,
                      sqlerrm_in  in ams_error_log.sqlerrm%type default sqlerrm,
                      trace_in    in ams_error_log.trace%type default dbms_utility.format_error_backtrace);

end ams_errorlog_pkg;

CREATE OR REPLACE package body ams_errorlog_pkg is
  -- author  : guqin
  -- created : 2007-10-24 14:02:23
  -- purpose : 记录错误日志
  procedure log_error(procname_in in ams_error_log.procname%type,
                      keyword1_in in ams_error_log.keyword1%type default null,
                      keyword2_in in ams_error_log.keyword2%type default null,
                      keyword3_in in ams_error_log.keyword3%type default null,
                      keyword4_in in ams_error_log.keyword4%type default null,
                      info_in     in ams_error_log.info%type default null,
                      sqlcode_in  in ams_error_log.sqlcode%type default sqlcode,
                      sqlerrm_in  in ams_error_log.sqlerrm%type default sqlerrm,
                      trace_in    in ams_error_log.trace%type default dbms_utility.format_error_backtrace) is
    pragma autonomous_transaction; /*自治事务*/
  begin
    insert into ams_error_log(
           owner,    info,    sqlcode,  sqlerrm,
           trace,    logdate, procname, keyword1,
           keyword2, keyword3,keyword4, lastopdate,
           timestamp,hibernateversion
           )
    values(user,info_in,sqlcode_in,sqlerrm_in,
           trace_in,   sysdate,    upper(procname_in),keyword1_in,
           keyword2_in,keyword3_in,keyword4_in,       Sysdate,
           Sysdate,  1
           );
    commit;
  exception
    when others then
      rollback;
      dbms_output.put_line(sqlerrm);
      raise;
  end;
end ams_errorlog_pkg;