package com.small.demo.threads.works.service;

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
public interface AmsProcedureSV {

    /**
     * 无参或已经拼好返回参数  无返回值
     * @param sql
     */
    public void callProc(String sql);

    /**
     * 无参，有返回值
     * @param sql
     */
    public Map<String, Object> callProcAndGet(String sql);

    /**
     * 有参，无返回值
     * @param sql
     */
    public void callProcByParams(String sql, Map<String, Object> params);


    /**
     * 有参，有返回值
     * @param sql
     */
    public List callProcByParamsAndGet(String sql, Map<String, Object> params);



}
