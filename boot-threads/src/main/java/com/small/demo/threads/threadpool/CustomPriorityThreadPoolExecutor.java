package com.small.demo.threads.threadpool;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ CustomPriorityThreadPoolExecutor ] 说明：无
 * @Function :  功能说明：无
 *            来源  https://blog.csdn.net/qq_31142553/article/details/113750449
 *            经过测试，是有问题的，
 * @Date ：2023/2/7 13:51
 * @Version ： 1.0
 **/
public class CustomPriorityThreadPoolExecutor {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private final PriorityBlockingQueue<Runnable> priorityBlockingQueue;

    private final ThreadPoolExecutor threadPoolExecutor;

    // 自定义默认拒绝执行处理器：让调用线程执行最先提交到队列的任务，然后提交当前任务
    private static final RejectedExecutionHandler defaultHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (e.isShutdown()) {
                return;
            }
            Optional.ofNullable(e.getQueue().poll()).ifPresent(Runnable::run);
            e.execute(r);
        }
    };

    /**
     * 默认构造器
     */
    public CustomPriorityThreadPoolExecutor() {
        this(2, Runtime.getRuntime().availableProcessors() * 2 + 1, 60, TimeUnit.SECONDS, 1000, defaultHandler);
    }

    /**
     * 构造器
     *
     * @param corePoolSize    核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime   保留时间
     * @param unit            保留时间单位
     * @param workQueueSize   任务队列容量
     * @param handler         拒绝执行处理器
     */
    public CustomPriorityThreadPoolExecutor(int corePoolSize,
                                      int maximumPoolSize,
                                      long keepAliveTime,
                                      TimeUnit unit,
                                      int workQueueSize,
                                      RejectedExecutionHandler handler) {
        LOGGER.log(Level.INFO, "创建优先级线程池执行器：corePoolSize={0}，maximumPoolSize={1}，keepAliveTime={2}，unit={3}，workQueueSize={4}，handler={5}",
                new Object[]{corePoolSize, maximumPoolSize, keepAliveTime, unit.name(), workQueueSize, handler.getClass().getSimpleName()});

        /*
         * 优先级阻塞队列
         * 第一优先级priority，第二优先级taskNumber
         * 重写offer方法，改为有界
         */
        priorityBlockingQueue = new PriorityBlockingQueue(10, (Comparator<PriorityTask>) (t1, t2) ->
                t1.priority != t2.priority ? Integer.compare(t1.priority, t2.priority) : Integer.compare(t1.taskNumber, t2.taskNumber)
        ) {
            @Override
            public boolean offer(Object o) {
                return super.size() < workQueueSize ? super.offer(o) : false;
            }
        };

        // 线程池执行器，使用默认线程工厂
        threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                priorityBlockingQueue,
                handler);
    }

    /**
     * 异步执行，默认最低优先级
     *
     * @param command 任务
     */
    public void asyncExecute(Runnable command) {
        asyncExecute(command, TaskPriority.LOW);
    }

    /**
     * 异步执行
     *
     * @param command  任务
     * @param priority 优先级
     */
    public void asyncExecute(Runnable command, TaskPriority priority) {
        threadPoolExecutor.execute(new PriorityTask(command, priority.value));
        LOGGER.log(Level.INFO, "活跃线程数量：{0}，队列任务数量：{1}", new Object[]{threadPoolExecutor.getActiveCount(), priorityBlockingQueue.size()});
    }

    /**
     * 优先级任务
     */
    private static class PriorityTask implements Runnable {
        private static final AtomicInteger NUMBER_GENERATOR = new AtomicInteger(1);
        private final Runnable command;
        private final int priority;
        private final int taskNumber;

        public PriorityTask(Runnable command, Integer priority) {
            this.command = command;
            this.priority = priority;
            this.taskNumber = NUMBER_GENERATOR.getAndIncrement();
        }

        @Override
        public void run() {
            command.run();
        }
    }

    /**
     * 任务优先级枚举：HIGH > MEDIUM > LOW
     */
    public enum TaskPriority {
        HIGH(1),
        MEDIUM(2),
        LOW(3);

        private Integer value;

        TaskPriority(Integer value) {
            this.value = value;
        }
    }
}
