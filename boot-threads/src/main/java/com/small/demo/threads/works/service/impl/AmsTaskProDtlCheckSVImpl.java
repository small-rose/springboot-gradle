package com.small.demo.threads.works.service.impl;

import com.small.demo.threads.works.dao.AmsTaskProDtlCheckDao;
import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;
import com.small.demo.threads.works.pojo.AmsTaskProcedureTd;
import com.small.demo.threads.works.service.AmsTaskProDtlCheckSV;
import com.small.demo.threads.works.service.AmsTaskProcedureDtlTdSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProDtlCheckSV ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 17:14
 * @Version ： 1.0
 **/

@Service
public class AmsTaskProDtlCheckSVImpl implements AmsTaskProDtlCheckSV {

    @Autowired
    private AmsTaskProDtlCheckDao amsTaskProDtlCheckDao ;

    @Autowired
    private AmsTaskProcedureDtlTdSV amsTaskProcedureDtlTdSV ;

    @Override
    public boolean flagPolicyMirror(AmsTaskProcedureDetailTd taskDtl) {

        long count = amsTaskProDtlCheckDao.countPolicyMirror(taskDtl);
        return count > 0;
    }

    @Override
    public boolean flagBeforemirror(AmsTaskProcedureDetailTd taskDtl) {
        long count = amsTaskProDtlCheckDao.countBeforeMirror(taskDtl);
        return count > 0;
    }

    @Override
    public boolean flagOrder(AmsTaskProcedureDetailTd taskDtl) {
        long count = amsTaskProDtlCheckDao.countOrder(taskDtl);
        return count <= 0;
    }

    @Override
    public boolean flag_Parallel(AmsTaskProcedureDetailTd taskDtl) {
        long count = amsTaskProDtlCheckDao.countParallel(taskDtl);
        return count < taskDtl.getParallel();
    }

    @Override
    public boolean flagSyn(AmsTaskProcedureDetailTd taskDtl) {
        long count = amsTaskProDtlCheckDao.countFlagSyn(taskDtl);
        return count > 0;
    }

    @Override
    public boolean flagOnlyone(AmsTaskProcedureDetailTd taskDtl) {
        return amsTaskProcedureDtlTdSV.deleteTask(taskDtl.getProcedureId());
    }

    @Override
    public boolean flagPolicInsurance(AmsTaskProcedureDetailTd taskDtl) {
        long count = amsTaskProDtlCheckDao.countFlagPolicInsurance(taskDtl);
        return count > 0;
    }

    @Override
    public boolean flagPolicyMirrorHLW(AmsTaskProcedureDetailTd taskDtl) {

        long count = amsTaskProDtlCheckDao.countFlagPolicyMirrorHLW(taskDtl);
        return count > 0;
    }

    @Override
    public boolean flagSleepStart(AmsTaskProcedureDetailTd taskDtl) {
        long count = amsTaskProDtlCheckDao.countFlagSleepStart(taskDtl);
        return count <= 0;
    }

    @Override
    public boolean flagSleepEnd() {
        return true;
    }

    @Override
    public boolean flagTaskMaxParallel(AmsTaskProcedureDetailTd taskDtl, AmsTaskProcedureTd procDtlTd) {
        //本任务正在执行的子任务数
        int count = amsTaskProDtlCheckDao.countRunningDtl(taskDtl);

        if (procDtlTd!=null ){
            int maxParallel = procDtlTd.getMaxParallel() ;
            // 0 就是不限制子任务的并发数
            if (maxParallel ==0){
                return true ;
            }
            //大于0 且正在执行线程数在 最大线程数范围内
            if (maxParallel>0 &&  count < maxParallel){
                return true;
            }
        }
        return false;
    }
}
