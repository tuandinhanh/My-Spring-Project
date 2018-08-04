package com.anhtuan.springmvc.CRMSpringMVC.dao.notify;

import com.anhtuan.springmvc.CRMSpringMVC.model.notify.NotificationChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface NotifyChangeJpaRepository extends JpaRepository<NotificationChange, Integer> {
}
