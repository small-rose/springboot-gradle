package com.small.demo.threads.works.service;

import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;

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
    boolean FlagPolicyMirror(AmsTaskProcedureDetailTd taskDtl);
    /**
     * 判断未起保台账是否完成
     */
    boolean FlagBeforemirror(AmsTaskProcedureDetailTd taskDtl);
    /**
     * 判断有序执行
     */
    boolean FlagOrder(AmsTaskProcedureDetailTd taskDtl);

    /**
     * 判断 并发数
     */
    boolean Flag_Parallel(AmsTaskProcedureDetailTd taskDtl);

    /**
     * 判断 同步执行
     */
    boolean FlagSyn(AmsTaskProcedureDetailTd taskDtl);


    /**
     * 判断  仅执行一次
     */
    boolean FlagOnlyone(AmsTaskProcedureDetailTd taskDtl);

    /**
     * 判断是否有共保数据需要落地
     */
    boolean FlagPolicInsurance(AmsTaskProcedureDetailTd taskDtl);

    /**
     *  互联网应收
     */
    boolean FlagPolicyMirrorHLW(AmsTaskProcedureDetailTd taskDtl);

    /**
     *  开始休眠
     */
    boolean FlagSleepStart(AmsTaskProcedureDetailTd taskDtl);

    /**
     *  结束休眠
     */
    boolean FlagSleepEnd();




}
