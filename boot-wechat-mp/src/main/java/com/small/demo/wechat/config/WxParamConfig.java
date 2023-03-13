package com.small.demo.wechat.config;

import com.small.demo.wechat.core.YamlAndPropertySourceFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/3/12 21:41
 * @version: v1.0
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "wechatmp")
@PropertySource( value = "classpath:application-${spring.profiles.active}.yml", factory = YamlAndPropertySourceFactory.class )
@Data
public class WxParamConfig {

    private String appId = "";

    private String secret = "" ;


    private String token = "" ;

    private String encodingAESKey = "";

    @PostConstruct
    public void initWxParam(){
        log.info("appId : " + appId.substring(0,4)+"****"+appId.substring(appId.length()-4));
        log.info("secret : " + secret);
        log.info("token : " + token.substring(0,3)+"***");
        log.info("encodingAESKey : " + encodingAESKey);
        log.info("初始化参数成功");
    }
}
