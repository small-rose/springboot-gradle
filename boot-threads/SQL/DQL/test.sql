

CREATE OR REPLACE PACKAGE TEST_01 AS
    PROCEDURE proc_example(p1 NUMBER);
END TEST_01 ;
/
create or replace PACKAGE BODY TEST_01 AS
    V_SUBCOMPANY  VARCHAR2(100);
    V_ZZY  VARCHAR2(100);
    PROCEDURE proc_example(p1  NUMBER) IS
        PROCEDURE proc_example_I1(p2  NUMBER) IS
        BEGIN
            BEGIN
                V_SUBCOMPANY := (100010-1) || '';
                EXECUTE IMMEDIATE ' SELECT PROCEDURE_VALUE FROM AMS_TASK_PROCEDURE_TD WHERE TASK_ID =:1'
                    INTO V_ZZY
                -- ob 不支持在 USING 传参数的时候运算  ?
                USING   100010-1 || '' ;
                -- USING   V_SUBCOMPANY ;
                AMS_ERRORLOG_PKG.LOG_ERROR('TEST_01','测试执行',V_SUBCOMPANY
            ,'传入的参数是'|| p2, sysdate);
            EXCEPTION when others then
                NULL;
            END;
            NULL;
        END proc_example_I1;

        PROCEDURE proc_example_I2(p3 NUMBER) IS
        BEGIN
            NULL;
        END ;
    BEGIN
    proc_example_I1(p1);
    END;
  BEGIN
    NULL;
END TEST_01 ;

begin
    TEST_01.proc_example(1);
end;




create or replace package TYPE_TEST_DEMO is

    procedure init ;
    procedure exe_test ;

end TYPE_TEST_DEMO ;
/
create or replace package body TYPE_TEST_DEMO is

    TYPE T_VALUE2S IS RECORD(
        value0 VARCHAR2(100),
        value1 VARCHAR2(100));
    TYPE T_VALUE2S_F IS TABLE OF T_VALUE2S INDEX BY BINARY_INTEGER;
        C_COLLECTTION T_VALUE2S_F ;


    procedure exe_qy_out(i_sql in varchar2, o_value2s out T_VALUE2S_F) is
    begin
      --dbms_output.put_line(i_sql);
      EXECUTE IMMEDIATE i_sql bulk collect
      into o_value2s;
    end exe_qy_out;

    procedure init is
       v_sql varchar2(1000);
    begin
        v_sql := 'SELECT PARAM_KEY, PARAM_VALUE  FROM AMS_TASK_PROCEDURE_PARAM_TD ';

        exe_QY_out(v_sql, C_COLLECTTION);
        for i in 1 .. C_COLLECTTION.count() LOOP
            DBMS_OUTPUT.PUT_LINE(C_COLLECTTION(i).value0 || C_COLLECTTION(i).value1 );
        end loop;
    end init;

    procedure exe_test is
    begin
        init;
    end exe_test;

end TYPE_TEST_DEMO ;

