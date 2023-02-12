package com.small.demo.threads.threadpool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ TestTask ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 13:54
 * @Version ： 1.0
 **/
public class TestTask  implements Runnable {

    private final int i;

    public TestTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
        System.out.println("run-task: " + i + ", thread-name: " + Thread.currentThread().getName());
    }

}
