package com.anhtuan.springmvc.CRMSpringMVC.controller;

import com.anhtuan.springmvc.CRMSpringMVC.dao.UserJpaRepository;
import com.anhtuan.springmvc.CRMSpringMVC.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TemplateController {

    @Autowired
    UserJpaRepository userJpaRepository;

    @RequestMapping(value = "/")
    public String home(ModelMap modelMap) {

        List<User> list = userJpaRepository.findAll();
        modelMap.addAttribute("list", list);
        return "home";
    }
}
