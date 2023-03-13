package com.small.demo.threads.works.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProcedureTd ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 15:56
 * @Version ： 1.0
 **/

@Entity
@Table(name = "AMS_TASK_PROCEDURE_TD")
@Data
public class AmsTaskProcedureTd {



    /** 任务id **/
    @Id
    @Column(name="TASK_ID"/* , type=IdType.INPUT*/)
    private Long taskId ;



    /** 存储过程 **/
    @Column(name="MAX_PARALLEL")
    private int maxParallel ;
    /** 存储过程 **/
    @Column(name="PROCEDURE_VALUE")
    private String procedureValue ;

    /** 存储过程执行级别 **/
    @Column(name="PROCEDURE_LEVEL")
    private BigDecimal procedureLevel ;

    /** 存储过程执行顺序 **/
    @Column(name="PROCEDURE_ORDER")
    private BigDecimal procedureOrder ;

    /** 存储过程执行状态00初始，01待执行，02正在执行，03执行完成 **/
    @Column(name="PROCEDURE_STATUS")
    private String procedureStatus ;

    /** 存储过程是否启用1启用0不启用 **/
    @Column(name="IFVALID")
    private BigDecimal ifvalid ;

    /** 参数描述 **/
    @Column(name="PROCEDURE_DESC")
    private String procedureDesc ;
    /** 执行的日期 **/
    @Column(name="EXECUTION_DATE")
    private String executionDate ;
    /** 0串行1并行 **/
    @Column(name="IFPARALLEL")
    private BigDecimal ifparallel ;
    /** 创建时间 **/
    @Column(name="CREATE_TIME")
    private Date createTime ;
    /** 创建人 **/
    @Column(name="CREATER")
    private BigDecimal creater ;
}
