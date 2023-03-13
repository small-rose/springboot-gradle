package com.small.demo.threads.works.tasks.impl;

import com.small.demo.threads.custom.meta.CustomRejectedExecutionHandler;
import com.small.demo.threads.works.constant.TaskFlag;
import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;
import com.small.demo.threads.works.pojo.AmsTaskProcedureParamTd;
import com.small.demo.threads.works.pojo.AmsTaskProcedureTd;
import com.small.demo.threads.works.service.AmsProcedureSV;
import com.small.demo.threads.works.service.AmsTaskProDtlCheckSV;
import com.small.demo.threads.works.service.AmsTaskProcedureDtlTdSV;
import com.small.demo.threads.works.service.AmsTaskProcedureParamTdSV;
import com.small.demo.threads.works.tasks.AmsTaskServiceSV;
import com.small.demo.threads.works.thread.ExecuteJob;
import com.small.demo.threads.works.thread.async.AsyncTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskServiceImpl ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 15:22
 * @Version ： 1.0
 **/

@Slf4j
@Service
public class AmsTaskServiceImpl implements AmsTaskServiceSV {


    @Autowired
    private AmsTaskProcedureDtlTdSV amsTaskProcedureDtlTdSV ;
    @Autowired
    private AmsTaskProDtlCheckSV amsTaskProDtlCheckSV ;
    @Autowired
    private AmsTaskProcedureParamTdSV amsTaskProcedureParamTdSV ;
    @Autowired
    private AmsProcedureSV amsProcedureSV ;
    @Autowired
    private AsyncTestService asyncTestService ;

    private static ThreadPoolTaskExecutor taskExecutor;
    /**
     * 核心线程数
     */
    private int corePoolSize = Runtime.getRuntime().availableProcessors() ;

    /**
     * 最大线程数
     */
    private int maxPoolSize = Runtime.getRuntime().availableProcessors() * 2 ;

    private int  queueCapacity = 500 ;

    @PostConstruct
    public void init(){
        taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);//核心线程大小
        taskExecutor.setMaxPoolSize(maxPoolSize);//最大线程大小
        taskExecutor.setQueueCapacity(queueCapacity);//队列最大容量
        //当提交的任务个数大于QueueCapacity，就需要设置该参数，但spring提供的都不太满足业务场景，可以自定义一个，也可以注意不要超过QueueCapacity即可
        taskExecutor.setRejectedExecutionHandler(new CustomRejectedExecutionHandler());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60);
        taskExecutor.setKeepAliveSeconds(60);//默认就是60,非核心线程空闲60s就会自动销毁
        taskExecutor.setThreadNamePrefix("PkgCallTask-");

        taskExecutor.initialize();
        log.info("初始化线程池 pgkThreadPool ");
    }



    public void initParams(Map<Long, AmsTaskProcedureParamTd> paramsMap) {
        paramsMap.clear();
        List<AmsTaskProcedureParamTd> paramTdList = amsTaskProcedureParamTdSV.getAmsTaskProcedureParamTdList();
        for (AmsTaskProcedureParamTd paramTd: paramTdList) {
            paramsMap.put(paramTd.getProcedureId(), paramTd);
        }
        log.info("初始化执行参数成功！");
    }

    @Override
    public void taskExecute(AmsTaskProcedureTd procDtlTd) {

        if (null == procDtlTd){
            log.info("！！！任务号找不到明细，无法执行");
            return;
        }
        log.info("任务号 {}，开始执行" , procDtlTd);
        List<AmsTaskProcedureDetailTd> taskList = amsTaskProcedureDtlTdSV.getDtlListByTaskIdList(procDtlTd.getTaskId());
        log.info("任务 {} 有 {} 子任务", procDtlTd.getTaskId(), taskList.size());

        List<AmsTaskProcedureDetailTd> exeTaskList= new ArrayList<>();
        Map<Long, AmsTaskProcedureParamTd> paramsMap = new HashMap<>();
        initParams(paramsMap);


        for (AmsTaskProcedureDetailTd taskDtl : taskList) {
            if (check(taskDtl, procDtlTd)){
                // 将线程更新到已就绪状态 01，防止重复提交
                amsTaskProcedureDtlTdSV.startTask(taskDtl.getProcedureId());

                // 提交线程任务
                AmsTaskProcedureParamTd paramTd = paramsMap.get(taskDtl.getProcedureId());
                ExecuteJob job = new ExecuteJob(taskDtl, paramTd, amsTaskProcedureDtlTdSV, amsProcedureSV);
                taskExecutor.submit(job);
                log.info("提交了一个任务: procedureId = {}" , taskDtl.getProcedureId());
                exeTaskList.add(taskDtl);
            }
        }
        log.info("筛选出 {} 个任务！！！" , exeTaskList.size());
        exeTaskList.clear();
        paramsMap.clear();

        //taskExecutor.shutdown();

    }



    private boolean check(AmsTaskProcedureDetailTd taskDtl, AmsTaskProcedureTd procDtlTd) {
        boolean result = true ;
        if (taskDtl==null){
            log.info("校验请求参数有问题！！！");
            return !result ;
        }
        if (result){
            result = amsTaskProDtlCheckSV.flagTaskMaxParallel(taskDtl, procDtlTd);
            log.info("校验任务{} 允许的最大并发数判断 {}", taskDtl.getTaskId(), result);
        }
        if (result){
            result = amsTaskProDtlCheckSV.flagTaskMaxParallel(taskDtl, procDtlTd);
            log.info("校验任务{} 允许的最大并发数判断 {}", taskDtl.getTaskId(), result);
        }
        Long procedureId = taskDtl.getProcedureId();
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_Policymirror)){
            result = amsTaskProDtlCheckSV.flagPolicyMirror(taskDtl);
            log.info("procedureId {} 台账完成判断 {}", procedureId, result);
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_Beforemirror)){
            result = amsTaskProDtlCheckSV.flagBeforemirror(taskDtl);
            log.info("procedureId {} 未起保台账完成判断 {}" , procedureId, result);
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_Order)){
            result = amsTaskProDtlCheckSV.flagOrder(taskDtl);
            log.info("procedureId {} 执行顺序判断 {}" , procedureId, result);
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_Parallel)){
            result = amsTaskProDtlCheckSV.flag_Parallel(taskDtl);
            log.info("procedureId {} 并发数判断 {}" , procedureId, result);

        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_Syn)){
            result = amsTaskProDtlCheckSV.flagSyn(taskDtl);
            log.info("procedureId {} 同步执行判断 {}" , procedureId, result);
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_Onlyone)){
            result = amsTaskProDtlCheckSV.flagOnlyone(taskDtl);
            log.info("procedureId {} 仅执行一次 {}" , procedureId, result);
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_PolicInsurance)){
            result = amsTaskProDtlCheckSV.flagPolicInsurance(taskDtl);
            log.info("procedureId {} 判断是否有共保数据需要落地 {}" , procedureId, result);
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_PolicymirrorHLW)){
            result = amsTaskProDtlCheckSV.flagPolicyMirrorHLW(taskDtl);
            log.info("procedureId {} 互联网应收完成判断 {}" , procedureId, result);
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_SleepStart)){
            result = amsTaskProDtlCheckSV.flagSleepStart(taskDtl);
            log.info("procedureId {} SleepStart 判断 {}" , procedureId, result);
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_SleepEnd)){
            result = amsTaskProDtlCheckSV.flagSleepEnd();
            log.info("procedureId {} SleepEnd 判断 {}" , procedureId, result);
        }
        return result ;
    }
}
