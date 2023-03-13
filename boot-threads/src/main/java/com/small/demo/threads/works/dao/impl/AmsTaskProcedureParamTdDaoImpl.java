package com.small.demo.threads.works.dao.impl;

import com.small.demo.threads.works.dao.AmsTaskProcedureParamTdDao;
import com.small.demo.threads.works.pojo.AmsTaskProcedureParamTd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProcedureParamTdDaoImpl ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/20 16:08
 * @Version ： 1.0
 **/

@Repository
public class AmsTaskProcedureParamTdDaoImpl implements AmsTaskProcedureParamTdDao {

    @Autowired
    private JdbcTemplate jdbcTemplate ;


    @Override
    public List<AmsTaskProcedureParamTd> getAmsTaskProcedureParamTdList() {
        // 查开启的任务，
        String sql = " select * from AMS_TASK_PROCEDURE_PARAM_TD " ;
        RowMapper<AmsTaskProcedureParamTd> rowMapper = new BeanPropertyRowMapper<>(AmsTaskProcedureParamTd.class);
        return jdbcTemplate.query(sql, rowMapper);
    }
}
