package com.example.umc9th.domain.notification.repository;

import com.example.umc9th.domain.notification.entity.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

}
