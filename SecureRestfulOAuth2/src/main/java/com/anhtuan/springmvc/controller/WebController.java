package com.anhtuan.springmvc.controller;

import com.anhtuan.springmvc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @Autowired
    @Qualifier("studentService")
    private StudentService studentService;

    @RequestMapping(value = "/")
    public String homeThymeleaf() {
        return "thymeleaf/home.html";
    }

    @RequestMapping(value = "/home")
    public String homeJsp() {
        return "jsp/home.jsp";
    }
}
