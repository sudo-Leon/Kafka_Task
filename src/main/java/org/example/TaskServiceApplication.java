package org.example;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class TaskServiceApplication {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(TaskServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TaskServiceApplication.class, args);
        logger.info("✅ Task Service Application started successfully!");
    }
    }
