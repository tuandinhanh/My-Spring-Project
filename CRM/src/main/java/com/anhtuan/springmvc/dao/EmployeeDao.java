package com.anhtuan.springmvc.dao;

import com.anhtuan.springmvc.model.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee findById(int id);

    void saveEmployee(Employee employee);

    void deleteEmployeeById(int id);

    void deleteEmployeeBySsn(String ssn);

    List<Employee> fillAllEmployees();

    Employee findEmployeeBySsn(String ssn);
}
