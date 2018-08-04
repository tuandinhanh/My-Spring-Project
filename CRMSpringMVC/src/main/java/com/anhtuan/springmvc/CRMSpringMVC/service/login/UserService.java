package com.anhtuan.springmvc.CRMSpringMVC.service.login;

import com.anhtuan.springmvc.CRMSpringMVC.model.login.User;

import java.util.List;

public interface UserService {

    //boolean isUserAvailable(String username);

    List<User> findAll();

    User findUserById(Integer id);

    User getUserByLogin(String login);

    User getUserByFirstName(String firstName);

    Integer countUsersByFirstName(String firstName);

    User save(User user);

    void delete(User user);
}
