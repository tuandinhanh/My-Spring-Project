package com.anhtuan.springmvc.service;

import com.anhtuan.springmvc.dao.StudentDao;
import com.anhtuan.springmvc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    @Qualifier("studentDao")
    private StudentDao studentDao;

    @Override
    public Student findById(int id) {
        return studentDao.findById(id);
    }

    @Override
    public void saveStudent(Student student) {
        studentDao.saveStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        Student entity = studentDao.findById(student.getId());
        if (entity != null) {
            entity.setName(student.getName());
            entity.setClazz(student.getClazz());
        }
    }

    @Override
    public void deleteStudentById(int id) {
        studentDao.deleteStudentById(id);
    }

    @Override
    public List<Student> findAllStudent() {
        return studentDao.findAllStudent();
    }
}
