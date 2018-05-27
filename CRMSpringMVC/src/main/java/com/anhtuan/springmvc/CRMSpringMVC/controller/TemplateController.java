package com.anhtuan.springmvc.CRMSpringMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {

    @RequestMapping(value = "/")
    public String home(ModelMap modelMap) {

        //List<User> list = userJpaRepository.findAll();
        String message = "Hello World!";
        modelMap.addAttribute("message", message);
        return "home";
    }
}
