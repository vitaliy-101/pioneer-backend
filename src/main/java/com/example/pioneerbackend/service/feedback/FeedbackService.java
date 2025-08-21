package com.example.pioneerbackend.service.feedback;

import com.example.pioneerbackend.dto.feedback.FeedbackRequest;
import com.example.pioneerbackend.service.notification.NotificationService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.pioneerbackend.constant.FeedbackMessage.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbackService {
    private final NotificationService notificationService;

    public void createFeedback(FeedbackRequest request) throws MessagingException {
        notificationService.sendToAdmin(
                ADMIN_MESSAGE_THEME,
                createAdminMessage(request.getName(), request.getPhone())
        );
    }
}
