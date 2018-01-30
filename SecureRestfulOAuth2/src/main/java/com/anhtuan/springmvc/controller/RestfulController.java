package com.anhtuan.springmvc.controller;

import com.anhtuan.springmvc.model.Student;
import com.anhtuan.springmvc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;

@RestController
public class RestfulController {

    @Autowired
    @Qualifier("studentService")
    private StudentService studentService;

    /*@RequestMapping(value = "/addStudent", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
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
    }*/
    @GetMapping(value = "/getALlStudent", headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getAllStudents1() {
        List<Student> list = studentService.findAllStudent();
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/student", headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> list = studentService.findAllStudent();
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/student/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
        Student student = studentService.findById(id);
        if (student == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping(value = "/student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) {
        studentService.saveStudent(student);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/student/{id}").buildAndExpand(student.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
        Student currentStudent = studentService.findById(id);
        if (currentStudent == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        currentStudent.setName(student.getName());
        currentStudent.setClazz(student.getClazz());
        studentService.updateStudent(currentStudent);
        return new ResponseEntity<>(currentStudent, HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") int id) {
        Student student = studentService.findById(id);
        if (student == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        studentService.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
