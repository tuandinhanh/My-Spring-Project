package com.anhtuan.springmvc.CRMSpringMVC.controller;

import com.anhtuan.springmvc.CRMSpringMVC.dao.UserJpaRepository;
import com.anhtuan.springmvc.CRMSpringMVC.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TemplateController {

    private final UserJpaRepository userJpaRepository;

    @Autowired
    public TemplateController(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @GetMapping(value = "/")
    public String home(ModelMap modelMap) {

        List<User> list = userJpaRepository.findAll();
        modelMap.addAttribute("list", list);
        return "home";
    }

    @GetMapping(value = "/security/asd")
    public String security() {
        return "security";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
