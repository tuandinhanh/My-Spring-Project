package com.anhtuan.springmvc.dao;

import com.anhtuan.springmvc.model.User;

public interface UserDao {

    void save(User user);

    User findById(int id);

    User findBySsoId(String ssoId);
}
