package com.anhtuan.springmvc.dao;

import com.anhtuan.springmvc.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> findAllRoles();

    Role findByType(String type);

    Role findById(int id);

    void save(Role role);
}
