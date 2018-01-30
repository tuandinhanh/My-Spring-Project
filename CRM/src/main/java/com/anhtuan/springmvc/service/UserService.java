package com.anhtuan.springmvc.service;

import com.anhtuan.springmvc.model.User;

public interface UserService {

    public void save(User user);

    public User findById(int id);

    public User findBySsoId(String ssoId);
}