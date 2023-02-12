package com.small.demo.threads.works.dao.impl;

import com.small.demo.threads.works.dao.AmsTaskProcedureDtlTdDao;
import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;
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
public class AmsTaskProcedureDtlTdDaoImpl implements AmsTaskProcedureDtlTdDao {



    @Autowired
    private JdbcTemplate jdbcTemplate ;


    @Override
    public List<AmsTaskProcedureDetailTd> getDtlListByTaskIdList(Long taskId) {
        String sql = " select * from AMS_TASK_PROCEDURE_DETAIL_TD where task_id = "+ taskId;

        return jdbcTemplate.queryForList(sql, AmsTaskProcedureDetailTd.class);
    }

    @Override
    public int updateifvalid(Long procedureId) {
        String sql = " update ams_task_procedure_detail_td t set t.ifvalid='0' where t.procedure_id=" +procedureId +
                "  and t.ifvalid='1' " ;
        int count = jdbcTemplate.update(sql);
        return count ;
    }

    @Override
    public int updateProcedureStatus(Long procedureId) {
        String sql = " update ams_task_procedure_detail_td t set t.procedure_status=''02'',t.taskstartdate=sysdate'||\n" +
                "    ',execution_date=to_char(sysdate,''YYYY-MM-DD'')\n" +
                "     where t.procedure_id="+procedureId+ " and sysdate between executstart_date and executend_date\n" +
                "    and t.procedure_status='01' " ;
        int count = jdbcTemplate.update(sql);
        return count ;
    }

}
