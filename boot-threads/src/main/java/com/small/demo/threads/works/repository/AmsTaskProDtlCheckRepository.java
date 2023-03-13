package com.small.demo.threads.works.repository;

import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProDtlCheckRepository ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/21 13:55
 * @Version ： 1.0
 **/
@Repository
public interface AmsTaskProDtlCheckRepository extends
        JpaRepository<AmsTaskProcedureDetailTd, Long>, JpaSpecificationExecutor<AmsTaskProcedureDetailTd> {

    @Query(value = "select count(1) From ams_mirror_td t where t.mirrortype='4' and " +
            "    t.status in ('1','2') and t.mirrordate=trunc(sysdate)-1" +
            "    and  exists (" +
            "    select 1 From ams_task_procedure_param_td tq where tq.procedure_id = :#{#detailTd.procedureId} and tq.param_value=t.subcompany " +
            "    )", nativeQuery = true)
    int countPolicyMirror(@Param("detailTd") AmsTaskProcedureDetailTd detailTd);


    @Query(value = "select count(1) From ams_mirror_td t where t.mirrortype='5' and " +
            "    t.status in ('1','2') and t.mirrordate=trunc(sysdate)-1 " +
            "    and  exists (" +
            "    select 1 From ams_task_procedure_param_td tq where tq.procedure_id = :#{#detailTd.procedureId} and tq.param_value=t.subcompany " +
            "    )", nativeQuery = true)
    int countBeforeMirror(@Param("detailTd") AmsTaskProcedureDetailTd detailTd);

    @Query(value = "select count(1)  from ams_task_procedure_detail_td d " +
            "  where d.ifvalid = '1' " +
            "  and d.task_id= :#{#detailTd.taskId} and d.procedure_order < :#{#detailTd.procedureOrder} " +
            "  and sysdate between d.executstart_date and d.executend_date " +
            "  order by d.procedure_order", nativeQuery = true)
    int countOrder(@Param("detailTd") AmsTaskProcedureDetailTd detailTd);


    @Query(value = "select count(1) from ams_task_procedure_detail_td d" +
            "  where d.procedure_status in ('02') " +
            "  and d.task_id = :#{#detailTd.taskId}  and d.procedure_level = :#{#detailTd.procedureLevel}" +
            "  and sysdate between d.executstart_date and d.executend_date " +
            "  and d.ifvalid = '1' " +
            "  order by d.procedure_order", nativeQuery = true)
    int countParallel(@Param("detailTd") AmsTaskProcedureDetailTd detailTd);

    @Query(value = "select count(1)  " +
            "  From ams_task_procedure_detail_td d " +
            "  where d.procedure_status in ('02','01') " +
            "  and d.task_id = :#{#detailTd.taskId} and d.procedure_level = :#{#detailTd.procedureLevel}" +
            "  and nvl(d.creater,1) < nvl(:#{#detailTd.creater},1) " +
            "  and d.ifvalid='1' " +
            "  order by d.procedure_order", nativeQuery = true)
    int countFlagSyn(@Param("detailTd") AmsTaskProcedureDetailTd detailTd);


    @Query(value = "select count(1) " +
            "  from ams_task_procedure_detail_td d " +
            "  where d.ifvalid = '1' " +
            "  and d.task_id = :#{#detailTd.taskId}" +
            "  and d.procedure_order = :#{#detailTd.procedureOrder} " +
            "  and d.procedure_level = :#{#detailTd.procedureLevel} "+
            "  and d.executstart_date between i_rec.executstart_date and i_rec.executend_date " +
            "  and instr(d.procedure_falg,'[Flag_SleepEnd]')>0", nativeQuery = true)
    int countFlagSleepStart(@Param("detailTd") AmsTaskProcedureDetailTd detailTd);

    @Query(value = "select count(1) " +
            "      From ams_policyinsurancematch_ti t " +
            "      where t.status in( '0','2','3') " +
            "           and " +
            "           exists ( " +
            "           select 1 From ams_switchcontrol_detail_tc t1 where t1.name='BASE_DB_ACCT' and t1.value=t.subcompany " +
            "           and " +
            "             exists ( " +
            "             select 1 from ams_sysdefaultset_tc t2 where t2.sysdbcode=t1.value1 " +
            "             ) " +
            "           )", nativeQuery = true)
    int countFlagPolicInsurance(@Param("detailTd") AmsTaskProcedureDetailTd detailTd);

    @Query(value = "select count(1) " +
            "      from ams_mirror_detail_td t1 " +
            "     where exists (select 1 " +
            "              From ams_task_procedure_param_td tq " +
            "             where tq.procedure_id = :#{#detailTd.procedureId} " +
            "               and tq.param_value = t1.subcompany) " +
            "       and t1.mirrordate = trunc(sysdate) - 1 " +
            "       and t1.status = '2' " +
            "       and procname = '搬迁互联网保单数据'", nativeQuery = true)
    int countFlagPolicyMirrorHLW(@Param("detailTd") AmsTaskProcedureDetailTd detailTd);


}
