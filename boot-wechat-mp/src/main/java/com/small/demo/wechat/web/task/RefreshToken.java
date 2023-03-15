package com.small.demo.wechat.web.task;


import com.small.demo.wechat.web.service.WxTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ RefreshToken ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/3/13 12:10
 * @Version ： 1.0
 **/

@Slf4j
public class RefreshToken  implements InitializingBean {

        @Autowired
        private WxTokenService wxTokenService;


        /**
         * 刷新token的定时线程
         */
        //private ScheduledThreadPoolExecutor scheduledPool = new ScheduledThreadPoolExecutor(1,
        //        new BasicThreadFactory().builder().namingPattern("refresh-wx-access-token-%d").daemon(true).build());

        private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1, new DaemonThreadFactory("refresh-wx-access-token-"));

        // 创建一个守护线程工厂，用来创建守护线程
        private static class DaemonThreadFactory implements ThreadFactory {
                private final String prefix;
                private final AtomicInteger threadNumber = new AtomicInteger(1);

                public DaemonThreadFactory(String name){
                    this.prefix = name ;
                }
                @Override
                public Thread newThread(Runnable r) {
                        Thread t = Executors.defaultThreadFactory().newThread(r);
                        t.setName(prefix + threadNumber.getAndIncrement());
                        t.setDaemon(true);  // 将线程设置为守护线程
                        return t;
                }
        }

        @Override
        public void afterPropertiesSet() {
             log.info("启动刷新 Token 线程");
             // scheduledPool.scheduleAtFixedRate(() -> wxTokenService.refreshToken(),0, 7000, TimeUnit.SECONDS);
             scheduler.scheduleAtFixedRate(() -> wxTokenService.refreshToken(),0, 7000, TimeUnit.SECONDS);
        }

}
