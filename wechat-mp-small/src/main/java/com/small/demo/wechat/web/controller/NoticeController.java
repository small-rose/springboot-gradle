package com.small.demo.wechat.web.controller;

import com.small.demo.wechat.config.WxParamConfig;
import com.small.demo.wechat.web.enumc.NotifyEnum;
import com.small.demo.wechat.web.strategy.IWeChatNotify;
import com.small.demo.wechat.web.strategy.NotifyFactory;
import com.small.demo.wechat.web.utils.TokenUtil;
import com.small.demo.wechat.web.utils.XMLUtils;
import com.small.weixin.mp.aes.WXBizMsgCrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @Autowired
    private NotifyFactory notifyFactory;

    @Autowired
    private WxParamConfig wxParamConfig;
    /**
     * 公众号消息和事件推送
     *
     * @param timestamp    时间戳
     * @param nonce        随机数
     * @param msgSignature 消息体签名
     * @param echostr 初次接入配置所需
     * @param postData 消息体
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/event")
    public Object official(@RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam(value = "signature",required = false) String msgSignature,
                           @RequestParam(value = "echostr", required = false) String echostr,
                           @RequestBody(required = false) String postData) throws Exception {
        log.info("Msg接收到的POST请求: signature={}, timestamp={}, nonce={}, echostr={} postData={}", msgSignature, timestamp, nonce, echostr,postData);
        if(!TokenUtil.checkSignature(msgSignature, timestamp, nonce)){
            log.info("微信公众号连接Token验证失败！");
            //此处用于公众平台配置的初步接入
            return echostr;
        }
        WXBizMsgCrypt pc = wxParamConfig.getWxBizMsgCrypt();
        log.info("收到原始报文：{}", postData);
        //签名校验 数据解密
        String decryptXml = pc.decryptMsg(msgSignature, timestamp, nonce, postData);
        log.info("收到解密报文：{}", decryptXml);
        Map<String, String> decryptMap = XMLUtils.xmlToMap(decryptXml);
        log.info("报文解析：{}", decryptXml);
        //获取推送事件类型  可以拿到的事件: 1 关注/取消关注事件  2:扫描带参数二维码事件 3: 用户已经关注公众号 扫描带参数二维码事件 ...等等
        NotifyEnum notifyEnum = NotifyEnum.resolveEvent(decryptMap.get("MsgType"), decryptMap.get("Event"));
        IWeChatNotify infoType = notifyFactory.loadWeChatNotify(notifyEnum);
        //执行具体的策略 得到给微信的响应信息 微信有重试机制  需要考虑幂等性
        String result = infoType.weChatNotify(decryptMap);
        log.info("Msg响应的POST结果: 授权策略对象: [{}] 解密后信息: [{}] 返回给微信的信息: [{}]", infoType.getClass().getSimpleName(), decryptMap, result);
        return result;
    }
}