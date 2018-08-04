package com.anhtuan.springmvc.CRMSpringMVC.service.notify;

import com.anhtuan.springmvc.CRMSpringMVC.dao.notify.NotifyObjectJpaRepository;
import com.anhtuan.springmvc.CRMSpringMVC.model.notify.Notification;
import com.anhtuan.springmvc.CRMSpringMVC.model.notify.NotificationObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotifyObjectJpaRepository notifyJpaRepository;

    List<NotificationObject> findAllNotificationsByNotifierId(Integer notifierId) {
        return notifyJpaRepository.findNotificationObjectsByNotifierIdOrderByCreateOn(notifierId);
    }

    Page<NotificationObject> findNotificationsByNotifierId(Integer notifierId, Pageable pageable) {
        return notifyJpaRepository.findNotificationObjectsByNotifierId(notifierId, pageable);
    }
}
