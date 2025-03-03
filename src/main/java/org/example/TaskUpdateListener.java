package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TaskUpdateListener {

    private static final Logger logger = LoggerFactory.getLogger(TaskUpdateListener.class);
    private final NotificationService notificationService;

    public TaskUpdateListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "task-updates", groupId = "task-group")
    public void handleTaskUpdate(TaskUpdateEvent event) {
        logger.info("Получено обновление задачи: {}", event);
        notificationService.sendNotification(event);
    }
}