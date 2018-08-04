package com.anhtuan.springmvc.CRMSpringMVC.model.notify;

import com.anhtuan.springmvc.CRMSpringMVC.model.login.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "NOTIFICATION_CHANGE")
public class NotificationChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOTIFICATION_OBJECT_ID", referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_NOTIFICATION_OBJECT_2"))
    private NotificationObject notificationObject;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTOR_ID", referencedColumnName = "ID_USER",
            foreignKey = @ForeignKey(name = "FK_NOTIFICATION_ACTOR_ID_IDX"))
    private User user;

    @NotNull
    @Column(name = "ACTION")
    private String action;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NotificationObject getNotificationObject() {
        return notificationObject;
    }

    public void setNotificationObject(NotificationObject notificationObject) {
        this.notificationObject = notificationObject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
