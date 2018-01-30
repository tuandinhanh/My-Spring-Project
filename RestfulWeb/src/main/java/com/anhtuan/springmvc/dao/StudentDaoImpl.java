package com.anhtuan.springmvc.dao;

import com.anhtuan.springmvc.model.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("studentDao")
public class StudentDaoImpl extends AbstractDao<Integer, Student> implements StudentDao {
    @Override
    public Student findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveStudent(Student student) {
        persist(student);
    }

    @Override
    public void deleteStudentById(int id) {
        Student entity = getByKey(id);
        if (entity != null) delete(entity);
    }

    @Override
    public List<Student> findAllStudent() {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = builder.createQuery(Student.class);
        Root<Student> employeeRoot = criteriaQuery.from(Student.class);
        criteriaQuery.select(employeeRoot);
        TypedQuery<Student> query = getSession().createQuery(criteriaQuery);
        return query.getResultList();
    }
}
