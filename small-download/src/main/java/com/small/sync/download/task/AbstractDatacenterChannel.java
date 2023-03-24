package com.small.sync.download.task;

import com.small.sync.download.common.DataTaskContext;
import com.small.sync.download.queue.BoundedDataTaskQueue;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/3/18 22:22
 * @version: v1.0
 */
public abstract class AbstractDatacenterChannel extends BaseCachedChannel {

    private DataTaskContext dataTaskContext;// 上下文信息

    // 轮询线程休息时间，以毫秒为单位。
    private long sleepTime;

    /**
     * 启动方法，init进行初始化、startThreads启动任务。
     */
    @Override
    public void start() {
        init();
        startTasks();
    }

    /**
     * 初始化，可以被子类覆盖
     */
    protected void init() {
        queue = new BoundedDataTaskQueue<>(getQueueLength());// 启动阻塞线程
    }

    /**
     * 启动任务，可以被子类覆盖
     */
    protected void startTasks() {
        read();
        write();
    }

    /**
     * 读数据任务
     */
    abstract protected void read();

    /**
     * 写数据线程
     */
    abstract protected void write();



}
