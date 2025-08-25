package com.example.pioneerbackend.controller;

import com.example.pioneerbackend.dto.feedback.FeedbackRequest;
import com.example.pioneerbackend.service.feedback.FeedbackService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
@Slf4j
public class FeedbackController {
    private final FeedbackService service;

    @PostMapping
    public void create(@RequestBody FeedbackRequest request) throws MessagingException {
        try{
            service.createFeedback(request);
        } catch (Exception e){
           log.error("Error while sending feedback", e);
        }

    }
}
