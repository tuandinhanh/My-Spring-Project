package com.anhtuan.springmvc.CRMSpringMVC.model.notify;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "NOTIFICATION_OBJECT")
public class NotificationObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Column(name = "ENTITY_TYPE")
    private Integer entityType;

    @NotNull
    @Column(name = "ENTITY_ID")
    private Integer entityId;

    @NotNull
    @Column(name = "CREATED_ON")
    private Date createOn;

    @OneToMany(mappedBy = "notificationObject", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    @Transient
    private Integer actorId;

    @Transient
    private Integer notifierId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntityType() {
        return entityType;
    }

    public void setEntityType(Integer entityType) {
        this.entityType = entityType;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public Integer getNotifierId() {
        return notifierId;
    }

    public void setNotifierId(Integer notifierId) {
        this.notifierId = notifierId;
    }
}
