package com.small.demo.threads.custom.demo;

import com.small.demo.threads.custom.meta.CustomRejectedExecutionHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ ThreadPoolRejectedTest ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/22 14:20
 * @Version ： 1.0
 **/
public class ThreadPoolRejectedTest {


    public static void main(String[] args) {
        try {
            // 自定义的线程池任务拒绝策略
            RejectedExecutionHandler rejected = (Runnable r, ThreadPoolExecutor executor) -> {
                if (r instanceof RunnableImpl) {
                    RunnableImpl rm = (RunnableImpl) r;
                    Print.outln("线程任务被拒绝 Task was rejected : " + rm.id);
                    try {
                        // 等待1.5秒后，尝试将当前被拒绝的任务重新加入线程队列
                        // 此时主线程是会被阻塞的
                        Thread.sleep(1500);
                        Print.outln("尝试重新加入 try ti add queue : " + rm.id);
                        executor.execute(r);
                    } catch (Exception e) {
                    }
                }
            };
            //制定法你故意
            CustomRejectedExecutionHandler rejectedExecutionHandler = new CustomRejectedExecutionHandler();

            // 将线程池队列设置为有界队列
            LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(1);
            // 初始化线程池
            //ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, queue, rejected);
            ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, queue, rejectedExecutionHandler);
            // 模拟一个迭代器
            ListIterator<String> iterator = list(105).listIterator();
            // 假设每个线程每次处理20条数据
            List<String> list = new ArrayList<String>(20);
            int i = 1;
            while (iterator.hasNext()) {
                list.add(iterator.next());
                if (list.size() >= 20 || !iterator.hasNext()) {
                    executor.execute(new RunnableImpl(i++, list));
                    list.clear();
                }
            }

            Print.outln("--------------------任务添加完成 task add complete  ");
            executor.shutdown();
            while (!executor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
            }
            Print.outln("--------------------线程池执行结束 executor exe over ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> list(int size) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 1; i <= size; i++) {
            list.add(i + "");
        }
        return list;
    }
}

class RunnableImpl implements Runnable {

    public Integer id = -1;

    public RunnableImpl(int id, List<String> list) {
        this.id = id;
        Print.outln("init success : " + id);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000); // 假设线程每次处理需要3秒钟
            Print.outln("-----------------------------------Thread exe over : " + id);
        } catch (Exception e) {
        }
    }
}

class Print {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");

    private static String time() {
        return "[" + sdf.format(new Date()) + "] ";
    }

    public static void outln(String s) {
        System.out.println(time() + s);
    }
}