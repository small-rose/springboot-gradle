package com.small.demo.threads.works.dao.impl;

import com.small.demo.threads.works.dao.AmsTaskProcedureDtlTdDao;
import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
public class AmsTaskProcedureDtlTdDaoImpl implements AmsTaskProcedureDtlTdDao {



    @Autowired
    private JdbcTemplate jdbcTemplate ;


    @Override
    public List<AmsTaskProcedureDetailTd> getDtlListByTaskIdList(Long taskId) {
        String sql = " select * from ams_task_procedure_detail_td d where d.task_id = "+ taskId + " AND d.ifvalid = '1'" +
                " and d.procedure_status='01' and sysdate between d.executstart_date and d.executend_date order by d.procedure_order";
        RowMapper<AmsTaskProcedureDetailTd> rowMapper = new BeanPropertyRowMapper<>(AmsTaskProcedureDetailTd.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public int updateifvalid(Long procedureId) {
        String sql = " update ams_task_procedure_detail_td t set t.ifvalid='0' where t.procedure_id=" +procedureId +
                "  and t.ifvalid='1' " ;
        int count = jdbcTemplate.update(sql);
        return count ;
    }

    @Override
    public int updateProcedureStatus01(Long procedureId) {
        String sql = " update ams_task_procedure_detail_td t set t.procedure_status='01' " +
                "    where t.procedure_id = " + procedureId +
                "    and t.procedure_status in ('03','04') " ;
        int count = jdbcTemplate.update(sql);
        return count ;
    }

    @Override
    public int updateProcedureStatus02(Long procedureId) {
        String sql = " update ams_task_procedure_detail_td t set t.procedure_status='02',t.taskstartdate=sysdate, " +
                "    execution_date=to_char(sysdate,'YYYY-MM-DD') " +
                "    where t.procedure_id = " + procedureId + " and sysdate between executstart_date and executend_date " +
                "    and t.procedure_status = '01' " ;
        int count = jdbcTemplate.update(sql);
        return count ;
    }

    @Override
    public Date getNextDateByProcedureId(Long procedureId) {
        String sql = "select t.executnext_date From ams_task_procedure_detail_td t where t.procedure_id = " +procedureId;
        return jdbcTemplate.queryForObject(sql, Date.class);
    }

    @Override
    public int updateProcedureStatus03(Long procedureId, Date executstart_date, Date executend_date ) {
        String sql = " update ams_task_procedure_detail_td t set t.procedure_status='03', t.taskenddate=sysdate, " +
                "    t.executstart_date= "+executstart_date+" ,t.executend_date= "+executend_date+",creater=nvl(creater,1)+1,execution_date=to_char(sysdate,''YYYY-MM-DD'') " +
                "    where t.procedure_id = "+procedureId+ " and sysdate between executstart_date and executend_date " +
                "    and t.procedure_status = '02' " ;
        int count = jdbcTemplate.update(sql);
        return count ;
    }
    @Override
    public int updateProcedureStatus04(Long procedureId) {
        String sql = " update ams_task_procedure_detail_td t set t.procedure_status='04' " +
                "    where t.procedure_id = " + procedureId +
                "    and t.procedure_status = '02' " ;
        int count = jdbcTemplate.update(sql);
        return count ;
    }

    @Override
    public int updateProcedureStatus05(Long procedureId) {
        String sql = " update ams_task_procedure_detail_td t set t.procedure_status='05' " +
                "    where t.procedure_id = " + procedureId+
                "    and t.procedure_status = '02' " ;
        int count = jdbcTemplate.update(sql);
        return count ;
    }
}
