package com.small.demo.threads.works.dao;

import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProDtlCheckDao ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 17:52
 * @Version ： 1.0
 **/
public interface AmsTaskProDtlCheckDao {

    Long countPolicyMirror(AmsTaskProcedureDetailTd detailTd);

    Long countBeforeMirror(AmsTaskProcedureDetailTd detailTd);

    Long countOrder(AmsTaskProcedureDetailTd detailTd);

    Long countParallel(AmsTaskProcedureDetailTd detailTd);

    long countFlagSyn(AmsTaskProcedureDetailTd detailTd);

    long countFlagSleepStart(AmsTaskProcedureDetailTd detailTd);

    long countFlagPolicInsurance(AmsTaskProcedureDetailTd detailTd);

    long countFlagPolicyMirrorHLW(AmsTaskProcedureDetailTd detailTd);
}
