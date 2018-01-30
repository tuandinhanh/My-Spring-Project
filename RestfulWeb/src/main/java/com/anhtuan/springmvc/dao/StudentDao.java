package com.anhtuan.springmvc.dao;

import com.anhtuan.springmvc.model.Student;

import java.util.List;

public interface StudentDao {

    Student findById(int id);

    void saveStudent(Student student);

    void deleteStudentById(int id);

    List<Student> findAllStudent();
}
