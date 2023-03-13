package com.small.demo.threads.works.dao.impl;

import com.small.demo.threads.works.dao.AmsTaskProcedureTdDao;
import com.small.demo.threads.works.pojo.AmsTaskProcedureTd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
        // 查开启的任务， 暂将 2 设定为 启用
        String sql = " select * from AMS_TASK_PROCEDURE_TD d where d.ifvalid = '2' " +
                " order by d.procedure_order" ;
        RowMapper<AmsTaskProcedureTd> rowMapper = new BeanPropertyRowMapper<>(AmsTaskProcedureTd.class);
        return jdbcTemplate.query(sql, rowMapper);
    }
}
