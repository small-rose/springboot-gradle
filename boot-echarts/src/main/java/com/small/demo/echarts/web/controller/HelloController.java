package com.small.demo.echarts.web.controller;

import com.small.demo.echarts.web.vopo.demo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2022/12/18 22:26
 * @version: v1.0
 */
@Slf4j
@Controller
public class HelloController {

    @GetMapping(value = {"/hello"})
    public String hello(Map<String, Object> map) {
        //通过 map 向前台页面传递数据
        map.put("name", "这是一个demo");
        return "hello";
    }



    @GetMapping("/getStudents")
    public ModelAndView getStudent(){
        List<Student> students = new LinkedList<>();
        Student student = new Student();
        student.setId(1);
        student.setName("全栈学习笔记");
        student.setAge(21);
        Student student1 = new Student();
        student1.setId(2);
        student1.setName("张三");
        student1.setAge(22);
        students.add(student);
        students.add(student1);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("students",students);
        modelAndView.setViewName("students");
        return modelAndView;
    }
}
