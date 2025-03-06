package org.example.controller;

import org.example.model.Task;
import org.example.model.TaskUpdateEvent;
import org.example.repository.TaskRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final KafkaTemplate<String, TaskUpdateEvent> kafkaTemplate;
    private final TaskRepository taskRepository;
    private static final String TOPIC = "task-updates";

    public TaskController(KafkaTemplate<String, TaskUpdateEvent> kafkaTemplate, TaskRepository taskRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.taskRepository = taskRepository;
    }

    @PostMapping("/update")
    public String updateTask(@RequestParam String taskId, @RequestParam String newStatus) {
        // Проверяем, есть ли таск в БД
        Optional<Task> optionalTask = taskRepository.findByTaskId(taskId);
        Task task;

        if (optionalTask.isPresent()) {
            task = optionalTask.get();
            task.setStatus(newStatus);
        } else {
            task = new Task(taskId, newStatus);
        }

        // Сохраняем обновленный таск в БД
        taskRepository.save(task);

        // Отправляем событие в Kafka
        TaskUpdateEvent event = new TaskUpdateEvent(taskId, newStatus);
        kafkaTemplate.send(TOPIC, event);

        return "Task updated and event sent: " + event;
    }
}