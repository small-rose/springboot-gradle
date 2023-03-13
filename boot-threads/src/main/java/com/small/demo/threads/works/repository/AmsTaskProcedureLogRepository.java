package com.small.demo.threads.works.repository;

import com.small.demo.threads.works.pojo.AmsTaskProcedureLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProcedureLogRepository ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/3/6 15:37
 * @Version ： 1.0
 **/

@Repository
public interface AmsTaskProcedureLogRepository  extends
        JpaRepository<AmsTaskProcedureLog, Long>, JpaSpecificationExecutor<AmsTaskProcedureLog> {


}
