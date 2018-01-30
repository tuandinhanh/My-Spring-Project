package com.anhtuan.springmvc.dao;

import com.anhtuan.springmvc.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("roleDao")
public class RoleDaoImpl extends AbstractDao<Integer, Role> implements RoleDao {

    private Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

    @Override
    public List<Role> findAllRoles() {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> roleRoot = criteriaQuery.from(Role.class);
        criteriaQuery.orderBy(criteriaBuilder.asc(roleRoot.get("type")));
        return getSession().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Role findByType(String type) {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> roleRoot = criteriaQuery.from(Role.class);
        criteriaQuery.select(roleRoot).where(criteriaBuilder.equal(roleRoot.get("type"), type));
        try {
            return getSession().createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            logger.info(String.format("Role not found by Type = %s\n%s", type, e.getMessage()));
            return null;
        }

    }

    @Override
    public Role findById(int id) {
        return getByKey(id);
    }

    @Override
    public void save(Role role) {
        persist(role);
    }
}
