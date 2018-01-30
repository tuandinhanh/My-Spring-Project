/*
package com.anhtuan.springmvc.listener;

import com.anhtuan.springmvc.dao.RoleDao;
import com.anhtuan.springmvc.dao.UserDao;
import com.anhtuan.springmvc.model.Role;
import com.anhtuan.springmvc.model.RoleType;
import com.anhtuan.springmvc.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(DataSeedingListener.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Listener is done!");
        if (roleDao.findByType("ADMIN") == null) {
            Role role = new Role();
            role.setType(RoleType.ADMIN.getRoleType());
            roleDao.save(role);
            logger.info("save Role type = " + RoleType.ADMIN.getRoleType());
        }
        if (roleDao.findByType("USER") == null) {
            Role role = new Role();
            role.setType(RoleType.ADMIN.getRoleType());
            roleDao.save(role);
            logger.info("save Role type = " + RoleType.USER.getRoleType());
        }

        if (userDao.findBySsoId("ass") == null) {
            User user = new User();
            user.setSsoId("ass");
            user.setPassword("123456");
            user.setFirstName("kiablog");
            user.setLastName("thang khung");
            user.setEmail("kiablog@gmail.com");
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleDao.findByType("ADMIN"));
            roles.add(roleDao.findByType("USER"));
            user.setRoles(roles);
            userDao.save(user);
        }
    }
}
*/
