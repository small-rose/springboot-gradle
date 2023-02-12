package com.small.demo.threads.works.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AmsTaskProcedureDetailTd ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 16:18
 * @Version ： 1.0
 **/

@Data
public class AmsTaskProcedureDetailTd {


    /** 存储过程主键 **/
    @Id
    @Column( name = "PROCEDURE_ID")
    private Long procedureId ;
    /** 任务编码 **/
    @Column(name = "TASK_ID")
    private BigDecimal taskId ;
    /** 存储过程 **/
    @Column(name = "PROCEDURE_VALUE")
    private String procedureValue ;
    /** 存储过程执行级别 **/
    @Column(name = "PROCEDURE_LEVEL")
    private BigDecimal procedureLevel ;
    /** 存储过程执行顺序 **/
    @Column(name = "PROCEDURE_ORDER")
    private BigDecimal procedureOrder ;
    /** 存储过程执行状态 **/
    @Column(name = "PROCEDURE_STATUS")
    private String procedureStatus ;
    /** 存储过程是否启用 1启用 0不启用 **/
    @Column(name = "IFVALID")
    private BigDecimal ifvalid ;
    /** 参数描述 **/
    @Column(name = "PROCEDURE_DESC")
    private String procedureDesc ;
    /** 允许并发数　Flag_Parallel **/
    @Column(name = "PARALLEL")
    private BigDecimal parallel ;
    /** 任务id **/
    @Column(name = "JOB_ID")
    private BigDecimal jobId ;
    /** 执行的日期 **/
    @Column(name = "EXECUTION_DATE")
    private String executionDate ;
    /** 创建时间 **/
    @Column(name = "CREATE_TIME")
    private Date createTime ;
    /** 执行次数 **/
    @Column(name = "CREATER")
    private BigDecimal creater ;
    /** 存储过程执行状态,OK不走任何校验仅执行一次 **/
    @Column(name = "PROCEDURE_FALG")
    private String procedureFalg ;
    /** 任务开始时间 **/
    @Column(name = "TASKSTARTDATE")
    private Date taskstartdate ;
    /** 任务结束时间 **/
    @Column(name = "TASKENDDATE")
    private Date taskenddate ;
    /** 允许执行的日期区间 **/
    @Column(name = "EXECUTSTART_DATE")
    private Date executstartDate ;
    /** 允许执行的日期区间 **/
    @Column(name = "EXECUTEND_DATE")
    private Date executendDate ;
    /** 允许执行的下一次日期 **/
    @Column(name = "EXECUTNEXT_DATE")
    private String executnextDate ;
    /** 任务时间 **/
    @Column(name = "TASK_DATE")
    private Date taskDate ;
}
