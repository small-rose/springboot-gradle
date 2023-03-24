package com.small.demo.wechat.web.strategy;

import com.small.demo.wechat.web.annatation.NotifyType;
import com.small.demo.wechat.web.enumc.NotifyEnum;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ NotifyFactory ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/3/15 12:09
 * @Version ： 1.0
 **/
@Component
public class NotifyFactory  implements ApplicationContextAware {

    /**
     * 策略列表
     */
    private Map<NotifyEnum, IWeChatNotify> notifyMap = new HashMap<>();

    /**
     * 工厂获取事件执行策略对象
     *
     * @param notifyType
     * @return
     */
    public IWeChatNotify loadWeChatNotify(NotifyEnum notifyType) {
        IWeChatNotify notify = notifyMap.get(notifyType);
        //对于没配置的策略 返回一个默认的空实现即可
        return Optional.ofNullable(notify).orElse((xmlMap) -> this.defaultNotify(xmlMap));
    }

    /**
     * 工厂提供默认空实现
     *
     * @param xmlMap
     * @return
     */
    public String defaultNotify(Map<String, String> xmlMap) {
        return "success";
    }


    /**
     * 扫描带有NotifyType注解的bean组装成map
     * 新加策略时 在类上加入注解@NotifyType(...)即可
     * 支持枚举多个策略事件
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> notifyBeanMap = applicationContext.getBeansWithAnnotation(NotifyType.class);
        Map<NotifyEnum[], IWeChatNotify> annoValueBeanMap = notifyBeanMap.values().stream()
                .filter(obj -> ArrayUtils.contains(obj.getClass().getInterfaces(), IWeChatNotify.class))
                .map(obj -> (IWeChatNotify) obj)
                .collect(Collectors.toMap(obj -> obj.getClass().getAnnotation(NotifyType.class).value(), Function.identity()));

        annoValueBeanMap.entrySet().stream().forEach(enrty -> Arrays.stream(enrty.getKey()).forEach(type -> notifyMap.put(type, enrty.getValue())));
    }
}
