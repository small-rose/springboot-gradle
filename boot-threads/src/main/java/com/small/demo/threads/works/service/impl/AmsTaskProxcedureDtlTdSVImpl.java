package com.small.demo.threads.works.service.impl;

import com.small.demo.threads.works.dao.AmsTaskProcedureDtlTdDao;
import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;
import com.small.demo.threads.works.service.AmsTaskProcedureDtlTdSV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProcedureTdSVImpl ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 15:41
 * @Version ： 1.0
 **/
@Slf4j
@Service
public class AmsTaskProxcedureDtlTdSVImpl implements AmsTaskProcedureDtlTdSV {

    @Autowired
    private AmsTaskProcedureDtlTdDao amsTaskProcedureDtlTdDao ;

    @Override
    public List<AmsTaskProcedureDetailTd> getDtlListByTaskIdList(Long taskId) {
        return amsTaskProcedureDtlTdDao.getDtlListByTaskIdList(taskId);
    }

    @Override
    @Transactional
    public boolean deleteTask(Long procedureId) {

        int count = amsTaskProcedureDtlTdDao.updateifvalid(procedureId);
        if (count!=1){
            log.info("任务仅执行一次，更新任务procedureId = "+procedureId+"的 ifvalid = 0 失败！");
            return false ;
        }
        return true ;
    }

    @Override
    public boolean startTask(Long procedureId) {
        int count = amsTaskProcedureDtlTdDao.updateProcedureStatus(procedureId);
        if (count!=1){
            log.info("开始执行任务，更新任务procedureId = "+procedureId+"的 ifvalid = 0 失败！");
            return false ;
        }
        return true ;
    }
}
