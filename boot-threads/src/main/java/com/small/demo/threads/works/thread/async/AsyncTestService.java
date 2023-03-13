package com.small.demo.threads.works.thread.async;

import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;
import com.small.demo.threads.works.pojo.AmsTaskProcedureParamTd;
import com.small.demo.threads.works.service.AmsProcedureSV;
import com.small.demo.threads.works.service.AmsTaskProcedureDtlTdSV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AsyncWorkService ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/20 14:26
 * @Version ： 1.0
 **/
@Slf4j
@Component
public class AsyncTestService {

    //执行存过ID
    private Long procedureId ;
    // 执行任务对象实例
    private AmsTaskProcedureDetailTd atpDetailTd  ;
    // 执行参数对象实例
    private AmsTaskProcedureParamTd atpParamsTd  ;

    @Autowired
    private AmsTaskProcedureDtlTdSV amsTaskProcedureDtlTdSV ;
    @Autowired
    private AmsProcedureSV amsProcedureSV ;


    @Async("pgkThreadPool")
    public void exeWork(Long procedureId ,AmsTaskProcedureDetailTd atpDetailTd, AmsTaskProcedureParamTd atpParamsTd ){
        log.info("开始执行 "+procedureId +" ");
        try {

            //1、更新任务到 执行中-02 状态
            log.info("开始执行任务，即将更新 {} 的状态为 执行中-02 ", procedureId);
            amsTaskProcedureDtlTdSV.startTask(procedureId);
            log.info("更新 {} 的状态为 执行中-02 成功", procedureId);
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
            amsProcedureSV.callProc(sb.toString());


            // 3、执行完成， 更新执行完成的状态
            amsTaskProcedureDtlTdSV.endTask(procedureId);
            log.info("执行任务正常结束 {} 的状态为 执行完成-03 ", procedureId);
        }catch (Exception e){
            log.info("--"+Thread.currentThread().getName() +" EXE EXCEPTION {}" , e);

            // 3、执行完成， 更新执行异常的状态
            amsTaskProcedureDtlTdSV.exceptionTask(procedureId);
            log.info("执行任务正常结束 {} 的状态为 执行异常-04 ", procedureId);
        }finally {


            log.info("执行 "+procedureId +" 结束 ！");
        }
    }
}
