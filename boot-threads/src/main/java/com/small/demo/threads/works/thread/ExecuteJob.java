package com.small.demo.threads.works.thread;

import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;
import com.small.demo.threads.works.pojo.AmsTaskProcedureLog;
import com.small.demo.threads.works.pojo.AmsTaskProcedureParamTd;
import com.small.demo.threads.works.service.AmsProcedureSV;
import com.small.demo.threads.works.service.AmsTaskProcedureDtlTdSV;
import com.small.demo.threads.works.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;

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
public class ExecuteJob implements Runnable {

    //执行存过ID
    private Long procedureId;
    // 执行任务对象实例
    private AmsTaskProcedureDetailTd atpDetailTd;
    // 执行参数对象实例
    private AmsTaskProcedureParamTd atpParamsTd;

    private AmsTaskProcedureDtlTdSV amsTaskProcedureDtlTdSV;
    private AmsProcedureSV amsProcedureSV;

    public ExecuteJob(AmsTaskProcedureDetailTd amsTaskProcedureDetailTd,
                      AmsTaskProcedureParamTd amsTaskProcedureParamTd,
                      AmsTaskProcedureDtlTdSV amsTaskProcedureDtlTdSV,
                      AmsProcedureSV amsProcedureSV) {
        this.atpDetailTd = amsTaskProcedureDetailTd;
        this.atpParamsTd = amsTaskProcedureParamTd;
        this.procedureId = amsTaskProcedureDetailTd.getProcedureId();
        this.amsTaskProcedureDtlTdSV = amsTaskProcedureDtlTdSV;
        this.amsProcedureSV = amsProcedureSV;
    }


    @Override
    public void run() {
        long a = System.currentTimeMillis();
        log.info("开始执行 " + procedureId + " , 线程ID是 " + Thread.currentThread().getId()
                + " , 线程名称是 " + Thread.currentThread().getName());
        AmsTaskProcedureLog procLog = new AmsTaskProcedureLog();
        try {
            procLog.setTaskId(atpDetailTd.getTaskId());
            procLog.setProcedureId(procedureId);
            procLog.setThreadId(Thread.currentThread().getId());
            procLog.setThreadName(Thread.currentThread().getName());

            //1、更新任务到 执行中-02 状态
            amsTaskProcedureDtlTdSV.execTask(procedureId);
            // 2. 准备参数
            String procedureSQL = atpDetailTd.getProcedureValue();
            // 参数赋值
            if (null != atpParamsTd) {
                // 替换存过执行参数
                procedureSQL = procedureSQL.replaceAll(atpParamsTd.getParamKey(), "'" + atpParamsTd.getParamValue() + "'");
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
            procLog.setProcedureValue(sb.toString());
            try {
                // 3、执行 存过
                amsProcedureSV.callProc(sb.toString());
                log.info("procedureId 执行存过 ：{} ", sb.toString());
                TimeUnit.SECONDS.sleep(10);

                // 4.1 执行完成， 更新执行完成的状态
                amsTaskProcedureDtlTdSV.endTask(procedureId);
                procLog.setExeResult("03");
                log.info("执行 " + procedureId + " 结束 ！");

            } catch (Exception e) {
                // 4.2 执行存过出错， 更新执行执行出错 04 的状态
                amsTaskProcedureDtlTdSV.exceptionTask(procedureId);
                procLog.setExeResult("04-执行异常-内");
                String stackTrace = ExceptionUtils.getFullStackTrace(e);
                if (stackTrace.length() > 2000) {
                    stackTrace = stackTrace.substring(0, 2000);
                }
                procLog.setErrorInfo(stackTrace);
                log.info("执行 " + procedureId + " 的存过小异常 ！");
                e.printStackTrace();
            }

        } catch (Exception e) {
            log.info("--" + Thread.currentThread().getName() + " EXE EXCEPTION {}", e);
            amsTaskProcedureDtlTdSV.exceptionTask(procedureId);
            procLog.setExeResult("04-执行异常-外");
            String stackTrace = ExceptionUtils.getFullStackTrace(e);
            if (stackTrace.length() > 2000) {
                stackTrace = stackTrace.substring(0, 2000);
            }
            procLog.setErrorInfo(stackTrace);
            log.info("执行 " + procedureId + " 的存过出现大异常 ！");
        } finally {
            long b = System.currentTimeMillis();
            procLog.setTakeTime(DateUtils.dateDiff(a, b));

            AmsTaskProcedureDetailTd procDtlTd = amsTaskProcedureDtlTdSV.getDtlByProcedureId(procedureId);
            procLog.setTaskstartdate(procDtlTd.getTaskstartdate());
            procLog.setTaskenddate(procDtlTd.getTaskenddate());
            //procLog.setLogId(???);
            amsProcedureSV.save(procLog);
            log.info("save exe log success ! 保存执行日志成功");
        }
    }
}
