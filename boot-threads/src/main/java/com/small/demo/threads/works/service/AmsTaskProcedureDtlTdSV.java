package com.small.demo.threads.works.service;

import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;

import java.util.List;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProcedureTdSV ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 15:40
 * @Version ： 1.0
 **/
public interface AmsTaskProcedureDtlTdSV {

    public List<AmsTaskProcedureDetailTd> getDtlListByTaskIdList(Long taskId);

    public boolean deleteTask(Long procedureId);

    public boolean startTask(Long procedureId);
}
