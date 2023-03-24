package com.small.sync.download.task;

import com.small.sync.download.queue.BoundedDataTaskQueue;
import org.springframework.beans.factory.annotation.Required;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/3/18 22:52
 * @version: v1.0
 */
public abstract class BaseCachedChannel implements Channel {

    // channel id
    private String id;

    private String name;// channel name
    // 控制线程的数量
    private int queueLength;
    // 数据缓存队列
    protected BoundedDataTaskQueue  queue;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Required
    public void setId(String id) {
        this.id = id;
    }

    @Required
    public void setName(String name) {
        this.name = name;
    }

    public BoundedDataTaskQueue getQueue() {
        return queue;
    }

    public void setQueue(BoundedDataTaskQueue queue) {
        this.queue = queue;
    }

    @Required
    public void setQueueLength(int queueLength) {
        this.queueLength = queueLength;
    }

    public int getQueueLength() {
        return queueLength;
    }
}
