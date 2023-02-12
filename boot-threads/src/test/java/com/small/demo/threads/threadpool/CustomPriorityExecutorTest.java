package com.small.demo.threads.threadpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ CustomPriorityExecutorTest ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 13:56
 * @Version ： 1.0
 **/
public class CustomPriorityExecutorTest {

    @Test
    public  void test1() throws InterruptedException {
        CustomPriorityThreadPoolExecutor executor = new CustomPriorityThreadPoolExecutor(2, 8, 60, TimeUnit.SECONDS, 10, new ThreadPoolExecutor.CallerRunsPolicy());
        executor.asyncExecute(new TestTask(31), CustomPriorityThreadPoolExecutor.TaskPriority.LOW);
        executor.asyncExecute(new TestTask(32), CustomPriorityThreadPoolExecutor.TaskPriority.LOW);
        executor.asyncExecute(new TestTask(21), CustomPriorityThreadPoolExecutor.TaskPriority.MEDIUM);
        executor.asyncExecute(new TestTask(33));
        executor.asyncExecute(new TestTask(22), CustomPriorityThreadPoolExecutor.TaskPriority.MEDIUM);
        executor.asyncExecute(new TestTask(34));
        executor.asyncExecute(new TestTask(11), CustomPriorityThreadPoolExecutor.TaskPriority.HIGH);
        executor.asyncExecute(new TestTask(12), CustomPriorityThreadPoolExecutor.TaskPriority.HIGH);
        executor.asyncExecute(new TestTask(13), CustomPriorityThreadPoolExecutor.TaskPriority.HIGH);

        TimeUnit.SECONDS.sleep(10);
    }
}
