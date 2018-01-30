package com.anhtuan.springmvc.service;

import com.anhtuan.springmvc.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAllRoles();

    Role findByType(String type);

    Role findById(int id);

    void save(Role role);
}
