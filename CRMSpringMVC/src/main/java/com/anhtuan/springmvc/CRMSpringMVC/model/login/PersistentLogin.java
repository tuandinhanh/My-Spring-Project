package com.anhtuan.springmvc.CRMSpringMVC.model.login;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PERSISTENT_LOGINS")
public class PersistentLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "USERNAME", nullable = false, length = 64)
    private String username;

    @Id
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "SERIES", nullable = false, length = 64)
    private String series;

    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "TOKEN", nullable = false, length = 64)
    private String token;

    @NotNull
    @Column(name = "LAST_USED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUsed;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}
