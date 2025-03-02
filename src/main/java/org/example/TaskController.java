package org.example;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final KafkaTemplate<String, TaskUpdateEvent> kafkaTemplate;
    private static final String TOPIC = "task-updates";

    public TaskController(KafkaTemplate<String, TaskUpdateEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/update")
    public String updateTask(@RequestParam String taskId, @RequestParam String newStatus) {
        TaskUpdateEvent event = new TaskUpdateEvent(taskId, newStatus);
        kafkaTemplate.send(TOPIC, event);
        return "Task update event sent: " + event;
    }
}