package com.small.demo.threads.works.repository;

import com.small.demo.threads.works.pojo.AmsTaskProcedureTd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProcedureParamTdRepository ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/21 13:48
 * @Version ： 1.0
 **/
@Repository
public interface AmsTaskProcedureTdRepository extends
        JpaRepository<AmsTaskProcedureTd, Long>, JpaSpecificationExecutor<AmsTaskProcedureTd> {


    @Query(value = " select * from AMS_TASK_PROCEDURE_TD where d.ifvalid = '1'  order by d.procedure_order",
            nativeQuery = true)
    public List<AmsTaskProcedureTd> getAllProcedureTdList();
}
