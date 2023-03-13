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

    int countPolicyMirror(AmsTaskProcedureDetailTd detailTd);

    int countBeforeMirror(AmsTaskProcedureDetailTd detailTd);

    int countOrder(AmsTaskProcedureDetailTd detailTd);

    int countParallel(AmsTaskProcedureDetailTd detailTd);

    int countFlagSyn(AmsTaskProcedureDetailTd detailTd);

    int countFlagSleepStart(AmsTaskProcedureDetailTd detailTd);

    int countFlagPolicInsurance(AmsTaskProcedureDetailTd detailTd);

    int countFlagPolicyMirrorHLW(AmsTaskProcedureDetailTd detailTd);

    int countRunningDtl(AmsTaskProcedureDetailTd taskDtl);
}
