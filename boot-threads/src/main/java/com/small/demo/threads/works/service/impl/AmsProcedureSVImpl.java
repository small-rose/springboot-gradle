package com.small.demo.threads.works.service.impl;

import com.small.demo.threads.works.pojo.AmsTaskProcedureLog;
import com.small.demo.threads.works.repository.AmsTaskProcedureLogRepository;
import com.small.demo.threads.works.service.AmsProcedureSV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsProcedureSV ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/8 15:11
 * @Version ： 1.0
 **/
@Slf4j
@Service
public class AmsProcedureSVImpl implements AmsProcedureSV {


    @Autowired
    private JdbcTemplate jdbcTemplate ;

    @Autowired
    private AmsTaskProcedureLogRepository amsTaskProcedureLogRepository ;
    /**
     * 无参或已经拼好返回参数 无返回值
     * @param sql
     */
    public void callProc(String sql){
        // jdbcTemplate.execute("call sp_insert_table('100001')");
        jdbcTemplate.execute(sql);
    }

    /**
     * 无参，有返回值
     * @param sql
     */
    public Map<String, Object> callProcAndGet(String sql){
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.NVARCHAR));
        return jdbcTemplate.call(new CallableStatementCreator() {

            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {

                //CallableStatement cs = con.prepareCall("{call search_users_proc(?)}");
                CallableStatement cs = con.prepareCall(sql);
                return cs;
            }
        }, parameters);


    }

    /**
     * 有参，无返回值
     * @param sql
     */
    public void callProcByParams(String sql, Map<String, Object> params){
        /*jdbcTemplate.execute(
                new CallableStatementCreator() {
                    @Override
                    public CallableStatement createCallableStatement(Connection con) throws SQLException {
                        // {call sp_select_table (?,?)}
                        String storedProc = "{call sp_select_table (?,?)}";// 调用的sql
                        CallableStatement cs = con.prepareCall(storedProc);

                        cs.setString(1, "1");// 设置输入参数的值
                        cs.registerOutParameter(2, OracleTypes.VARCHAR);// 注册输出参数的类型
                        return cs;
                    }
                });*/
    }


    /**
     * 有参，有返回值
     * @param sql
     */
    public List callProcByParamsAndGet(String sql, Map<String, Object> params){
        List resultList = (List) jdbcTemplate.execute(
                new CallableStatementCreator() {
                    public CallableStatement createCallableStatement(Connection con) throws SQLException {
                        String storedProc = "{call sp_list_table(?,?)}";// 调用的sql
                        CallableStatement cs = con.prepareCall(storedProc);
                        cs.setString(1, "p1");// 设置输入参数的值
                        //cs.registerOutParameter(2, OracleTypes.CURSOR);// 注册输出参数的类型
                        return cs;
                    }
                }, new CallableStatementCallback() {
                    public Object doInCallableStatement(CallableStatement cs) throws SQLException,DataAccessException {
                        List resultsMap = new ArrayList();
                        cs.execute();
                        ResultSet rs = (ResultSet) cs.getObject(2);// 获取游标一行的值
                        while (rs.next()) {// 转换每行的返回值到Map中
                            Map rowMap = new HashMap();
                            rowMap.put("id", rs.getString("id"));
                            rowMap.put("name", rs.getString("name"));
                            resultsMap.add(rowMap);
                        }
                        rs.close();
                        return resultsMap;
                    }
                });
        return resultList ;
    }


    @Override
    public void save(AmsTaskProcedureLog procLog) {
        amsTaskProcedureLogRepository.save(procLog);
    }


}
