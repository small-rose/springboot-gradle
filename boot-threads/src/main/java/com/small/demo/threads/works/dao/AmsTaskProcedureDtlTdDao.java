package com.small.demo.threads.works.dao;

import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;

import java.util.List;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProcedureTdDao ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 15:32
 * @Version ： 1.0
 **/
public interface AmsTaskProcedureDtlTdDao {

    public List<AmsTaskProcedureDetailTd> getDtlListByTaskIdList(Long taskId);

    int updateifvalid(Long procedureId);

    int updateProcedureStatus(Long procedureId);
}
