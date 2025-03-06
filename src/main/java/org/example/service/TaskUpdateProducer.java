package org.example.service;

import org.example.model.TaskUpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TaskUpdateProducer {

    private static final Logger logger = LoggerFactory.getLogger(TaskUpdateProducer.class);
    private final KafkaTemplate<String, TaskUpdateEvent> kafkaTemplate;

    public TaskUpdateProducer(KafkaTemplate<String, TaskUpdateEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTaskUpdate(TaskUpdateEvent event) {
        logger.info("Отправка события в Kafka: {}", event);
        kafkaTemplate.send("task-updates", event);
    }
}