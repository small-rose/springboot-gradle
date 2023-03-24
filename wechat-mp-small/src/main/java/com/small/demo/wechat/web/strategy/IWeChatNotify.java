package com.small.demo.wechat.web.strategy;

import java.util.Map;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ IWeChatNotify ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/3/15 12:01
 * @Version ： 1.0
 **/
public interface IWeChatNotify {
    /**
     * 上层事件推送策略抽象接口
     *
     * @param xmlMap 微信推送的参数数据
     * @return  返回给微信的回复信息 如：接收到用户发消息事件，我们给他返回“收到”
     * @throws Exception
     */
    String weChatNotify(Map<String, String> xmlMap) throws Exception;
}
