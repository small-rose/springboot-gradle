package com.small.sync.download.task;

/**
 *
 */
public interface Channel {
    /**
     * 抽象任务id
     * @return
     */
    public String getId();
    /**
     * 抽象任务名称
     * @return
     */
    public String getName();


    /**
     * 任务启动
     * @return
     */
    public void start();
    /**
     * 抽象任务关闭
     * @return
     */
    public void close();


}
