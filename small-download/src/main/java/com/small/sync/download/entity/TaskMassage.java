package com.small.sync.download.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/3/18 22:58
 * @version: v1.0
 */
@Data
public class TaskMassage {

    private String applyno;// 申请号
    private String taskcode;// 任务类型
    private String tasktype;
    private Date applytime;
    private Date finishedtime;
    private String resulttype;
    private String result;
    private String note;
    private String status;
    private String Param;
    private String path;
    private String msgString;
    private List msgList;
    private Map fieldNames;
    private int start;
    private int id;
    private String sqlStr;
}
