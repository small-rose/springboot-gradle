package com.small.sync.download.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/3/18 22:39
 * @version: v1.0
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "smallsync")
@PropertySource( value = "classpath:application.yml" )
@Data
public class ParamProperties {

    private int threadPoolSize ;

    private String secret = "" ;

    private String token = "" ;

    private String encodingAESKey = "";
}
