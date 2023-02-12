package com.small.demo.threads.works.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ AMS_TASK_PROCEDURE_PARAM_TD ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/8 18:13
 * @Version ： 1.0
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AmsTaskProcedureParamTd {

    @Id
    @Column( name = "PARAM_ID")
    private Long paramId ;
    /** 存储过程主键 **/
    @Column( name = "PROCEDURE_ID")
    private Long procedureId ;


    @Column(name = "PARAM_KEY")
    private String paramKey ;

    @Column(name = "PARAM_VALUE")
    private String paramValue ;

    @Column(name = "PARAM_TYPE")
    private String paramType ;


    @Column(name = "PARAM_ORDER")
    private Integer paramOrder ;

    @Column(name = "PARAM_DESC")
    private Integer paramDesc ;


    /** 创建时间 **/
    @Column(name = "CREATE_TIME")
    private Date createTime ;
    /** 执行次数 **/
    @Column(name = "CREATER")
    private BigDecimal creater ;
}
