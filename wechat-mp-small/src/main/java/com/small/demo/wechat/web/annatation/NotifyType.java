package com.small.demo.wechat.web.annatation;


import com.small.demo.wechat.web.enumc.NotifyEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author
 * @create 2023-03-15 15:27
 * @desc  微信的推送事件策略类型的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface NotifyType {


    /**
     * 事件推送的类型 支持枚举多个事件
     * @return
     */
    NotifyEnum[] value();
}
