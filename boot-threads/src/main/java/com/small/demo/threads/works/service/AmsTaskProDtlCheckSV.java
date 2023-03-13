package com.small.demo.threads.works.service;

import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;
import com.small.demo.threads.works.pojo.AmsTaskProcedureTd;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProDtlCheckSV ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 17:14
 * @Version ： 1.0
 **/
public interface AmsTaskProDtlCheckSV {

    /**
     * 判断应收台账是否切好
     */
    boolean flagPolicyMirror(AmsTaskProcedureDetailTd taskDtl);
    /**
     * 判断未起保台账是否完成
     */
    boolean flagBeforemirror(AmsTaskProcedureDetailTd taskDtl);
    /**
     * 判断有序执行
     */
    boolean flagOrder(AmsTaskProcedureDetailTd taskDtl);

    /**
     * 判断 并发数
     */
    boolean flag_Parallel(AmsTaskProcedureDetailTd taskDtl);

    /**
     * 判断 同步执行
     */
    boolean flagSyn(AmsTaskProcedureDetailTd taskDtl);


    /**
     * 判断  仅执行一次
     */
    boolean flagOnlyone(AmsTaskProcedureDetailTd taskDtl);

    /**
     * 判断是否有共保数据需要落地
     */
    boolean flagPolicInsurance(AmsTaskProcedureDetailTd taskDtl);

    /**
     *  互联网应收
     */
    boolean flagPolicyMirrorHLW(AmsTaskProcedureDetailTd taskDtl);

    /**
     *  开始休眠
     */
    boolean flagSleepStart(AmsTaskProcedureDetailTd taskDtl);

    /**
     *  结束休眠
     */
    boolean flagSleepEnd();


    /**
     * 校验最大并发数
     * @param taskDtl
     * @return
     */
    boolean flagTaskMaxParallel(AmsTaskProcedureDetailTd taskDtl, AmsTaskProcedureTd procDtlTd);
}
