package com.example.pioneerbackend.dto.feedback;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Запрос обратной связи пользователем")
public class FeedbackRequest {
    String name;
    String phone;
}
