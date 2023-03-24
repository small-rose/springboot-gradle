package com.small.demo.wechat.web.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.small.demo.wechat.config.WxParamConfig;
import com.small.demo.wechat.config.redis.RedisCache;
import com.small.demo.wechat.web.constant.WXConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ WxTokenService ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/3/13 12:12
 * @Version ： 1.0
 **/
@Slf4j
@Service
public class WxTokenService {

    @Autowired
    private WxParamConfig wxConfig;

    @Autowired
    private RedisCache redisCache;
    /**
     * 刷新微信公众号的access_token
     * https请求:
     *       https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
     * 微信返回数据:
     *       {"access_token":"ACCESS_TOKEN","expires_in":7200}
     * @return
     */
    public String refreshToken() {
        String redisKey = String.format(WXConstant.ACCESS_TOKEN_KEY,wxConfig.getAppId());
        String url = String.format(WXConstant.WX_ACCESS_TOKEN_URL,wxConfig.getAppId(),wxConfig.getSecret());
        //HttpClient工具根据项目自行修改
        String result = HttpUtil.post(url,"");
        log.info("获取微信公众号的access_token: {}", result);
        String accessToken = JSON.parseObject(result).getString("access_token");
        //redis工具根据项目自行修改
        redisCache.setCacheObject(redisKey, accessToken, 7200, TimeUnit.SECONDS);
        return accessToken;
    }
}
