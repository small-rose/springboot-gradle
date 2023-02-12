package com.small.demo.threads.works.dao.impl;

import com.small.demo.threads.works.dao.AmsTaskProcedureTdDao;
import com.small.demo.threads.works.pojo.AmsTaskProcedureTd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProcedureTdDao ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 15:32
 * @Version ： 1.0
 **/
@Repository
public class AmsTaskProcedureTdDaoImpl implements AmsTaskProcedureTdDao {



    @Autowired
    private JdbcTemplate jdbcTemplate ;

    @Override
    public List<AmsTaskProcedureTd> getAllProcedureTdList() {
        String sql = " select * from AMS_TASK_PROCEDURE_TD " ;

        return jdbcTemplate.queryForList(sql, AmsTaskProcedureTd.class);
    }
}
