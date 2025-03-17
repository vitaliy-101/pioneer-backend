package com.example.pioneerbackend.service.basket;

import com.example.pioneerbackend.dto.basket.BasketCreateRequest;
import com.example.pioneerbackend.dto.basket.BasketGetResponse;
import com.example.pioneerbackend.entity.basket.Basket;
import com.example.pioneerbackend.entity.user.User;
import com.example.pioneerbackend.mapper.BasketMapper;
import com.example.pioneerbackend.repository.BasketRepository;
import com.example.pioneerbackend.service.product.ProductService;
import com.example.pioneerbackend.util.UserUuid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.pioneerbackend.util.UserUuidUtils.setBasketUserId;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository repository;
    private final ProductService productService;
    private final BasketMapper mapper;

    public void create(User user,
                       String uuid,
                       BasketCreateRequest request) {
        var basket = mapper.toEntity(request.getCount(), productService.findProductById(request.getProductId()));
        setBasketUserId(user, uuid, basket);
        repository.save(basket);
    }

    @Transactional
    public void updateCount(Integer count, Long productId, UserUuid userUuid) {
        repository.updateBasketCount(count, userUuid.getUserId(), userUuid.getUuid(), productId);
    }

    @Transactional
    public void deleteById(Long productId, UserUuid userUuid) {
        repository.deleteById(userUuid.getUserId(), userUuid.getUuid(), productId);
    }

    public BasketGetResponse findAll(UserUuid userUuid) {
        return new BasketGetResponse(
                mapper.toInfoResponseList(repository.findBasketElements(userUuid.getUserId(), userUuid.getUuid()))
        );
    }

    public Basket findBasketByProductId(Long productId, UserUuid userUuid) {
        return repository.findBasketsByProductId(userUuid.getUserId(), userUuid.getUuid(), productId);
    }

    public List<Basket> findBasketsByProductIds(List<Long> productIds, UserUuid userUuid) {
        return repository.findBasketsByProducts(userUuid.getUserId(), userUuid.getUuid(), productIds);
    }



}
