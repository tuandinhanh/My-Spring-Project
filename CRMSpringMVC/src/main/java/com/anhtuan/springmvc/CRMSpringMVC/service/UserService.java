package com.anhtuan.springmvc.CRMSpringMVC.service;

import com.anhtuan.springmvc.CRMSpringMVC.model.Role;
import com.anhtuan.springmvc.CRMSpringMVC.model.User;

import java.util.List;

public interface UserService {

    //boolean isUserAvailable(String username);

    List<User> findAll();

    User getUserByLogin(String login);

    User getUserByFirstName(String firstName);

    Integer countUsersByFirstName(String firstName);

    User save(User user);

    void delete(User user);
}
