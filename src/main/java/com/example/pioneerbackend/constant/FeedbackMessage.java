package com.example.pioneerbackend.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FeedbackMessage {
    public static final String ADMIN_MESSAGE_THEME = "Запрос обратной связи";

    public static final String ADMIN_MESSAGE_BODY = """
        Пользователь запросил обратную связь!
        Данные пользователя:
        Имя: %s
        Телефон: %s
        
        """;

    public static String createAdminMessage(String name, String phone) {
        return String.format(ADMIN_MESSAGE_BODY, name, phone);
    }
}
