package com.small.demo.threads.works.controller;

import com.small.demo.threads.works.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Project : springboot-gradle
 * @Author : zhangzongyuan
 * @Description : [ TestController ] 说明：无
 * @Function :  功能说明：无
 * @Date ：2023/2/8 17:21
 * @Version ： 1.0
 **/

@Controller
@Api
@RequestMapping("/procedure")
public class TestController {


    @Autowired
    private TestService testService ;


    @ApiOperation("测试执行固定的默认存过 call proc")
    @ApiParam(name= "procName" ,allowableValues = "无参存过名字，有参存过已拼好参数")
    @PostMapping("/call/default")
    @ResponseBody
    public String callDefault(){
        try {

            testService.callProc("");
            return "默认存过 SUCCESS";
        }catch (Exception e){
            return "FAILED-"+e.getMessage();
        }
    }
    @ApiOperation("测试 call proc")
    @ApiParam(name= "procName" ,allowableValues = "无参存过名字，有参存过已拼好参数")
    @PostMapping("/call/{procName}")
    @ResponseBody
    public String call(@PathVariable("procName") String procName){
        try {

            testService.callProc(procName);
            return "自定义存过 SUCCESS";
        }catch (Exception e){
            return "FAILED-"+e.getMessage();
        }
    }

    @ApiOperation("测试 DQL")
    @ApiParam(name= "dql" ,allowableValues = "完整的DQL存过，或者匿名存过")
    @PostMapping("/execute/{dql}")
    @ResponseBody
    public String execute(@PathVariable("dql") String dql){
        try {

            testService.executeProc(dql);
            return "动态存过 SUCCESS";
        }catch (Exception e){
            return "FAILED-"+e.getMessage();
        }
    }
}
