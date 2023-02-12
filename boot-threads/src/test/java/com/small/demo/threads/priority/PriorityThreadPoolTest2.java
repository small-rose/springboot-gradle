package com.small.demo.threads.priority;

import com.small.demo.threads.priority.task.FirstTask;
import com.small.demo.threads.priority.task.SecondTask;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ PriorityThreadPoolExecutorTest ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 9:23
 * @Version ： 1.0
 **/
public class PriorityThreadPoolTest2 {

    @Test
    public void testPriority() throws InterruptedException, ExecutionException {
        PriorityThreadPoolExecutor pool = new PriorityThreadPoolExecutor(1, 1000, 1, TimeUnit.MINUTES);

        Future[] futures = new Future[20];
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < futures.length; i++) {
            int index = i;
            int r = i%3 ;
            futures[i] = pool.submit(new FirstTask<>(i, r, "线程名字"+index));
        }
        // 等待所有任务结束
        for (int i = 0; i < futures.length; i++) {
            futures[i].get();
        }

        futures[19].get();
        System.out.println(buffer);
        //assertEquals("0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, ", buffer.toString());

    }




    @Test
    public void testPriorityExe() throws InterruptedException, ExecutionException {
        PriorityThreadPoolExecutor pool = new PriorityThreadPoolExecutor(1, 1000, 1, TimeUnit.MINUTES);

        Future[] futures = new Future[20];
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < futures.length; i++) {
            int index = i;
            int r = i%3 ;
            futures[i] = pool.submit(new SecondTask(i, r, "线程名字"+index));
            System.out.println("add a task "+i);
        }
        // 等待所有任务结束
        for (int i = 0; i < futures.length; i++) {
            futures[i].get();
        }

        System.out.println(buffer);
        //assertEquals("0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, ", buffer.toString());

    }

    @Test
    public void testExe() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1000, 1, TimeUnit.MINUTES, new ArrayBlockingQueue(10));

        Future[] futures = new Future[20];
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < futures.length; i++) {
            int index = i;
            int r = i%3 ;
            pool.submit(new SecondTask(i, r, "线程名字"+index));
        }
        Thread.currentThread().setDaemon(true);
        System.out.println(buffer);
        //assertEquals("0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, ", buffer.toString());

    }
}
