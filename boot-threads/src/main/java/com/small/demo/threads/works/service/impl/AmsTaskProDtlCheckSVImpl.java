package com.small.demo.threads.works.service.impl;

import com.small.demo.threads.works.dao.AmsTaskProDtlCheckDao;
import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;
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
    public boolean FlagPolicyMirror(AmsTaskProcedureDetailTd taskDtl) {

        long count = amsTaskProDtlCheckDao.countPolicyMirror(taskDtl);
        return count > 0;
    }

    @Override
    public boolean FlagBeforemirror(AmsTaskProcedureDetailTd taskDtl) {
        long count = amsTaskProDtlCheckDao.countBeforeMirror(taskDtl);
        return count > 0;
    }

    @Override
    public boolean FlagOrder(AmsTaskProcedureDetailTd taskDtl) {
        long count = amsTaskProDtlCheckDao.countOrder(taskDtl);
        return count <= 0;
    }

    @Override
    public boolean Flag_Parallel(AmsTaskProcedureDetailTd taskDtl) {
        long count = amsTaskProDtlCheckDao.countParallel(taskDtl);
        return count < taskDtl.getParallel().doubleValue();
    }

    @Override
    public boolean FlagSyn(AmsTaskProcedureDetailTd taskDtl) {
        long count = amsTaskProDtlCheckDao.countFlagSyn(taskDtl);
        return count > 0;
    }

    @Override
    public boolean FlagOnlyone(AmsTaskProcedureDetailTd taskDtl) {
        return amsTaskProcedureDtlTdSV.deleteTask(taskDtl.getProcedureId());
    }

    @Override
    public boolean FlagPolicInsurance(AmsTaskProcedureDetailTd taskDtl) {
        long count = amsTaskProDtlCheckDao.countFlagPolicInsurance(taskDtl);
        return count > 0;
    }

    @Override
    public boolean FlagPolicyMirrorHLW(AmsTaskProcedureDetailTd taskDtl) {

        long count = amsTaskProDtlCheckDao.countFlagPolicyMirrorHLW(taskDtl);
        return count > 0;
    }

    @Override
    public boolean FlagSleepStart(AmsTaskProcedureDetailTd taskDtl) {
        long count = amsTaskProDtlCheckDao.countFlagSleepStart(taskDtl);
        return count <= 0;
    }

    @Override
    public boolean FlagSleepEnd() {
        return true;
    }
}
