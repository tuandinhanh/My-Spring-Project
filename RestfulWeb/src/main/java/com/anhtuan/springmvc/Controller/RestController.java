package com.anhtuan.springmvc.Controller;

import com.anhtuan.springmvc.service.StudentService;
import com.anhtuan.springmvc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RestController {

    @Autowired
    private StudentService studentService;

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
    public ResponseEntity addStudent(@RequestParam("name") String name, @RequestParam("clazz") String clazz) {
        Student student = new Student();
        student.setName(name);
        student.setClazz(clazz);
        studentService.saveStudent(student);
        return new ResponseEntity(student, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllStudent", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public List<Student> getAllStudent() {
        List<Student> list = studentService.findAllStudent();
        return list;
    }
}
