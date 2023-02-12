package com.small.demo.boot.chatgpt3.web.controller;

import com.small.demo.boot.chatgpt3.web.service.ChatGptService;
import com.small.demo.boot.chatgpt3.web.vo.ChatRequestVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/2/12 13:14
 * @version: v1.0
 */

@Slf4j
@RestController
@Api(tags = "chat测试")
public class ChatGptController {

    @Autowired
    private ChatGptService chatGptService ;


    @PostMapping("/chat")
    public String chat(ChatRequestVO chatRequestVO){

        return chatGptService.getResponse(chatRequestVO);
    }
}
