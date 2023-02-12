package com.small.demo.threads.works.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ TestService ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/9 13:15
 * @Version ： 1.0
 **/
@Slf4j
@Service
public class TestService {

    @Autowired
    private AmsProcedureSV amsProcedureSV ;

    private static int index = 0 ;
    /*

     */
    public void callProc(String procName){
        String sql = "";

        if (StringUtils.hasText(procName)){
            sql = "call " + procName ;
        }else {
            if (index == 0) {
                index = 1;
            } else {
                index = index + 1;
            }
            sql = "call TEST_01.proc_example(" + index + ") ";
        }
        log.info(" SQL : {}" , sql);
        amsProcedureSV.callProc(sql);
    }


    public void executeProc(String dql){
        String sql = "" ;
        if (StringUtils.hasText(dql)){
            sql = dql ;
        }else {
            sql = "CREATE OR REPLACE PROCEDURE ALL_TABLE_STATISTICS_COUNT\n" +
                    "IS\n" +
                    "V_SQL VARCHAR2(1000) DEFAULT '';\n" +
                    "BEGIN\n" +
                    "\n" +
                    "FOR RS IN ( SELECT T.TABLE_NAME FROM USER_TABLES T )LOOP\n" +
                    "V_SQL :='ANALYZE TABLE '||RS.TABLE_NAME||' COMPUTE STATISTICS';\n" +
                    "EXECUTE IMMEDIATE V_SQL;\n" +
                    "COMMIT;\n" +
                    "END LOOP;\n" +
                    "EXCEPTION\n" +
                    "WHEN OTHERS THEN\n" +
                    "DBMS_OUTPUT.PUT_LINE('ERRM ALL_TABLE_STATISTICS_COUNT:' || SQLERRM);\n" +
                    "END;\n" +
                    "/\n" +
                    "BEGIN\n" +
                    "  ALL_TABLE_STATISTICS_COUNT ;\n" +
                    "END ;\n" +
                    "/\n" +
                    "DROP PROCEDURE ALL_TABLE_STATISTICS_COUNT ;\n";
        }
        log.info("DQL : {}" , sql);
        amsProcedureSV.callProc(sql);
    }
}
