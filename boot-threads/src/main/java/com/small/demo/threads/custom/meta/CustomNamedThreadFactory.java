package com.small.demo.threads.custom.meta;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ NamedThreadFactory ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/20 13:53
 * @Version ： 1.0
 **/
public class CustomNamedThreadFactory implements ThreadFactory {

        private final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;
        private final String factoryName;

    public CustomNamedThreadFactory(String group, String factoryName) {
            this.group = new ThreadGroup(group);
            this.factoryName = factoryName;
            namePrefix = factoryName + "-pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            return t;
        }
}
