package com.small.demo.threads.priority.task;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ FirstTask ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/7 9:41
 * @Version ： 1.0
 **/
public class FirstTask<T> implements Callable<T> {
    private String name;
    int index;
    int priority;

    public FirstTask(int index, int priority, String name) {

        this.index = index;
        this.priority = priority;
        this.name = name ;
    }

    @Override
    public T call() throws Exception {
        System.out.println("执行我的第一个任务开始 --"+name);

        TimeUnit.SECONDS.sleep(1);
        System.out.println("执行我的第一个任务完成 -- "+name);
        return null;
    }

}
