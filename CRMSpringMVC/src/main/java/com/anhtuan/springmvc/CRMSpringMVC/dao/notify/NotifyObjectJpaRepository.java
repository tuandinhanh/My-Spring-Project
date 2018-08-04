package com.anhtuan.springmvc.CRMSpringMVC.dao.notify;

import com.anhtuan.springmvc.CRMSpringMVC.model.notify.NotificationObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface NotifyObjectJpaRepository extends JpaRepository<NotificationObject, Integer> {

    List<NotificationObject> findNotificationObjectsByNotifierIdOrderByCreateOn(Integer notifierId);

    Page<NotificationObject> findNotificationObjectsByNotifierId(Integer notifierId, Pageable pageable);

}
