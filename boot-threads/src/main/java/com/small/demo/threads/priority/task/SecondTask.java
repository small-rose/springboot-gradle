package com.small.demo.threads.priority.task;

import java.util.concurrent.TimeUnit;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ FirstTask ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 9:41
 * @Version ： 1.0
 **/
public class SecondTask implements Runnable {
    private String name;
    int index;
    int priority;

    public SecondTask(int index, int priority, String name) {

        this.index = index;
        this.priority = priority;
        this.name = name ;
    }

    @Override
    public void run() {
        System.out.println("开始执行我的第2个任务 - "+ name);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行我的第2个任务完成 - " +name);
     }
}
