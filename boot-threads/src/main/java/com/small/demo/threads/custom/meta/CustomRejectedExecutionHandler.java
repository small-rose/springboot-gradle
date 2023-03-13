package com.small.demo.threads.custom.meta;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ CustomRejectedExecutionHandler ] 说明：无
 * @Function :  功能说明：
 *                  自定义的线程池策略-超过线程数+队列数也添加至队列，不丢弃
 * @Date ：2023/2/22 14:14
 * @Version ： 1.0
 **/
public class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

    public CustomRejectedExecutionHandler(){}

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if (!executor.isShutdown()) {
            try {
                System.out.println("========="+Thread.currentThread().getName()+":CustomRejected Active !");
                // 使用put 的方式阻塞队列
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
