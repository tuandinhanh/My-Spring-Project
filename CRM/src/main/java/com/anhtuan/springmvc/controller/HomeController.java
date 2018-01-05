package com.anhtuan.springmvc.controller;

import com.anhtuan.springmvc.model.Employee;
import com.anhtuan.springmvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
public class HomeController {

    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home() {
        return "thymeleaf/home.html";
    }

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listEmployees(ModelMap modelMap){
        List<Employee> list = employeeService.fillAllEmployees();
        modelMap.addAttribute("employees", list);
        return "thymeleaf/allemployees.html";
    }

    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newEmployee(ModelMap modelMap) {
        Employee employee = new Employee();
        modelMap.addAttribute("employee", employee);
        modelMap.addAttribute("edit", false);
        return "thymeleaf/registration.html";
    }

    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            modelMap.addAttribute("edit",false);
            return "thymeleaf/registration.html";
        }
        if (!employeeService.isEmployeeSsnUnique(employee.getId(), employee.getSsn())) {
            FieldError fieldError = new FieldError("employee", "ssn", messageSource.getMessage("non.unique.ssn", new String[] {employee.getSsn()}, Locale.getDefault()));
            result.addError(fieldError);
            modelMap.addAttribute("edit",false);
            return "thymeleaf/registration.html";
        }
        employeeService.saveEmployee(employee);
        modelMap.addAttribute("success", "Employee " + employee.getName() + " registered successfully");
        return "thymeleaf/success.html";
    }

    @RequestMapping(value = {"/edit/employee"}, method = RequestMethod.GET)
    public String editEmployee(@RequestParam("id") Integer id, ModelMap modelMap) {
        Employee employee = employeeService.findById(id);
        modelMap.addAttribute("employee", employee);
        modelMap.addAttribute("edit", true);
        return "thymeleaf/registration.html";
    }

    @RequestMapping(value = "/edit/employee", method = RequestMethod.POST)
    public String updateEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, ModelMap modelMap, @RequestParam("id") Integer id) {
        if (result.hasErrors()) {
            modelMap.addAttribute("edit", true);
            return "thymeleaf/registration.html";
        }
        if (!employeeService.isEmployeeSsnUnique(employee.getId(), employee.getSsn())) {
            FieldError ssnFieldError = new FieldError("employee", "ssn", messageSource.getMessage("non.unique.ssn", new String[] {employee.getSsn()}, Locale.getDefault()));
            result.addError(ssnFieldError);
            modelMap.addAttribute("edit", true);
            return "thymeleaf/registration.html";
        }
        employeeService.updateEmployee(employee);
        modelMap.addAttribute("success", "Employee " + employee.getName() + " update successfully");
        return "thymeleaf/success.html";
    }

    @RequestMapping(value = {"/delete/employee"}, method = RequestMethod.GET)
    public String deleteEmployee(@RequestParam("id") Integer id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/list";
    }
}
