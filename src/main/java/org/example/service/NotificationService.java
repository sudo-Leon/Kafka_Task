package org.example.service;

import org.example.model.TaskUpdateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final JavaMailSender mailSender;

    @Value("${app.notification.recipient-email}")
    private String recipientEmail;  // Теперь email загружается из application.yml

    public NotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendNotification(TaskUpdateEvent event) {
        String subject = "Обновление задачи: " + event.getTaskId();
        String message = "🔔 Статус задачи изменился!\n📌 Детали: " + event.getNewStatus();

        logger.info("📧 Подготовка к отправке email на {}", recipientEmail);
        try {
            sendEmail(recipientEmail, subject, message);
            logger.info("✅ Email успешно отправлен на {}", recipientEmail);
        } catch (MailException e) {
            logger.error("❌ Ошибка при отправке email: {}", e.getMessage());
        }
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}