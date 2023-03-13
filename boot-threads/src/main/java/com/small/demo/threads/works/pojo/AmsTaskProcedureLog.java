package com.small.demo.threads.works.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "AMS_TASK_PROCEDURE_LOG")
public class AmsTaskProcedureLog {

    @Id
    @Column( name = "LOG_ID")
    private Long logId ;
    /** 任务编码 **/
    @Column(name = "TASK_ID")
    private Integer taskId ;
    /** 存储过程主键 **/
    @Column( name = "PROCEDURE_ID")
    private Long procedureId ;

    /** 存储过程 **/
    @Column(name = "PROCEDURE_VALUE")
    private String procedureValue ;


    /** 线程ID **/
    @Column( name = "THREAD_ID")
    private Long threadId ;

    /** 线程名称 **/
    @Column(name = "THREAD_NAME")
    private String threadName ;

    /** 线程名称 **/
    @Column(name = "EXE_RESULT")
    private String exeResult ;
    /** 线程名称 **/
    @Column(name = "ERROR_INFO")
    private String errorInfo ;


    /** 任务开始时间 **/
    @Column(name = "TASKSTARTDATE")
    private Date taskstartdate ;
    /** 任务结束时间 **/
    @Column(name = "TASKENDDATE")
    private Date taskenddate ;
    /** 执行耗时  **/
    @Column(name = "TAKE_TIME")
    private String takeTime ;
    /** 执行耗时  **/
    @Column(name = "LASTOPDATE")
    private Date lastopdate ;


}
