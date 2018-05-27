package com.anhtuan.springmvc.controller;

import com.anhtuan.springmvc.model.Student;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/restful")
public class HomeController {

    @RequestMapping(value = "/home")
    public String home(ModelMap modelMap) {
        modelMap.addAttribute("test", "ssssss");
        return "thymeleaf/home.html";
    }

    @RequestMapping(value = "/")
    public String homeJSP() {
        return "jsp/home.jsp";
    }

    // @ResponseBody means the returned String is the response, not a view name
    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getHello(@RequestParam String name) {
        return String.format("Hello, %s", name);
    }

    /* Restful Web */
    @RequestMapping(value = "/add", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public HttpEntity<Student> addStudent(@RequestParam("name") String name, @RequestParam("class") String clazz) {
        Student student = new Student();
        student.setName(name);
        student.setClazz(clazz);
        return new ResponseEntity<Student>(student,HttpStatus.OK);
    }
}
