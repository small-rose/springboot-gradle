package com.small.demo.threads.works.service.impl;

import com.small.demo.threads.works.dao.AmsTaskProcedureParamTdDao;
import com.small.demo.threads.works.pojo.AmsTaskProcedureParamTd;
import com.small.demo.threads.works.service.AmsTaskProcedureParamTdSV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProcedureParamTdSVImpl ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/20 16:06
 * @Version ： 1.0
 **/
@Slf4j
@Service
public class AmsTaskProcedureParamTdSVImpl implements AmsTaskProcedureParamTdSV {

    @Autowired
    private AmsTaskProcedureParamTdDao amsTaskProcedureParamTdDao ;

    @Override
    public List<AmsTaskProcedureParamTd> getAmsTaskProcedureParamTdList() {
        return  amsTaskProcedureParamTdDao.getAmsTaskProcedureParamTdList();
    }
}
