package com.small.demo.threads.works.utils;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ DateUtils ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/3/6 17:48
 * @Version ： 1.0
 **/
public class DateUtils {


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
        diff = endTime - startTime;
        day = diff / nd; //计算相差天数
        hour = diff % nd / nh ;
        min = (diff % nd % nh ) / nm ;
        sed = (diff % nd % nh % nm) / ns ;
        StringBuffer sb = new StringBuffer();
        if (day>0){
            sb.append(day).append("天");
        }
        if (hour>0){
            sb.append(hour).append("小时");
        }
        if (min>0){
            sb.append(min).append("分");
        }
        if (sed>0){
            sb.append(sed).append("秒");
        }
        return sb.toString();
    }
}
