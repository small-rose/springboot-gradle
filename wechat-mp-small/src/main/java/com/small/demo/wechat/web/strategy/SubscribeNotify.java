package com.small.demo.wechat.web.strategy;

import com.small.demo.wechat.web.annatation.NotifyType;
import com.small.demo.wechat.web.enumc.NotifyEnum;
import com.small.demo.wechat.web.service.WxNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ SubscribeNotify ] 说明：无
 * @Function :  功能说明：关注事件的处理
 * @Date ：2023/3/15 12:12
 * @Version ： 1.0
 **/

@Component
@NotifyType(NotifyEnum.SUBSCRIBE)
public class SubscribeNotify implements IWeChatNotify{

    @Autowired
    private WxNotifyService wxNotifyService;

    @Override
    public String weChatNotify(Map<String, String> xmlMap) throws Exception {

        return "";
    }
}
