package com.small.sync.download.common;

import com.small.sync.download.config.ParamProperties;
import lombok.Getter;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/3/18 22:31
 * @version: v1.0
 */
@Component
public class DataTaskContext implements InitializingBean {

    @Autowired
    private ParamProperties paramProperties;

    private int threadPoolSize;

    @Getter
    protected Executor pool;

    public int getThreadPoolSize() {
        return threadPoolSize;
    }


    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    @PostConstruct
    public void init(){
        threadPoolSize = paramProperties.getThreadPoolSize();
    }

    /**
     * 初始化线程池
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new ScheduledThreadPoolExecutor(getThreadPoolSize(),
                        new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
    }
}
