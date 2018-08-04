package com.anhtuan.springmvc.CRMSpringMVC.model.notify;

import com.anhtuan.springmvc.CRMSpringMVC.model.login.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "NOTIFICATION")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NOTIFICATION_OBJECT_ID", referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_NOTIFICATION_OBJECT"))
    private NotificationObject notificationObject;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOTIFIER_ID", referencedColumnName = "ID_USER",
            foreignKey = @ForeignKey(name = "FK_NOTIFICATION_NOTIFIER_ID"))
    private User notifier;

    @NotNull
    @Column(name = "STATUS")
    private Integer status;

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

    public User getNotifier() {
        return notifier;
    }

    public void setNotifier(User notifier) {
        this.notifier = notifier;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
