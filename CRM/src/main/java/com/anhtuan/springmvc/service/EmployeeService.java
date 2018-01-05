package com.anhtuan.springmvc.service;

import com.anhtuan.springmvc.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findById(int id);

    void saveEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployeeById(int id);

    void deleteEmployeeBySsn(String ssn);

    List<Employee> fillAllEmployees();

    Employee findEmployeeBySsn(String ssn);

    boolean isEmployeeSsnUnique(Integer id, String ssn);
}
