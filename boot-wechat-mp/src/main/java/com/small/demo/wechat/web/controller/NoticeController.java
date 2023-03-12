package com.small.demo.wechat.web.controller;

import com.small.demo.wechat.web.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：  服务器配置验证
 * @author: 张小菜
 * @date: 2023/3/12 22:56
 * @version: v1.0
 */

@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {


    /**
     * 公众号消息和事件推送
     *
     * @param timestamp    时间戳
     * @param nonce        随机数
     * @param signature   消息体签名
     * @param echostr      初次接入配置所需
     * @param postData     消息体
     * @return
     */
    @ResponseBody
    @RequestMapping( value = "/event" )
    public Object official(@RequestParam( "timestamp" ) String timestamp,
                           @RequestParam( "nonce" ) String nonce,
                           @RequestParam( value = "signature", required = false ) String signature,
                           @RequestParam( value = "echostr", required = false ) String echostr,
                           @RequestBody( required = false ) String postData) throws Exception {
        if(TokenUtil.checkSignature(signature, timestamp, nonce)){
            log.info("微信公众号连接成功！");
        }else{
            log.info("微信公众号连接失败！");
        }
        return echostr;
    }

}