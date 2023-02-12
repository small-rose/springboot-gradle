package com.small.demo.threads.works.service.impl;

import com.small.demo.threads.works.dao.AmsTaskProcedureTdDao;
import com.small.demo.threads.works.pojo.AmsTaskProcedureTd;
import com.small.demo.threads.works.service.AmsTaskProcedureTdSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProcedureTdSVImpl ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 15:41
 * @Version ： 1.0
 **/
@Service
public class AmsTaskProcedureTdSVImpl implements AmsTaskProcedureTdSV {


    @Autowired
    AmsTaskProcedureTdDao amsTaskProcedureTdDao ;

    @Override
    public List<AmsTaskProcedureTd> getAllProcedureTdList() {
        return amsTaskProcedureTdDao.getAllProcedureTdList();
    }
}
