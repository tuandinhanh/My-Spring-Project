package com.anhtuan.springmvc.CRMSpringMVC.dao.login;

import com.anhtuan.springmvc.CRMSpringMVC.model.login.PersistentLogin;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Repository("customPersistentTokenRepository")
@Transactional
public  class RememberMeDaoImpl implements PersistentTokenRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        PersistentLogin login = new PersistentLogin();
        login.setUsername(persistentRememberMeToken.getUsername());
        login.setSeries(persistentRememberMeToken.getSeries());
        login.setToken(persistentRememberMeToken.getTokenValue());
        login.setLastUsed(persistentRememberMeToken.getDate());
        entityManager.persist(login);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {

        PersistentLogin login = entityManager.find(PersistentLogin.class, series);
        login.setToken(tokenValue);
        login.setLastUsed(lastUsed);
        entityManager.flush();
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String series) {
        String qlString = "select P from " + PersistentLogin.class.getSimpleName() + " P where P.series = :series";
        List<PersistentLogin> logins = entityManager.createQuery(qlString, PersistentLogin.class).setParameter("series", series).getResultList();
        if (logins.size() < 1)
            return null;
        return new PersistentRememberMeToken(logins.get(0).getUsername(), logins.get(0).getSeries(),
                logins.get(0).getToken(), logins.get(0).getLastUsed());
    }

    @Override
    public void removeUserTokens(String username) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PersistentLogin> query = builder.createQuery(PersistentLogin.class);
        Root<PersistentLogin> root = query.from(PersistentLogin.class);

        Predicate hasUsername = builder.equal(root.get("username"), username);
        query.where(hasUsername);
        List<PersistentLogin> logins = entityManager.createQuery(query.select(root)).getResultList();
        if (logins.size() > 0)
            entityManager.remove(logins.get(0));
    }
}
