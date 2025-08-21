package com.example.pioneerbackend.service.order;

import com.example.pioneerbackend.dto.order.OrderRequest;
import com.example.pioneerbackend.dto.product.ProductSaleInfo;
import com.example.pioneerbackend.service.basket.BasketService;
import com.example.pioneerbackend.service.notification.NotificationService;
import com.example.pioneerbackend.service.product.ProductService;
import com.example.pioneerbackend.util.UserUuid;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.pioneerbackend.constant.OrderMessage.*;
import static com.example.pioneerbackend.util.NotificationUtils.addProductDataToMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final BasketService basketService;
    private final ProductService productService;
    private final NotificationService notificationService;

    public void createOrder(OrderRequest request, UserUuid userUuid) throws MessagingException {
        var productSaleInfos = basketService.findProductForSale(userUuid);

        productService.updateSales(productSaleInfos);
        basketService.delete(extractProductIds(productSaleInfos), userUuid);

        sendMessageToAdmin(productSaleInfos, request);
        sendMessageToUser(productSaleInfos, request);
    }

    private void sendMessageToAdmin(List<ProductSaleInfo> productSaleInfos, OrderRequest request) throws MessagingException {
        var message = createAdminMessage(
                request.getName(),
                request.getPhoneNumber(),
                request.getMail()
        );
        notificationService.sendToAdmin(
                ADMIN_MESSAGE_THEME,
                addProductDataToMessage(message, productSaleInfos)
        );
    }

    private void sendMessageToUser(List<ProductSaleInfo> productSaleInfos, OrderRequest request) throws MessagingException {
        var message = createUserMessage(
                request.getName(),
                request.getPhoneNumber()
        );
        notificationService.sendToUser(
                USER_MESSAGE_THEME,
                addProductDataToMessage(message, productSaleInfos),
                request.getMail()
        );
    }

    private List<Long> extractProductIds(List<ProductSaleInfo> productSaleInfos) {
        return productSaleInfos.stream()
                .map(ProductSaleInfo::getId)
                .toList();
    }
}

