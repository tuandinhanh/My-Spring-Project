package com.anhtuan.springmvc.CRMSpringMVC.dao;

import com.anhtuan.springmvc.CRMSpringMVC.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserJpaRepository extends JpaRepository<User, Integer> {
    User findUserByLogin(String login);

    User findUsersByFirstName(String fistName);

    Integer countUsersByFirstName(String firstName);

    Integer countUsersByLogin(String login);
}
