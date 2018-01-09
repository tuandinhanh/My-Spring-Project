package com.anhtuan.springmvc.Controller;

import com.anhtuan.springmvc.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController {

    @RequestMapping(value = "/")
    public String homeThymeleaf() {
        return "thymeleaf/home.html";
    }

    @RequestMapping(value = "/home")
    public String homeJsp() {
        return "jsp/home.jsp";
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity addStudent(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("clazz") String clazz) {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setClazz(clazz);
        return new ResponseEntity(student, HttpStatus.OK);
    }
}
