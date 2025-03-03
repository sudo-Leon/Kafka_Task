package org.example;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class FakeMailSender implements JavaMailSender {

    @Override
    public void send(SimpleMailMessage simpleMessage) {
        System.out.println("📧 [FAKE EMAIL] Email отправлен на: " + simpleMessage.getTo()[0]);
        System.out.println("📌 Тема: " + simpleMessage.getSubject());
        System.out.println("✉️ Текст: " + simpleMessage.getText());
    }

    // Остальные методы не используются
    @Override
    public void send(SimpleMailMessage... simpleMessages) {}

    @Override
    public MimeMessage createMimeMessage() {
        return null;
    }

    @Override
    public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
        return null;
    }

    @Override
    public void send(MimeMessage mimeMessage) throws MailException {

    }

    @Override
    public void send(MimeMessage... mimeMessages) throws MailException {

    }

    @Override
    public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {

    }

    @Override
    public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {

    }
}