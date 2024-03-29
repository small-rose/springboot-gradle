package com.small.demo.threads.works.thread;

import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;
import com.small.demo.threads.works.pojo.AmsTaskProcedureParamTd;
import com.small.demo.threads.works.service.AmsProcedureSV;
import com.small.demo.threads.works.service.AmsTaskProcedureDtlTdSV;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ ExecuteJob ] 说明：无
 * @Function :  功能说明： 有序执行的Job
 * @Date ：2023/2/8 15:02
 * @Version ： 1.0
 **/
@Slf4j
public class ExecuteJobOrdered<T> implements Callable<Boolean> {
    //执行存过ID
    private Long procedureId ;
    private Long orderNo ;

    // 执行任务对象实例
    private AmsTaskProcedureDetailTd atpDetailTd  ;
    // 执行参数对象实例
    private AmsTaskProcedureParamTd atpParamsTd  ;

    private AmsTaskProcedureDtlTdSV amsTaskProcedureDtlTdSV ;
    private AmsProcedureSV amsProcedureSV ;

    public ExecuteJobOrdered(Long procedureId, Long orderNo,
                             AmsTaskProcedureDetailTd amsTaskProcedureDetailTd,
                             AmsTaskProcedureParamTd amsTaskProcedureParamTd,
                             AmsTaskProcedureDtlTdSV amsTaskProcedureDtlTdSV,
                             AmsProcedureSV amsProcedureSV) {
        this.procedureId = procedureId;
        this.orderNo = orderNo;

        this.atpDetailTd = amsTaskProcedureDetailTd;
        this.atpParamsTd = amsTaskProcedureParamTd;
        this.procedureId = amsTaskProcedureDetailTd.getProcedureId();
        this.amsTaskProcedureDtlTdSV = amsTaskProcedureDtlTdSV;
        this.amsProcedureSV = amsProcedureSV;
    }


    @Override
    public Boolean call() throws Exception {
        log.info("开始执行 "+procedureId +" ,序号为 : " +orderNo);
        try {
            //1、更新任务到 执行中-02 状态
            amsTaskProcedureDtlTdSV.startTask(procedureId);

            String procedureSQL = atpDetailTd.getProcedureValue();

            // 参数赋值
            if (null != atpParamsTd){
                // 替换存过执行参数
                procedureSQL = procedureSQL.replaceAll(atpParamsTd.getParamKey(),"'"+atpParamsTd.getParamValue()+"'");
            }

            StringBuffer sb = new StringBuffer("");
            /*
             if (StringUtil.countSplit(procedureSQL, ";")>1){
                sb.append("CALL ").append(procedureSQL);
            }else if (StringUtil.countSplit(procedureSQL, ";")>1){
                sb.append("BEGIN ").append(procedureSQL).append(" END;");
            }*/
            //组装 匿名块
            sb.append("BEGIN ").append(procedureSQL).append(" END;");
            // 2、执行 存过
            //amsProcedureSV.callProc(sb.toString());
            TimeUnit.SECONDS.sleep(10);

        }catch (Exception e){
            log.info("--"+Thread.currentThread().getName() +" ORDER EXE EXCEPTION {}" , e);
        }finally {
            log.info("执行 "+procedureId +" ,序号为 : " +orderNo +" 结束了！");

        }
        return true;
    }
}
