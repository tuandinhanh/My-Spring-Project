package com.anhtuan.springmvc.dao;

import com.anhtuan.springmvc.model.User;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public void save(User user) {
        persist(user);
    }

    @Override
    public User findById(int id) {
        return getByKey(id);
    }

    @Override
    public User findBySsoId(String ssoId) {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get("ssoId"), ssoId));
        try {
            User user = getSession().createQuery(criteriaQuery).getSingleResult();
            Hibernate.initialize(user.getRoles());
            return user;
        } catch (NoResultException e) {
            logger.info("User not found by ssoId = " + ssoId);
            return null;
        }
    }
}
