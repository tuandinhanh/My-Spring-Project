package com.anhtuan.springmvc.CRMSpringMVC.service;

import com.anhtuan.springmvc.CRMSpringMVC.dao.UserJpaRepository;
import com.anhtuan.springmvc.CRMSpringMVC.model.Role;
import com.anhtuan.springmvc.CRMSpringMVC.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    /*@Override
    public boolean isUserAvailable(String username) {
        Integer count = userJpaRepository.countUsersByLogin(username);
        return count < 1;
    }*/

    @Override
    public User getUserByLogin(String login) {
        Assert.notNull(String.class, login);
        return userJpaRepository.findUserByLogin(login);
    }

    @Override
    public User getUserByFirstName(String firstName) {
        return userJpaRepository.findUsersByFirstName(firstName);
    }

    @Override
    public Integer countUsersByFirstName(String firstName) {
        return userJpaRepository.countUsersByFirstName(firstName);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userJpaRepository.delete(user);
    }
}
