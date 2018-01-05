package com.anhtuan.springmvc.service;

import com.anhtuan.springmvc.dao.EmployeeDao;
import com.anhtuan.springmvc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    @Qualifier("employeeDao")
    private EmployeeDao employeeDao;

    @Override
    public Employee findById(int id) {
        return employeeDao.findById(id);
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeDao.saveEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        Employee entity = employeeDao.findById(employee.getId());
        if (entity != null) {
            entity.setName(employee.getName());
            entity.setJoiningDate(employee.getJoiningDate());
            entity.setSalary(employee.getSalary());
            entity.setSsn(employee.getSsn());
        }
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeeDao.deleteEmployeeById(id);
    }

    @Override
    public void deleteEmployeeBySsn(String ssn) {
        employeeDao.deleteEmployeeBySsn(ssn);
    }

    @Override
    public List<Employee> fillAllEmployees() {
        return employeeDao.fillAllEmployees();
    }

    @Override
    public Employee findEmployeeBySsn(String ssn) {
        return employeeDao.findEmployeeBySsn(ssn);
    }

    @Override
    public boolean isEmployeeSsnUnique(Integer id, String ssn) {
        Employee employee = employeeDao.findEmployeeBySsn(ssn);
        return (employee == null || ((id != null) && (employee.getId() == id)));
    }
}
