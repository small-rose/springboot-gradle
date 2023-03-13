package com.small.demo.threads.works.repository;

import com.small.demo.threads.works.pojo.AmsTaskProcedureDetailTd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProcedureDtlTdRepository ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/21 10:08
 * @Version ： 1.0
 **/
@Repository
public interface AmsTaskProcedureDtlTdRepository extends
        JpaRepository<AmsTaskProcedureDetailTd, Long>, JpaSpecificationExecutor<AmsTaskProcedureDetailTd> {
    /*
    @Query(value=" select * from ams_task_procedure_detail_td d where d.task_id = ?1  AND d.ifvalid = '1'  "+
            " and d.procedure_status='01'   and sysdate between d.executstart_date and d.executend_date order by d.procedure_order ",
            nativeQuery = true)
    */
    @Query(value=" select * from ams_task_procedure_detail_td d where d.task_id = ?1  AND d.ifvalid = '1'  "+
            " and ( d.procedure_status='03' or  ( d.procedure_status='04' and d.error_count < d.max_error_count ))  " +
            "and sysdate between d.executstart_date and d.executend_date order by d.procedure_order ",
            nativeQuery = true)
    public List<AmsTaskProcedureDetailTd> getDtlListByTaskIdList(Long taskId);


    @Modifying
    @Query(value="update ams_task_procedure_detail_td t set t.ifvalid='0' where t.procedure_id= ?1 and t.ifvalid='1'", nativeQuery = true)
    public int updateifvalid(Long procedureId);


    @Modifying
    @Query(value  = " update ams_task_procedure_detail_td t set t.procedure_status='01' "+
            "    where t.procedure_id = ?1 and sysdate between executstart_date and executend_date " +
            "    and t.procedure_status in ('03','04')", nativeQuery = true)
    public int updateProcedureStatus01(Long procedureId);

    @Modifying
    @Query(value  = " update ams_task_procedure_detail_td t set t.procedure_status='02',t.taskstartdate=sysdate, " +
            "    t.execution_date=to_char(sysdate,'YYYY-MM-DD') " +
            "    where t.procedure_id = ?1 and sysdate between executstart_date and executend_date " +
            "    and t.procedure_status = '01' ", nativeQuery = true)
    public int updateProcedureStatus02(Long procedureId);


    @Query(value="select t.executnext_date  From ams_task_procedure_detail_td t where t.procedure_id = ?1 ", nativeQuery = true)
    public String getNextDateExpByProcedureId(Long procedureId);

    @Query(value="select ?1  From dual ", nativeQuery = true)
    public Date getNextStartDate(String executNextDate);

    @Modifying
    @Query(value = " update ams_task_procedure_detail_td t set t.procedure_status='03', " +
            "   t.error_count = 0, t.job_id = 0, t.taskenddate=sysdate,t.executstart_date= :executeStartDate ," +
            "   t.executend_date= :executeEndDate, creater=nvl(creater,1)+1, execution_date=to_char(sysdate,'YYYY-MM-DD') " +
            "    where t.procedure_id = :procedureId  and sysdate between executstart_date and executend_date " +
            "    and t.procedure_status = '02' ", nativeQuery = true)
    public int updateProcedureStatus03(@Param("procedureId")Long procedureId,
                                @Param("executeStartDate") Date executeStartDate,
                                @Param("executeEndDate") Date executeEndDate);


    @Modifying
    @Query(value  = " update ams_task_procedure_detail_td t set t.procedure_status='04'," +
            "    t.error_count = nvl(t.error_count,1) + 1 " +
            "    where t.procedure_id = ?1 and t.procedure_status = '02' ", nativeQuery = true)
    public int updateProcedureStatus04(Long procedureId);


    @Modifying
    @Query(value  = " update ams_task_procedure_detail_td t set t.procedure_status='05' " +
            "    where t.procedure_id = ?1 and t.procedure_status = '02' ", nativeQuery = true)
    public int updateProcedureStatus05(Long procedureId);


}
