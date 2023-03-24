package com.small.sync.download.queue;

import com.small.sync.download.entity.TaskMassage;

public interface DataTaskQueue<M extends TaskMassage> {

    /**
     * 获得队列的id
     */
    public String getId();

    /**
     * 获得队列的名称
     */
    public String getName();

    /**
     * 获得队列的长度
     */
    public int size();

    /**
     * 获得队列最大长度
     */
    public int capacity();

    /**
     * 获取队列头第一条数据
     */
    public M take() throws InterruptedException;

    /**
     * 往队列尾插入一条数据
     */
    public void put(M m) throws InterruptedException;
}
