package com.small.demo.boot.chatgpt3.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/2/12 13:21
 * @version: v1.0
 */
@Slf4j
@Data
@ApiModel("chat请求模型")
public class ChatRequestVO {

    @ApiModelProperty(name = "requestMsg" ,notes = "命令")
    private String requestMsg ;

    @ApiModelProperty(name = "lengthMsg" ,notes = "结果长度")
    private Integer lengthMsg ;
}
