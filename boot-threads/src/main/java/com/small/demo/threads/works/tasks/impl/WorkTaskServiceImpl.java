package com.small.demo.threads.works.tasks.impl;

import com.small.demo.threads.works.pojo.AmsTaskProcedureTd;
import com.small.demo.threads.works.service.AmsTaskProcedureTdSV;
import com.small.demo.threads.works.tasks.AmsTaskServiceSV;
import com.small.demo.threads.works.tasks.WorkTaskSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ WorkTaskServiceImpl ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 15:36
 * @Version ： 1.0
 **/

@Service
public class WorkTaskServiceImpl implements WorkTaskSV {

    @Autowired
    AmsTaskProcedureTdSV amsTaskProcedureTdSV ;
    @Autowired
    AmsTaskServiceSV amsTaskServiceSV ;


    @Override
    public boolean executeTask() {

        List<AmsTaskProcedureTd> allProcedureTdList = amsTaskProcedureTdSV.getAllProcedureTdList();
        for (AmsTaskProcedureTd procedureTd : allProcedureTdList){
            amsTaskServiceSV.taskExecute(procedureTd.getTaskId());
        }


        return true ;
    }
}
