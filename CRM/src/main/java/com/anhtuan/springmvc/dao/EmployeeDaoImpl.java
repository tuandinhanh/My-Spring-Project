package com.anhtuan.springmvc.dao;

import com.anhtuan.springmvc.model.Employee;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Integer, Employee> implements EmployeeDao {
    @Override
    public Employee findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveEmployee(Employee employee) {
        persist(employee);
    }

    @Override
    public void deleteEmployeeById(int id) {
        Employee entity = findById(id);
        if (entity != null) delete(entity);
    }

    @Override
    public void deleteEmployeeBySsn(String ssn) {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaDelete<Employee> criteriaDelete = criteriaBuilder.createCriteriaDelete(Employee.class);
        Root<Employee> employeeRoot = criteriaDelete.from(Employee.class);
        criteriaDelete.where(criteriaBuilder.equal(employeeRoot.get("ssn"), ssn));
        Query<Employee> query = getSession().createQuery(criteriaDelete);
        query.executeUpdate();
    }

    @Override
    public List<Employee> fillAllEmployees() {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        criteriaQuery.select(employeeRoot);
        TypedQuery<Employee> query = getSession().createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public Employee findEmployeeBySsn(String ssn) {
        CriteriaBuilder createBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = createBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        criteriaQuery.select(employeeRoot);
        criteriaQuery.where(createBuilder.equal(employeeRoot.get("ssn"), ssn));
        TypedQuery<Employee> query = getSession().createQuery(criteriaQuery);
        Employee result;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            result = null;
            System.out.print("Error: " + e.toString() + "\n");
        }
        return result;
    }
}
