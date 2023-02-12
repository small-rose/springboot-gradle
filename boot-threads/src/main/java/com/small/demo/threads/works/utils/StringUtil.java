package com.small.demo.threads.works.utils;

import org.springframework.util.StringUtils;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ StingUtil ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/8 18:07
 * @Version ： 1.0
 **/
public class StringUtil {

    public static int countSplit(String key, String split){
        if (!StringUtils.hasText(key)){
            return 0;
        }
        return key.length() - key.replaceAll(split, "").length();
    }
}
