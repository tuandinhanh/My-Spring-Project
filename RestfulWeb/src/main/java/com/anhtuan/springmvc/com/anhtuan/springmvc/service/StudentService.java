package com.anhtuan.springmvc.com.anhtuan.springmvc.service;

import com.anhtuan.springmvc.model.Student;

import java.util.List;

public interface StudentService {

    Student findById(int id);

    void saveStudent(Student student);

    void updateStudent(Student student);

    void deleteStudentById(int id);

    List<Student> findAllStudent();
}
