package com.small.demo.threads.works;

import org.junit.jupiter.api.Test;

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
}
