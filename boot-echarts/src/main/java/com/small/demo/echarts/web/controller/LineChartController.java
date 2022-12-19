package com.small.demo.echarts.web.controller;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.icepear.echarts.Line;
import org.icepear.echarts.charts.line.LineAreaStyle;
import org.icepear.echarts.charts.line.LineSeries;
import org.icepear.echarts.components.coord.cartesian.CategoryAxis;
import org.icepear.echarts.components.title.Title;
import org.icepear.echarts.render.Engine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/12/19 14:48
 * @version: v1.0
 */
@RestController
@Slf4j
public class LineChartController {

    /**
     * 直接返回 html页面方式
     * @return
     */
    @GetMapping("/linechartHtml")
    public ResponseEntity<String> getChart() {
        Line line = new Line()
                .addXAxis(new CategoryAxis()
                        .setData(new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"})
                        .setBoundaryGap(false))
                .addYAxis()
                .addSeries(new LineSeries()
                        .setData(new Number[]{820, 932, 901, 934, 1290, 1330, 1320})
                        //.setAreaStyle(new LineAreaStyle())
                ).setTitle(new Title().setText("LineCharHtml").setSubtext("subtitle "));
        Engine engine = new Engine();
        // return the full html of the echarts, used in iframes in your own template
        String json = engine.renderHtml(line);
        return ResponseEntity.ok(json);
    }


    /**
     * 直接返回 html页面方式
     * @return
     */
    @GetMapping("/lineAreaChartHtml")
    public ResponseEntity<String> lineAreaChartHtml() {
        Line line = new Line()
                .addXAxis(new CategoryAxis()
                        .setData(new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"})
                        .setBoundaryGap(false))
                .addYAxis()
                .addSeries(new LineSeries()
                        .setData(new Number[]{820, 932, 901, 934, 1290, 1330, 1320})
                        .setAreaStyle(new LineAreaStyle()))
                .setTitle(new Title().setText("LineAreaCharHtml").setSubtext("subtitle").setPadding(10).setRight(5));
        Engine engine = new Engine();
        // return the full html of the echarts, used in iframes in your own template
        String json = engine.renderHtml(line);
        return ResponseEntity.ok(json);
    }


    /**
     * 返回 Json 格式的 options
     *  前端需要引入echarts 相关js
     * @return
     */
    @GetMapping("/linechartJson")
    public Object getChartJson() {
        Line line = new Line()
                .addXAxis(new CategoryAxis()
                        .setData(new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"})
                        .setBoundaryGap(false))
                .addYAxis()
                .addSeries(new LineSeries()
                        .setData(new Number[]{820, 932, 901, 934, 1290, 1330, 1320}).setSmooth(true)
                        //.setAreaStyle(new LineAreaStyle()));
                ).setTitle(new Title().setText("linechartJson").setSubtext("subtitle").setRight(0).setTop(5));;
        Engine engine = new Engine();
        // return the full html of the echarts, used in iframes in your own template
        String json = engine.renderJsonOption(line);
        System.out.println(json);
        return JSONObject.parse(json);
    }


    /**
     * 返回 Json 格式的 options
     *  前端需要引入echarts 相关js
     * @return
     */
    @GetMapping("/lineAreaChart")
    public Object getChartJson2() {
        Line line = new Line()
                .addXAxis(new CategoryAxis()
                        .setData(new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"})
                        .setBoundaryGap(true))
                .addYAxis()
                .addSeries(new LineSeries()
                                .setData(new Number[]{820, 932, 901, 934, 1290, 1330, 1320})
                        .setAreaStyle(new LineAreaStyle()))
                .setTitle(new Title().setText("linechartJson").setSubtext("subtitle").setPadding(5).setBottom(0));

        Engine engine = new Engine();
        // return the full html of the echarts, used in iframes in your own template
        String json = engine.renderJsonOption(line);
        System.out.println(json);
        return JSONObject.parse(json);
    }
}
