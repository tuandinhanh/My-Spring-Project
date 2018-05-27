package com.anhtuan.springmvc.CRMSpringMVC.controller;

import com.anhtuan.springmvc.CRMSpringMVC.dao.UserJpaRepository;
import com.anhtuan.springmvc.CRMSpringMVC.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @GetMapping(value = "/all", produces = "application/json")
    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    @PostMapping(value = "/add", headers = "Accept=application/json", produces = "application/json")
    public HttpEntity add(@RequestBody final User user) {
        userJpaRepository.save(user);
        return new HttpEntity(HttpStatus.OK);
    }
}
