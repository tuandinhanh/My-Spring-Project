package com.anhtuan.springmvc.CRMSpringMVC.dao.notify;

import com.anhtuan.springmvc.CRMSpringMVC.model.notify.Notification;
import com.anhtuan.springmvc.CRMSpringMVC.model.notify.NotificationObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public interface NotifyJpaRepository extends JpaRepository<Notification, Integer> {
    Page<Notification> findByNotificationObject_Id(Integer notificationObjectId, Pageable pageable);

    List<Notification> findByNotificationObject_Id(Integer notificationObjectId);

    Page<Notification> findByNotifier_Id(Integer notifierId, Pageable pageable);

    List<Notification> findByNotifier_Id(Integer notifierId);
}
