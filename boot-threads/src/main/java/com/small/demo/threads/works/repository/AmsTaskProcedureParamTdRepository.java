package com.small.demo.threads.works.repository;

import com.small.demo.threads.works.pojo.AmsTaskProcedureParamTd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProcedureParamTdRepository ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/21 13:48
 * @Version ： 1.0
 **/
@Repository
public interface AmsTaskProcedureParamTdRepository extends
        JpaRepository<AmsTaskProcedureParamTd, Long>, JpaSpecificationExecutor<AmsTaskProcedureParamTd> {



}
