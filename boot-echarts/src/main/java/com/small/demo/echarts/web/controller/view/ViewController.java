package com.small.demo.echarts.web.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/12/19 12:03
 * @version: v1.0
 */
@Controller
public class ViewController {


    @RequestMapping(value = {"/","/index"})
    public String index(Map<String, Object> map) {
        //通过 map 向前台页面传递数据
        map.put("name", "Echarts-Java Demo");
        return "index";
    }

    /**
     * 折线图
     * @param map
     * @return
     */
    @RequestMapping(value = {"/line","/line.html"})
    public String line(Map<String, Object> map) {
        //通过 map 向前台页面传递数据
        map.put("name", "Echarts-Java Demo");
        map.put("title", "Line Charts");
        map.put("active", "Line");

        return "echarts/line";
    }

    /**
     * 柱状图
     * @param map
     * @return
     */
    @RequestMapping(value = {"/bar","/bar.html"})
    public String bar(Map<String, Object> map) {
        //通过 map 向前台页面传递数据
        map.put("name", "Echarts-Java Demo");
        map.put("active", "Bar");
        return "echarts/bar";
    }

    /**
     * 散点图
     * @param map
     * @return
     */
    @RequestMapping(value = {"/scatter","/scatter.html"})
    public String scatter(Map<String, Object> map) {
        //通过 map 向前台页面传递数据
        map.put("name", "Echarts-Java Demo");
        map.put("active", "Scatter");
        return "echarts/scatter";
    }

    /**
     * 雷达图图
     * @param map
     * @return
     */
    @RequestMapping(value = {"/radar","/radar.html"})
    public String radar(Map<String, Object> map) {
        //通过 map 向前台页面传递数据
        map.put("name", "Echarts-Java Demo");
        map.put("active", "Radar");
        return "echarts/radar";
    }
}
