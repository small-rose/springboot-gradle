package com.small.sync.download.queue;

import com.small.sync.download.entity.TaskMassage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/3/18 22:56
 * @version: v1.0
 */
public class BoundedDataTaskQueue<M extends TaskMassage>  implements DataTaskQueue<M>{

    private String id;
    private String name ;
    private int capacity;
    private final BlockingQueue<M> queue;

    public BoundedDataTaskQueue(int capacity) {
        this.queue = new LinkedBlockingQueue<M>(
                capacity < 0 ? Integer.MAX_VALUE : capacity);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int size() {
        return this.queue.size();
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public M take() throws InterruptedException {
        return this.queue.take();
    }

    @Override
    public void put(M  m) throws InterruptedException {
        this.queue.put(m);
    }
}
