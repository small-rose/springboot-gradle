package com.small.demo.threads.works.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ ThreadManager ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/20 13:56
 * @Version ： 1.0
 **/
@Slf4j
@Configuration
//@EnableAsync(proxyTargetClass = true)//利用@EnableAsync注解开启异步任务支持
@ComponentScan({"com.small.demo.threads.works.thread.async"}) //必须加此注解扫描包
public class ThreadConfig implements AsyncConfigurer {

    /**
     * 核心线程数
     */
    private int corePoolSize = Runtime.getRuntime().availableProcessors() ;

    /**
     * 最大线程数
     */
    private int maxPoolSize = Runtime.getRuntime().availableProcessors() * 2 ;


    private int  queueCapacity = 500 ;

    /**
     * 可以并发的
     * @return
     */
    @Bean
    public Executor pgkThreadPool(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);//核心线程大小
        taskExecutor.setMaxPoolSize(maxPoolSize);//最大线程大小
        taskExecutor.setQueueCapacity(queueCapacity);//队列最大容量
        //当提交的任务个数大于QueueCapacity，就需要设置该参数，但spring提供的都不太满足业务场景，可以自定义一个，也可以注意不要超过QueueCapacity即可
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60);
        taskExecutor.setThreadNamePrefix("PGK-Thread-");
        taskExecutor.initialize();
        log.info("初始化线程池 pgkThreadPool ");
        return taskExecutor;
    }


    /**
     * 顺序执行
     * @return
     */
    @Bean //(destroyMethod = "shutdown")
    public Executor pgkOrderThreadPool(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(1);//核心线程大小
        taskExecutor.setMaxPoolSize(1);//最大线程大小
        taskExecutor.setQueueCapacity(500);//队列最大容量
        //当提交的任务个数大于QueueCapacity，就需要设置该参数，但spring提供的都不太满足业务场景，可以自定义一个，也可以注意不要超过QueueCapacity即可
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60);
        taskExecutor.setThreadNamePrefix("PGK-Order-Thread-");
        taskExecutor.initialize();
        log.info("初始化线程池 pgkOrderThreadPool ");
        return taskExecutor;
    }
}
