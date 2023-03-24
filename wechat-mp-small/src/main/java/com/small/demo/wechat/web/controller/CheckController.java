package com.small.demo.wechat.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/3/12 21:38
 * @version: v1.0
 */
@Slf4j
@RestController
public class CheckController {


    @RequestMapping("/")
    @ResponseBody
    protected String doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        return "Hello World";
    }


}
