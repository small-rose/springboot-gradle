package com.small.demo.threads.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ SemaphoreConcurrent ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/22 13:49
 * @Version ： 1.0
 **/
@Slf4j
public class SemaphoreDemo {
    // 排队总人数（请求总数）
    public static int clientTotal = 15;

    // 可同时受理业务的窗口数量（同时并发执行的线程数）
    public static int threadTotal = 3;

    public static void main(String[] args) throws Exception{

        ExecutorService pool = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        // 指定 信号量
        final Semaphore semaphore = new Semaphore(threadTotal, true);
        for (int i = 0; i < clientTotal; i++) {
            int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try{
                        //(阻塞)获取许可
                        semaphore.acquire();

                        //处理业务逻辑
                        resolve(finalI);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        semaphore.release();
                        countDownLatch.countDown();
                    }
                }
            };
            pool.execute(runnable);
        }
        countDownLatch.await();
        pool.shutdown();
    }


    private static void resolve(int i) throws InterruptedException {
        log.info("services no {} , is handling ... ", i);
        Thread.sleep(2000);
    }
}
