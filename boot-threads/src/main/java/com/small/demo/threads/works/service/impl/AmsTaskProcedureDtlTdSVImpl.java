package com.small.demo.threads.works.service.impl;

import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;
import com.small.demo.threads.works.repository.AmsTaskProcedureDtlTdRepository;
import com.small.demo.threads.works.service.AmsTaskProcedureDtlTdSV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
public class AmsTaskProcedureDtlTdSVImpl implements AmsTaskProcedureDtlTdSV {

    //@Autowired
    //private amsTaskProcedureDtlTdRepository amsTaskProcedureDtlTdRepository ;
    @Autowired
    private AmsTaskProcedureDtlTdRepository amsTaskProcedureDtlTdRepository ;
    @Override
    public List<AmsTaskProcedureDetailTd> getDtlListByTaskIdList(Long taskId) {
        return amsTaskProcedureDtlTdRepository.getDtlListByTaskIdList(taskId);
    }

    @Override
    public AmsTaskProcedureDetailTd getDtlByProcedureId(Long procedureId) {
        return amsTaskProcedureDtlTdRepository.getById(procedureId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean deleteTask(Long procedureId) {
        int count = amsTaskProcedureDtlTdRepository.updateifvalid(procedureId);
        if (count == 1) {
            return true ;
        }
        log.info("任务仅执行一次，更新任务procedureId = " + procedureId + "的 ifvalid = 0 失败！");
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean startTask(Long procedureId) {
        try{
            int count = amsTaskProcedureDtlTdRepository.updateProcedureStatus01(procedureId);
            if (count == 1) {
                return true;
            }
            log.info("开始就绪任务，更新任务procedureId = " + procedureId + "的 status = 01 失败！");
            amsTaskProcedureDtlTdRepository.updateProcedureStatus05(procedureId);
        } catch (Exception e) {
            log.info("更新任务状态到02出错了", e);
            amsTaskProcedureDtlTdRepository.updateProcedureStatus05(procedureId);
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean execTask(Long procedureId) {
        try{
            int count = amsTaskProcedureDtlTdRepository.updateProcedureStatus02(procedureId);
            if (count == 1) {
                return true;
            }
            log.info("开始执行任务，更新任务procedureId = " + procedureId + "的 status = 02 失败！");
            amsTaskProcedureDtlTdRepository.updateProcedureStatus05(procedureId);
        } catch (Exception e) {
            log.info("更新任务状态到02出错了", e);
            amsTaskProcedureDtlTdRepository.updateProcedureStatus05(procedureId);
        }
        return false;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean endTask(Long procedureId) {
        try {
            String executNextDate = amsTaskProcedureDtlTdRepository.getNextDateExpByProcedureId(procedureId);
            Date start = amsTaskProcedureDtlTdRepository.getNextStartDate(executNextDate);
            Date end = null; //把start的日期加1天
            int count = amsTaskProcedureDtlTdRepository.updateProcedureStatus03(procedureId, start, end);
            if (count == 1) {
                return true;
            }
            log.info("开始执行任务，更新任务procedureId = " + procedureId + "的 status = 03 失败！");
            amsTaskProcedureDtlTdRepository.updateProcedureStatus05(procedureId);
        }catch (Exception e){
            log.info("更新任务状态到03出错了", e);
            amsTaskProcedureDtlTdRepository.updateProcedureStatus05(procedureId);
        }
        return false ;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean exceptionTask(Long procedureId) {
        try {
            int count = amsTaskProcedureDtlTdRepository.updateProcedureStatus04(procedureId);
            if (count==1){
                return true ;
            }
            log.info("开始执行任务，更新任务procedureId = "+procedureId+"的 status = 04 失败！");
            amsTaskProcedureDtlTdRepository.updateProcedureStatus05(procedureId);
        }catch (Exception e){
            log.info("更新任务状态到03出错了", e);
            amsTaskProcedureDtlTdRepository.updateProcedureStatus05(procedureId);
        }
        return false ;
    }


}
