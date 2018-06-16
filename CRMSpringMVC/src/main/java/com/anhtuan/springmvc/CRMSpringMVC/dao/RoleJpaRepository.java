package com.anhtuan.springmvc.CRMSpringMVC.dao;

import com.anhtuan.springmvc.CRMSpringMVC.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoleJpaRepository extends JpaRepository<Role, Integer> {

    @Query("select R from Role R where R.role = :role")
    Role findRoleByRole(@Param("role") String role);
}
