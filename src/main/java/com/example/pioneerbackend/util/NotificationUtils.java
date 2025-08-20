package com.example.pioneerbackend.util;

import com.example.pioneerbackend.dto.product.ProductSaleInfo;
import lombok.experimental.UtilityClass;

import java.util.List;

import static com.example.pioneerbackend.constant.Urls.PRODUCT_URL;

@UtilityClass
public class NotificationUtils {
    private static final String PRODUCT_LINE = "%-50s %5d шт.\n";
    private static final String DIVIDER = "------------------------\n";
    private static final String HEADER = "\nСостав заказа:\n" + DIVIDER;
    private static final String FOOTER = DIVIDER;

    public static String addProductDataToMessage(String message, List<ProductSaleInfo> productSaleInfos) {
        if (productSaleInfos.isEmpty()) {
            return message;
        }

        var table = new StringBuilder(HEADER);
        for (var info : productSaleInfos) {
            table.append(String.format(PRODUCT_LINE,
                    "• Ссылка на товар: " + PRODUCT_URL + info.getId(),
                    info.getCount()));
        }

        table.append(FOOTER);
        return message + table;
    }

    public static String formatAsPlainText(String text) {
        return "======================\n" +
                "PIONEER-GAS - ЗАКАЗ\n" +
                "======================\n\n" +
                text +
                "\n\nСпасибо за ваш заказ!";
    }


}