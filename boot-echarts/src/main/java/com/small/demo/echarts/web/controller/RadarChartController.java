package com.small.demo.echarts.web.controller;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.icepear.echarts.Line;
import org.icepear.echarts.Radar;
import org.icepear.echarts.charts.graph.GraphEdgeLineStyle;
import org.icepear.echarts.charts.line.LineAreaStyle;
import org.icepear.echarts.charts.line.LineSeries;
import org.icepear.echarts.charts.radar.RadarDataItem;
import org.icepear.echarts.components.coord.cartesian.CategoryAxis;
import org.icepear.echarts.components.coord.radar.RadarIndicator;
import org.icepear.echarts.components.title.Title;
import org.icepear.echarts.render.Engine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述： 散点图
 * @author: 张小菜
 * @date: 2022/12/19 14:48
 * @version: v1.0
 */
@RestController
@Slf4j
public class RadarChartController {

    /**
     * 直接返回 html页面方式
     * @return
     */
    @GetMapping("/baseRadarHtml")
    public ResponseEntity<String> baseRadarHtml() {
        Radar radar = new Radar()
                .setTitle("Basic Radar")
                .setLegend()
                .setRadarAxis(new RadarIndicator[] {
                        new RadarIndicator().setName("销售部").setMax(6500),
                        new RadarIndicator().setName("行政管理").setMax(16000),
                        new RadarIndicator().setName("IT信息部").setMax(30000),
                        new RadarIndicator().setName("客户服务").setMax(38000),
                        new RadarIndicator().setName("开发部").setMax(52000),
                        new RadarIndicator().setName("市场部").setMax(25000) })
                .addSeries(new RadarDataItem[] {
                        new RadarDataItem()
                                .setValue(new Number[] { 4200, 3000, 20000, 35000, 50000, 18000 })
                                .setName("预算分配图"),
                        new RadarDataItem()
                                .setValue(new Number[] { 5000, 14000, 28000, 26000, 42000, 21000 })
                                .setName("实际陈本") }
                );
        Engine engine = new Engine();
        // return the full html of the echarts, used in iframes in your own template
        String json = engine.renderHtml(radar);
        return ResponseEntity.ok(json);
    }


    /**
     * 直接返回 html页面方式
     * @return
     */
    @GetMapping("/baseRadarHtml2")
    public ResponseEntity<String> lineAreaChartHtml() {
        Radar radar = new Radar()
                .setTitle(new Title().setName("Basic Radar 2").setRight(5).setTop(5))
                .setLegend()
                .setRadarAxis(new RadarIndicator[] {
                        new RadarIndicator().setName("销售部").setMax(6500).setColor("red"),
                        new RadarIndicator().setName("行政管理").setMax(16000).setColor("red"),
                        new RadarIndicator().setName("IT信息部").setMax(30000).setColor("red"),
                        new RadarIndicator().setName("客户服务").setMax(38000),
                        new RadarIndicator().setName("开发部").setMax(52000),
                        new RadarIndicator().setName("市场部").setMax(25000).setColor("red") })
                .addSeries(new RadarDataItem[] {
                        new RadarDataItem()
                                .setValue(new Number[] { 4200, 3000, 20000, 35000, 50000, 18000 })
                                .setName("预算分配图"),
                        new RadarDataItem()
                                .setValue(new Number[] { 5000, 14000, 28000, 26000, 42000, 21000 })
                                .setName("实际陈本").setAreaStyle(new LineAreaStyle().setOpacity(0.5)).setLineStyle(new GraphEdgeLineStyle())
                });
        Engine engine = new Engine();
        // return the full html of the echarts, used in iframes in your own template
        String json = engine.renderHtml(radar);
        return ResponseEntity.ok(json);
    }


    /**
     * 返回 Json 格式的 options
     *  前端需要引入echarts 相关js
     * @return
     */
    @GetMapping("/baseRadarJson")
    public Object getChartJson() {
        Line line = new Line()
                .addXAxis(new CategoryAxis()
                        .setData(new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"})
                        .setBoundaryGap(false))
                .addYAxis()
                .addSeries(new LineSeries()
                        .setData(new Number[]{820, 932, 901, 934, 1290, 1330, 1320})
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
    @GetMapping("/baseRadarJson2")
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
