package com.example.pioneerbackend.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderMessage {
    public static final String USER_MESSAGE_THEME = "PIONEER-GAS: Оформление заказа";
    public static final String ADMIN_MESSAGE_THEME = "Новый заказ";

    public static final String USER_MESSAGE_BODY = """
        Здравствуйте! Вы оформили заказ на сайте PIONEER-GAS.
        Ваши контактные данные:
        Имя: %s
        Телефон: %s
        
        """;

    public static final String ADMIN_MESSAGE_BODY = """
        Пользователь оформил новый заказ!
        Данные пользователя:
        Имя: %s
        Телефон: %s
        Email: %s
        
        """;

    public static String createUserMessage(String name, String phone) {
        return String.format(USER_MESSAGE_BODY, name, phone);
    }

    public static String createAdminMessage(String name, String phone, String email) {
        return String.format(ADMIN_MESSAGE_BODY, name, phone, email);
    }

}
