package com.anhtuan.springmvc.CRMSpringMVC.controller;

import com.anhtuan.springmvc.CRMSpringMVC.dao.RoleJpaRepository;
import com.anhtuan.springmvc.CRMSpringMVC.dao.UserJpaRepository;
import com.anhtuan.springmvc.CRMSpringMVC.model.Role;
import com.anhtuan.springmvc.CRMSpringMVC.model.User;
import com.anhtuan.springmvc.CRMSpringMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
public class HomeController {

    private final EntityManager entityManager;

    private final UserService userService;

    private final RoleJpaRepository roleJpaRepository;

    @Autowired
    public HomeController(UserService userService, RoleJpaRepository roleJpaRepository, EntityManager entityManager) {
        this.userService = userService;
        this.roleJpaRepository = roleJpaRepository;
        this.entityManager = entityManager;
    }

    @GetMapping(value = "/allUsers", produces = "application/json")
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping(value = "/findUsersByFirstName")
    public ResponseEntity findUsersByFirstName(@RequestParam(value = "firstName", required = true) String firstName) {
        User user = userService.getUserByFirstName(firstName);
        if (user == null)
            return ResponseEntity.ok("No found");
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/countUsersByFirstName")
    public Integer countUsersByFirstName(@RequestParam(value = "firstName", required = true) String firstName) {
        return userService.countUsersByFirstName(firstName);
    }

    @PostMapping(value = "/add", headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity add(@RequestBody final User user) {
        userService.save(user);
        return ResponseEntity.ok("ok");
    }

    @GetMapping(value = "/allRoles", produces = "application/json")
    public List<Role> findAllRoles() {
        return roleJpaRepository.findAll();
    }

    @GetMapping(value = "/testQuery")
    public Role testQuery(@RequestParam(value = "role", required = true) String role) {
        return roleJpaRepository.findRoleByRole(role);
    }

    @GetMapping(value = "/testHQL")
    public List<Role> testHQL() {
        String query = "select R from " + Role.class.getSimpleName() +" R";
        return entityManager.createQuery(query, Role.class).getResultList();
    }
}
