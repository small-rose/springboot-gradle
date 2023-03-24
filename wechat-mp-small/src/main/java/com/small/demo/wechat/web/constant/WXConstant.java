package com.small.demo.wechat.web.constant;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ WXConstant ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/3/13 12:15
 * @Version ： 1.0
 **/
public class WXConstant {

    /**
     * 微信公众号access_token_key 用于保存在redis中的key
     */
    public static final String ACCESS_TOKEN_KEY = "wechat:accessToken:%s";

    /**
     * 获取微信公众号的access_token
     */
    public static final String WX_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

}
