package com.anhtuan.springmvc.CRMSpringMVC.dao;

import com.anhtuan.springmvc.CRMSpringMVC.model.PersistentLogin;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

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
        PersistentLogin login = new PersistentLogin();

        login.setSeries(series);
        login.setToken(tokenValue);
        login.setLastUsed(lastUsed);

        entityManager.merge(login);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String series) {
        String qlString = "select P from " + PersistentLogin.class.getSimpleName().toUpperCase() + " P where P.SERIES = :series";
        PersistentLogin login = entityManager.createQuery(qlString, PersistentLogin.class).setParameter("series", series).getSingleResult();
        if (login != null)
            return new PersistentRememberMeToken(login.getUsername(), login.getSeries(), login.getToken(), login.getLastUsed());
        return null;
    }

    @Override
    public void removeUserTokens(String username) {

    }
}
