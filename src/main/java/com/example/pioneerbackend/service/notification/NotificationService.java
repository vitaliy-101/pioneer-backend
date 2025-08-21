package com.example.pioneerbackend.service.notification;

import com.example.pioneerbackend.util.NotificationUtils;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import static com.example.pioneerbackend.util.NotificationUtils.formatAsPlainText;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final JavaMailSender mailSender;

    @Value("${notification.admin.email}")
    private String adminEmail;

    @Value("${notification.from}")
    private String fromEmail;

    public void sendToAdmin(String subject, String text) throws MessagingException {
        sendNotification(subject, text, adminEmail);
    }

    public void sendToUser(String subject, String text, String mail) throws MessagingException {
        sendNotification(subject, text, mail);
    }

    public void sendNotification(String subject, String text, String mail) throws MessagingException {
        var message = mailSender.createMimeMessage();
        var helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(fromEmail);
        helper.setTo(mail);
        helper.setSubject(subject);
        helper.setText(formatAsPlainText(text), false);
        mailSender.send(message);
    }
}
