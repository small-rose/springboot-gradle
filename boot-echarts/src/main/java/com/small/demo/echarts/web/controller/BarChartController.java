package com.small.demo.echarts.web.controller;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.icepear.echarts.Bar;
import org.icepear.echarts.charts.bar.*;
import org.icepear.echarts.components.title.Title;
import org.icepear.echarts.components.tooltip.Tooltip;
import org.icepear.echarts.components.tooltip.TooltipAxisPointer;
import org.icepear.echarts.render.Engine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：  柱状图
 * @author: 张小菜
 * @date: 2022/12/19 14:48
 * @version: v1.0
 */
@RestController
@Slf4j
public class BarChartController {

    /**
     * 直接返回 html页面方式
     * @return
     */
    @GetMapping("/barChartHtml")
    public ResponseEntity<String> getChart() {
        Bar bar = new Bar()
                .addXAxis(new String[] { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" })
                .addYAxis()
                .addSeries(new Number[] { 120, 200, 150, 80, 70, 110, 130 }
                ).setTitle(new Title().setText("BarChartHtml").setSubtext("subtitle "));
        Engine engine = new Engine();
        // return the full html of the echarts, used in iframes in your own template
        String json = engine.renderHtml(bar);
        return ResponseEntity.ok(json);
    }


    /**
     * 直接返回 html页面方式
     * @return
     */
    @GetMapping("/barHighlightSingle")
    public ResponseEntity<String> lineAreaChartHtml() {
        Bar bar = new Bar()
                .addXAxis(new String[] { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" })
                .addYAxis()
                .addSeries(new BarSeries().setData(new BarDataItem[] {
                        new BarDataItem().setValue(120),
                        new BarDataItem().setValue(200).setItemStyle(new BarItemStyle().setColor("#a90000")),
                        new BarDataItem().setValue(150),
                        new BarDataItem().setValue(80),
                        new BarDataItem().setValue(70),
                        new BarDataItem().setValue(110),
                        new BarDataItem().setValue(130)
                })).setTitle(new Title().setText("barHighlightSingle").setSubtext("subtitle").setPadding(10).setRight(5));
        Engine engine = new Engine();
        // return the full html of the echarts, used in iframes in your own template
        String json = engine.renderHtml(bar);
        return ResponseEntity.ok(json);
    }


    /**
     * 返回 Json 格式的 options
     *  前端需要引入echarts 相关js
     * @return
     */
    @GetMapping("/multipleBarchartJson")
    public Object multipleBarchartJson() {
        Bar bar = new Bar()
                .setLegend()
                .setTooltip("item")
                .addXAxis(new String[] { "Matcha Latte", "Milk Tea", "Cheese Cocoa", "Walnut Brownie" })
                .addYAxis()
                .addSeries("2015", new Number[] { 43.3, 83.1, 86.4, 72.4 })
                .addSeries("2016", new Number[] { 85.8, 73.4, 65.2, 53.9 })
                .addSeries("2017", new Number[] { 93.7, 55.1, 82.5, 39.1 })
                .setTitle(new Title().setText("linechartJson").setSubtext("subtitle").setRight(0).setTop(5));;
        Engine engine = new Engine();
        // return the full html of the echarts, used in iframes in your own template
        String json = engine.renderJsonOption(bar);
        System.out.println(json);
        return JSONObject.parse(json);
    }


    /**
     * 返回 Json 格式的 options
     *  前端需要引入echarts 相关js
     * @return
     */
    @GetMapping("/horizontalStackedBar")
    public Object horizontalStackedBar() {
        Bar bar = new Bar()
                .setTooltip(new Tooltip().setTrigger("axis")
                        .setAxisPointer(new TooltipAxisPointer().setType("shadow")))
                .setLegend()
                .addXAxis()
                .addYAxis(new String[] { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" })
                .addSeries(createSeries("Direct", new Number[] { 320, 302, 301, 334, 390, 330, 320 }))
                .addSeries(createSeries("Mail Ad", new Number[] { 120, 132, 101, 134, 90, 230, 210 }))
                .addSeries(createSeries("Affiliate Ad", new Number[] { 220, 182, 191, 234, 290, 330, 310 }))
                .addSeries(createSeries("Video Ad", new Number[] { 150, 212, 201, 154, 190, 330, 410 }))
                .addSeries(createSeries("Search Engine", new Number[] { 820, 832, 901, 934, 1290, 1330, 1320 }))
                .setTitle(new Title().setText("linechartJson").setSubtext("subtitle").setPadding(5).setBottom(0));

        Engine engine = new Engine();
        // return the full html of the echarts, used in iframes in your own template
        String json = engine.renderJsonOption(bar);
        System.out.println(json);
        return JSONObject.parse(json);
    }

    BarSeries createSeries(String name, Object[] data) {
        return new BarSeries().setName(name).setStack("total")
                .setLabel(new BarLabel().setShow(true))
                .setEmphasis(new BarEmphasis().setFocus("series"))
                .setData(data);
    }

}
