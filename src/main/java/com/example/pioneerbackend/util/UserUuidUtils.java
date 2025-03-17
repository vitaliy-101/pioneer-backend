package com.example.pioneerbackend.util;

import com.example.pioneerbackend.entity.basket.Basket;
import com.example.pioneerbackend.entity.user.User;
import com.example.pioneerbackend.exceptions.NotFoundUserIdForBasket;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserUuidUtils {
    public static UserUuid silenceIds(User user, String uuid) {
        return new UserUuid(user == null ? null : user.getId(), uuid);
    }

    public static void setBasketUserId(User user,
                                       String uuid,
                                       Basket basket) {
        if (user != null) {
            basket.setUser(user);
        } else if (uuid != null) {
            basket.setUuid(uuid);
        } else {
            throw new NotFoundUserIdForBasket();
        }
    }
}

