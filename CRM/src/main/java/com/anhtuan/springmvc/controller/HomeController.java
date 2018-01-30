package com.anhtuan.springmvc.controller;

import com.anhtuan.springmvc.model.*;
import com.anhtuan.springmvc.service.EmployeeService;
import com.anhtuan.springmvc.service.RoleService;
import com.anhtuan.springmvc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;

    @Autowired
    private MessageSource messageSource;

    /*@Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;*/


    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home() {
        return "thymeleaf/home.html";
    }

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listEmployees(ModelMap modelMap){
        List<Employee> list = employeeService.fillAllEmployees();
        modelMap.addAttribute("employees", list);
        logger.info("List Employee method");
        return "thymeleaf/allemployees.html";
    }

    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newEmployee(ModelMap modelMap) {
        Employee employee = new Employee();
        modelMap.addAttribute("employee", employee);
        modelMap.addAttribute("edit", false);
        logger.info("new Employ method");
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
        logger.info("save Employee method");
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

    /*private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }*/

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "jsp/loginPage.jsp";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/Access_Denied")
    public String accessDenied() {
        return "thymeleaf/accessDenied.html";
    }

    /*--------------------------------------------------------------*/

    @RequestMapping(value = "/test", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<User> test() {
        User user = new User();
        user.setSsoId("ass");
        user.setPassword("123456");
        user.setFirstName("kiablog");
        user.setLastName("thang khung");
        user.setEmail("kiablog@gmail.com");
        user.setState(State.ACTIVE.getState());
        HashSet<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setId(99);
        role.setType(RoleType.ADMIN.getRoleType());
        roles.add(role);
        role.setId(100);
        role.setType(RoleType.USER.getRoleType());
        roles.add(role);
        user.setRoles(roles);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/addRole", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity addRole() {
        if (roleService.findByType("ADMIN") == null) {
            Role role = new Role();
            role.setType(RoleType.ADMIN.getRoleType());
            roleService.save(role);
            logger.info("save Role type = " + RoleType.ADMIN.getRoleType());
        }
        if (roleService.findByType("USER") == null) {
            Role role = new Role();
            role.setType(RoleType.USER.getRoleType());
            roleService.save(role);
            logger.info("save Role type = " + RoleType.USER.getRoleType());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity addUser() {
        if (userService.findBySsoId("ass") == null) {
            User user = new User();
            user.setSsoId("ass");
            user.setPassword("123456");
            user.setFirstName("kiablog");
            user.setLastName("thang khung");
            user.setEmail("kiablog@gmail.com");
            user.setState(State.ACTIVE.getState());
            LinkedHashSet<Role> roles = new LinkedHashSet<>();
            roles.add(roleService.findByType("ADMIN"));
            roles.add(roleService.findByType("USER"));
            user.setRoles(roles);
            userService.save(user);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/findUser", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity findUserBySsoID(@RequestParam("ssoId") String ssoId) {
        User user = userService.findBySsoId(ssoId);
        if (user == null) {
            boolean index = "Active".equals(State.ACTIVE.getState());
            System.out.println("khong tim dc! loi roi " + index);
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/findRole", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity findRole(@RequestParam(value = "type") String type) {
        Role role = roleService.findByType(type);
        if (role == null) {
            System.out.println("khong tim dc! loi roi");
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @RequestMapping(value = "/findRoleByUser", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity findRoleByUser(@RequestParam(value = "ssoId") String ssoId) {
        User user = userService.findBySsoId(ssoId);
        if (user == null) {
            System.out.println("khong tim dc! loi roi");
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        Set<Role> roles = user.getRoles();
        ArrayList<Role> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(role);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
