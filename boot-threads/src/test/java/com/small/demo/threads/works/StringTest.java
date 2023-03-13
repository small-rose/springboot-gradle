package com.small.demo.threads.works;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ StringTest ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/8 18:01
 * @Version ： 1.0
 **/
public class StringTest {


    @Test
    public void test_18(){
        String key = "aaa;bbb;";
        int count = key.length() - key.replaceAll(";", "").length();
        System.out.println(count);
        System.out.println(key);
        
    }

    @Test
    public void test_nano() throws InterruptedException {
        long a = System.nanoTime();
        TimeUnit.SECONDS.sleep(2);
        long b = System.nanoTime();
        System.out.println((b-a)/1000000);

    }

    @Test
    public void test_sys() throws InterruptedException {
        long a = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(2);
        long b = System.currentTimeMillis();
        System.out.println(dateDiff(b,a));

    }


    public static String dateDiff(Long startTime, Long endTime) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        long diff;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sed = 0 ;
        long hmil = 0 ;
        diff = endTime - startTime;
        day = diff / nd; //计算相差天数
        hour = diff % nd / nh ;
        min = (diff % nd % nh ) / nm ;
        sed = (diff % nd % nh % nm) / ns ;
        hmil = diff % nd % nh % nm % ns ;
        return day + "天" + hour  + "小时"
                + min + "分" + sed  + "秒" +
                hmil + "毫秒";
    }
}
