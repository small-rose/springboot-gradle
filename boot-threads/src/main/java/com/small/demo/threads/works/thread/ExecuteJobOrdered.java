package com.small.demo.threads.works.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ ExecuteJob ] 说明：无
 * @Function :  功能说明： 有序执行的Job
 * @Date ：2023/2/8 15:02
 * @Version ： 1.0
 **/
@Slf4j
public class ExecuteJobOrdered<T> implements Callable<T> {
    //执行存过ID
    private Long procedureId ;
    private Long orderNo ;

    public ExecuteJobOrdered(Long procedureId, Long orderNo) {
        this.procedureId = procedureId;
        this.orderNo = orderNo;
    }


    @Override
    public T call() throws Exception {
        log.info("开始执行 "+procedureId +" ,序号为 : " +orderNo);
        try {

        }catch (Exception e){

        }finally {
            log.info("执行 "+procedureId +" ,序号为 : " +orderNo +" 结束了！");
        }
        return null;
    }
}
