package com.small.demo.threads.works.dao.impl;

import com.small.demo.threads.works.dao.AmsTaskProDtlCheckDao;
import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProDtlCheckDaoImpl ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 17:52
 * @Version ： 1.0
 **/
@Repository
public class AmsTaskProDtlCheckDaoImpl implements AmsTaskProDtlCheckDao {

    @Autowired
    private JdbcTemplate jdbcTemplate ;

    @Override
    public Long countPolicyMirror(AmsTaskProcedureDetailTd detailTd) {
        String sql = "select count(1) " +
                "    From ams_policyinsurancematch_ti t\n" +
                "    where t.status in( '0','2','3')\n" +
                "    and exists (\n" +
                "           select 1 From ams_switchcontrol_detail_tc t1 where t1.name='BASE_DB_ACCT' and t1.value=t.subcompany\n" +
                "       and  exists (\n" +
                "             select 1 from ams_sysdefaultset_tc t2 where t2.sysdbcode=t1.value1\n" +
                "             )\n" +
                "           )";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public Long countBeforeMirror(AmsTaskProcedureDetailTd detailTd) {
        String sql = "select count(1) From ams_mirror_td t where t.mirrortype='5' and\n" +
                "    t.status in ('1','2') and t.mirrordate=trunc(sysdate)-1\n" +
                "    and  exists (\n" +
                "    select 1 From ams_task_procedure_param_td tq where tq.procedure_id="+detailTd.getProcedureId()+" and tq.param_value=t.subcompany\n" +
                "    )";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public Long countOrder(AmsTaskProcedureDetailTd detailTd) {
        String sql = "select count(1)  from ams_task_procedure_detail_td d\n" +
                "  where d.ifvalid = '1'\n" +
                "  and d.task_id=" + detailTd.getTaskId()+
                "  and d.procedure_order< " +detailTd.getProcedureOrder() +
                "  and sysdate between d.executstart_date and d.executend_date\n" +
                "  order by d.procedure_order";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public Long countParallel(AmsTaskProcedureDetailTd detailTd) {
        String sql = "select count(1) into count_flag\n" +
                "   from ams_task_procedure_detail_td d\n" +
                "  where d.procedure_status in ('02')\n" +
                "  and d.task_id= " + detailTd.getTaskId()+
                "  and d.procedure_level= " + detailTd.getProcedureLevel() +
                "  and sysdate between d.executstart_date and d.executend_date\n" +
                "  and d.ifvalid='1'\n" +
                "  order by d.procedure_order";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public long countFlagSyn(AmsTaskProcedureDetailTd detailTd) {
        String sql = "select count(1)  " +
                "  From ams_task_procedure_detail_td d\n" +
                "  where d.procedure_status in ('02','01')\n" +
                "  and d.task_id= " + detailTd.getTaskId()+
                "  and d.procedure_level= " + detailTd.getProcedureLevel() +
                "  and nvl(d.creater,1)<nvl("+detailTd.getCreater()+",1)\n" +
                "  and d.ifvalid='1' " +
                "  order by d.procedure_order";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public long countFlagSleepStart(AmsTaskProcedureDetailTd detailTd) {
        String sql = "select count(1) " +
                "  from ams_task_procedure_detail_td d\n" +
                "  where d.ifvalid = '1'\n" +
                "  and d.task_id=" + detailTd.getTaskId()+
                "  and d.procedure_order=" + detailTd.getProcedureOrder() +
                "  and d.procedure_level=" + detailTd.getProcedureLevel() +
                "  and d.executstart_date between i_rec.executstart_date and i_rec.executend_date\n" +
                "  and instr(d.procedure_falg,'[Flag_SleepEnd]')>0";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public long countFlagPolicInsurance(AmsTaskProcedureDetailTd detailTd) {
        String sql = "select count(1) " +
                "      From ams_policyinsurancematch_ti t\n" +
                "      where t.status in( '0','2','3')\n" +
                "           and\n" +
                "           exists (\n" +
                "           select 1 From ams_switchcontrol_detail_tc t1 where t1.name='BASE_DB_ACCT' and t1.value=t.subcompany\n" +
                "           and\n" +
                "             exists (\n" +
                "             select 1 from ams_sysdefaultset_tc t2 where t2.sysdbcode=t1.value1\n" +
                "             )\n" +
                "           )";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public long countFlagPolicyMirrorHLW(AmsTaskProcedureDetailTd detailTd) {
        String sql = "select count(1) " +
                "      from ams_mirror_detail_td t1\n" +
                "     where exists (select 1\n" +
                "              From ams_task_procedure_param_td tq\n" +
                "             where tq.procedure_id = " + detailTd.getProcedureId() +
                "               and tq.param_value = t1.subcompany)\n" +
                "       and t1.mirrordate = trunc(sysdate) - 1\n" +
                "       and t1.status = '2'\n" +
                "       and procname = '搬迁互联网保单数据'";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }


}
