package org.example;

public class TaskUpdateEvent {
    private String taskId;
    private String newStatus;

    // Конструктор по умолчанию (нужен для сериализации)
    public TaskUpdateEvent() {
    }

    // Конструктор с параметрами
    public TaskUpdateEvent(String taskId, String newStatus) {
        this.taskId = taskId;
        this.newStatus = newStatus;
    }

    // Геттеры и сеттеры
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    @Override
    public String toString() {
        return "TaskUpdateEvent{" +
                "taskId='" + taskId + '\'' +
                ", newStatus='" + newStatus + '\'' +
                '}';
    }
}