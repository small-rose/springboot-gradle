package com.small.demo.threads.works.tasks.impl;

import com.small.demo.threads.works.constant.TaskFlag;
import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;
import com.small.demo.threads.works.service.AmsTaskProDtlCheckSV;
import com.small.demo.threads.works.service.AmsTaskProcedureDtlTdSV;
import com.small.demo.threads.works.tasks.AmsTaskServiceSV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void taskExecute(Long taskId) {

        if (null == taskId){
            log.info("！！！任务号找不到明细，无法执行");
        }
        List<AmsTaskProcedureDetailTd> taskList = amsTaskProcedureDtlTdSV.getDtlListByTaskIdList(taskId);

        for (AmsTaskProcedureDetailTd taskDtl : taskList) {
            if (check(taskDtl)){
                //表示可以執行
            }
        }

    }

    private boolean check(AmsTaskProcedureDetailTd taskDtl) {
        boolean result = true ;
        if (taskDtl==null){
            log.info("校验请求参数有问题！！！");
            return !result ;
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_Policymirror)){
            result = amsTaskProDtlCheckSV.FlagPolicyMirror(taskDtl);
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_Beforemirror)){
            result = amsTaskProDtlCheckSV.FlagBeforemirror(taskDtl);
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_Order)){
            result = amsTaskProDtlCheckSV.FlagOrder(taskDtl);
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_Parallel)){
            result = amsTaskProDtlCheckSV.Flag_Parallel(taskDtl);
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_Syn)){
            result = amsTaskProDtlCheckSV.FlagSyn(taskDtl);
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_Onlyone)){
            result = amsTaskProDtlCheckSV.FlagOnlyone(taskDtl);
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_PolicInsurance)){
            result = amsTaskProDtlCheckSV.FlagPolicInsurance(taskDtl);
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_PolicymirrorHLW)){
            result = amsTaskProDtlCheckSV.FlagPolicyMirrorHLW(taskDtl);
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_SleepStart)){
            result = amsTaskProDtlCheckSV.FlagSleepStart(taskDtl);
        }
        if (result && taskDtl.getProcedureFalg()!=null && taskDtl.getProcedureFalg().contains(TaskFlag.Flag_SleepEnd)){
            result = amsTaskProDtlCheckSV.FlagSleepEnd();
        }
        return result ;
    }
}
