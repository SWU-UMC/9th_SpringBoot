package com.example.umc9th.domain.notification.repository;

import com.example.umc9th.domain.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
