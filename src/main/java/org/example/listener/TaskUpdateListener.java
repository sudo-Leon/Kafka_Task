package org.example.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.model.TaskUpdateEvent;
import org.example.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskUpdateListener {

    private static final Logger logger = LoggerFactory.getLogger(TaskUpdateListener.class);
    private final NotificationService notificationService;

    public TaskUpdateListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(
            topics = "${app.kafka.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleTaskUpdates(List<ConsumerRecord<String, TaskUpdateEvent>> records, Acknowledgment acknowledgment) {
        if (records.isEmpty()) {
            return;
        }

        for (ConsumerRecord<String, TaskUpdateEvent> record : records) {
            TaskUpdateEvent event = record.value();
            logger.info("📥 Получено обновление задачи: {}", event);
            notificationService.sendNotification(event);
        }

        acknowledgment.acknowledge(); // Подтверждаем обработку всех сообщений в batch
        logger.info("✅ Все сообщения обработаны и подтверждены.");
    }
}